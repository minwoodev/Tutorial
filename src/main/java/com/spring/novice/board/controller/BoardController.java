package com.spring.novice.board.controller;

import java.io.File;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.novice.board.service.BoardService;
import com.spring.novice.board.service.CommentService;
import com.spring.novice.vo.BoardLikeVo;
import com.spring.novice.vo.BoardVo;
import com.spring.novice.vo.FileVo;
import com.spring.novice.vo.ReadPageVo;
import com.spring.novice.vo.UserVo;

@Controller
@RequestMapping("/board/*")

public class BoardController {

	@Autowired
	BoardService boardService;

	@Autowired
	CommentService commentService;

	@RequestMapping(value = "mainPage", method = RequestMethod.GET)
	public String mainPage(Model model, String category, String keyword,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {

		ArrayList<HashMap<String, Object>> dataList = boardService.getBoardList(category, keyword, pageNum);

		int count = boardService.getBoardCount(category, keyword);

		int totalPageCount = (int) Math.ceil(count / 10.0);

		// 1 2 3 4 5 , 6 7 8 9 10
		int startPage = ((pageNum - 1) / 5) * 5 + 1;
		int endPage = ((pageNum - 1) / 5 + 1) * (5);
		if (endPage > totalPageCount) {
			endPage = totalPageCount;
		}

		// 페이징 링크 검색 추가 옵션...
		String additionalParam = "";

		if (category != null) {
			additionalParam += "&category=" + category;
		}

		if (keyword != null) {
			// URL encoding -> 영어 숫자 특수 문자 아닌 값이 존재 할때...
			try {
				keyword = URLEncoder.encode(keyword, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}

			additionalParam += "&keyword=" + keyword;
		}

		model.addAttribute("additionalParam", additionalParam);
		model.addAttribute("count", count);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("dataList", dataList);

		return "board/mainPage";
	}

	@RequestMapping("writeContentPage")
	public String writeContentPage(@ModelAttribute("boardVo") BoardVo vo) {
		return "board/writeContentPage";
	}

	@RequestMapping("writeContentProcess")
	public String writeContentProcess(@Valid BoardVo param, BindingResult result, HttpSession session,
			MultipartFile[] uploadFiles) {

		if (result.hasErrors()) {
			return "board/writeContentPage";
		}

		ArrayList<FileVo> fileVoList = new ArrayList<FileVo>();

		String uploadFolder = "C:/uploadFolder/";

		if (uploadFiles != null) {
			for (MultipartFile uploadFile : uploadFiles) {
				if (uploadFile.isEmpty()) {
					continue;
				}

				// 날짜별 폴더 생성... 2022/01/19/
				Date today = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
				String folderPath = sdf.format(today);

				File todayFolder = new File(uploadFolder + folderPath);

				if (!todayFolder.exists()) {
					todayFolder.mkdirs();
				}

				// 중복되지 않게 저장해야된다...!!...
				// 방법 : 랜덤 + 시간
				String fileName = "";
				UUID uuid = UUID.randomUUID();
				fileName += uuid.toString();

				long currentTime = System.currentTimeMillis();
				fileName += "_" + currentTime;

				// 확장자 추가...
				String originalFileName = uploadFile.getOriginalFilename();
				String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
				fileName += ext;

				try {
					uploadFile.transferTo(new File(uploadFolder + folderPath + fileName));
				} catch (Exception e) {
					e.printStackTrace();
				}

				FileVo fileVo = new FileVo();
				fileVo.setOrg_file_name(originalFileName);
				fileVo.setStored_file_name(fileName);
				fileVoList.add(fileVo);
			}
		}

		System.out.println(uploadFiles);

		UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");
		param.setUser_no(sessionUser.getUser_no());

		boardService.insertBoard(param, fileVoList);
		return "redirect:./mainPage";
	}

	@RequestMapping("readContentPage")
	public String readContentPage(@RequestParam(value = "board_no", defaultValue = "0") int no, Model model,
			HttpServletRequest request, HttpSession session) {
		ArrayList<ReadPageVo> readPageVo = boardService.getReadPageList(no);

		if (boardService.isSelectReadBoardNo(no)) {
			if (!boardService.isSelectReadClientIp(request.getRemoteAddr())) {
				ReadPageVo param = new ReadPageVo();
				param.setBoard_no(no);
				param.setClient_ip(request.getRemoteAddr());

				boardService.insertReadPage(param);
				boardService.increaseReadCount(no);
			}
		} else {
			ReadPageVo param = new ReadPageVo();
			param.setBoard_no(no);
			param.setClient_ip(request.getRemoteAddr());

			boardService.insertReadPage(param);
			boardService.increaseReadCount(no);
		}

		for (ReadPageVo param : readPageVo) {
			if (param != null) {
				if (boardService.isSelectReadClientIp(request.getRemoteAddr())) {
					if (param.getClient_ip().equals(request.getRemoteAddr())) {
						Date writeDate = new Date(System.currentTimeMillis());
						Date tagetDate = new Date(param.getRead_write_date().getTime() + 1000 * 60 * 60 * 24);

						if (writeDate.after(tagetDate)) {
							boardService.increaseReadCount(no);
							boardService.updateReadPage(param);
						}
					}
				}
			}
		}

		HashMap<String, Object> map = boardService.getBoard(no);
		ArrayList<HashMap<String, Object>> dataList = commentService.getCommentList(no);

		ArrayList<HashMap<String, Object>> fileList = boardService.selectFileList(no);

		int totalLikeCount = boardService.getTotalLikeCount(no);
		model.addAttribute("totalLikeCount", totalLikeCount);

		UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");
		if (sessionUser != null) {
			// 로그인을 했을때...
			int userNo = sessionUser.getUser_no();
			BoardLikeVo boardLikeVo = new BoardLikeVo();
			boardLikeVo.setUser_no(userNo);
			boardLikeVo.setBoard_no(no);

			int myLikeCount = boardService.getMyLikeCount(boardLikeVo);
			model.addAttribute("myLikeCount", myLikeCount);
		}

		model.addAttribute("data", map);
		model.addAttribute("dataList", dataList);
		model.addAttribute("fileList", fileList);

		return "board/readContentPage";
	}

	@RequestMapping(value = "updateContentPage", method = RequestMethod.POST)
	public String updateContentPage(int board_no, Model model, @ModelAttribute("boardVo") BoardVo param) {

		HashMap<String, Object> data = boardService.getBoard(board_no);
		ArrayList<HashMap<String, Object>> fileList = boardService.selectFileList(board_no);

		model.addAttribute("data", data);
		model.addAttribute("file", fileList);

		return "board/updateContentPage";

	}

	@RequestMapping("updateContentProcess")
	public String updateContentProcess(@Valid BoardVo param, BindingResult result,
			@RequestParam(value = "fileNoDel[]") String[] files,
			@RequestParam(value = "fileNameDel[]") String[] fileNames,
			@RequestParam(value = "uploadFiles") MultipartFile[] uploadFiles) {

		if (result.hasErrors()) {
			return "board/udpateContentPage";
		}

		ArrayList<FileVo> fileVoList = new ArrayList<FileVo>();

		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
		String folderPath = sdf.format(today);
		File todayFolder = new File("C://uploadFolder//" + folderPath);

		if (uploadFiles != null) {
			for (MultipartFile uploadFile : uploadFiles) {
				if (uploadFile.isEmpty()) {
					continue;
				}

				if (!todayFolder.exists()) {
					todayFolder.mkdirs();
				}

				// 중복되지 않게 저장해야된다...!!...
				// 방법 : 랜덤 + 시간
				String fileName = "";
				UUID uuid = UUID.randomUUID();
				fileName += uuid.toString();

				long currentTime = System.currentTimeMillis();
				fileName += "_" + currentTime;

				// 확장자 추가...
				String originalFileName = uploadFile.getOriginalFilename();
				String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
				fileName += ext;

				try {
					uploadFile.transferTo(new File("C://uploadFolder//" + folderPath + fileName));
				} catch (Exception e) {
					e.printStackTrace();
				}

				FileVo fileVo = new FileVo();
				fileVo.setBoard_no(param.getBoard_no());
				fileVo.setOrg_file_name(originalFileName);
				fileVo.setStored_file_name(fileName);
				fileVo.setFile_size((int) uploadFile.getSize());
				fileVoList.add(fileVo);
			}
		}

		boardService.updateBoard(param, files, fileNames, fileVoList);

		return "redirect:./readContentPage?board_no=" + param.getBoard_no();
	}

	@RequestMapping("deleteContentProcess")
	public String deleteContentProcess(int board_no) {

		boardService.deleteContentPage(board_no);

		return "redirect:./mainPage";

	}

	@RequestMapping(value = "/fileDown")
	public void fileDown(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception {
		Map<String, Object> resultMap = boardService.selectFileInfo(map);
		String storedFileName = (String) resultMap.get("STORED_FILE_NAME");
		String originalFileName = (String) resultMap.get("ORG_FILE_NAME");
		Timestamp time = (Timestamp) resultMap.get("UPLOAD_WRITE_DATE");

		Date today = new Date(time.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
		String folderPath = sdf.format(today);

		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
		byte fileByte[] = org.apache.commons.io.FileUtils
				.readFileToByteArray(new File("C://uploadFolder//" + folderPath + "//" + storedFileName));

		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition",
				"attachment; fileName=\"" + URLEncoder.encode(originalFileName, "UTF-8") + "\";");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	@RequestMapping("likeProcess")
	public String likeProcess(BoardLikeVo param, HttpSession session) {

		// 행위자 정보는 세션에서 꼭 뽑아오자...
		UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");
		int userNo = sessionUser.getUser_no();
		param.setUser_no(userNo);

		boardService.doLike(param);

		return "redirect:./readContentPage?board_no=" + param.getBoard_no();
	}

	@RequestMapping(value = "myBoardPage")
	public String myBoardPage(Model model, int user_no,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		ArrayList<HashMap<String, Object>> dataList = boardService.getMyBoardList(user_no, pageNum);

		int count = boardService.getBoardCount("", "");

		int totalPageCount = (int) Math.ceil(count / 10.0);

		// 1 2 3 4 5 , 6 7 8 9 10
		int startPage = ((pageNum - 1) / 5) * 5 + 1;
		int endPage = ((pageNum - 1) / 5 + 1) * (5);
		if (endPage > totalPageCount) {
			endPage = totalPageCount;
		}

		model.addAttribute("count", count);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("dataList", dataList);

		return "board/myBoardPage";
	}
}
