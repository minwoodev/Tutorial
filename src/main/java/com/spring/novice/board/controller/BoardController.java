package com.spring.novice.board.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.novice.board.service.BoardService;
import com.spring.novice.board.service.CommentService;
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

	@RequestMapping("mainPage")
	public String mainPage(Model model) {

		ArrayList<HashMap<String, Object>> dataList = boardService.getBoardList();

		model.addAttribute("dataList", dataList);

		return "board/mainPage";
	}

	@RequestMapping("writeContentPage")
	public String writeContentPage(@ModelAttribute("boardVo") BoardVo vo) {
		return "board/writeContentPage";
	}

	@RequestMapping("writeContentProcess")
	public String writeContentProcess(@Valid BoardVo param, BindingResult result, HttpSession session, MultipartFile [] uploadFiles ) {

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
				
				Date today = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				String folderPath = sdf.format(today);
				
				File todayFoloder = new File(uploadFolder + folderPath);
				
				if (!todayFoloder.exists()) {
					todayFoloder.mkdirs();
				}
				
				String fileName = "";
				UUID uuid = UUID.randomUUID();
				fileName += uuid.toString();
				
				long currentTime = System.currentTimeMillis();
				fileName += "_" + currentTime;
				
				String originalFileName = uploadFile.getOriginalFilename();
 				String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
 				fileName += ext;
 				
 				try {
					uploadFile.transferTo(new File(uploadFolder + folderPath + fileName));
				}catch(Exception e) {
					e.printStackTrace();
				}
 				 				
				String storedFileName = uuid.toString() + ext;

 				
 				FileVo fileVo = new FileVo();
 				fileVo.setOrg_file_name(originalFileName);
 				fileVo.setStored_file_name(storedFileName);
 				fileVoList.add(fileVo);
			}
		}
		
		UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");
		param.setUser_no(sessionUser.getUser_no());

		boardService.insertBoard(param, fileVoList);

		return "redirect:./mainPage";

	}
	
	@RequestMapping("readContentPage")
	public String readContentPage(@RequestParam(value="board_no", defaultValue="0") int no, Model model, HttpServletRequest request) {
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
		
		model.addAttribute("data", map);
		model.addAttribute("dataList", dataList);
		model.addAttribute("fileList", fileList);

		return "board/readContentPage";
	}

	@RequestMapping("updateContentPage")
	public String updateContentPage(int board_no, Model model) {
		
		HashMap<String, Object> data = boardService.getBoard(board_no);
		
		model.addAttribute("data", data);
		
		return "board/updateContentPage";
		
	}	
	
	@RequestMapping("updateContentProcess")
	public String updateContentProcess(@Valid BoardVo param, BindingResult result) {
		
		if (result.hasErrors()) {
			return "board/udpateContentPage";
		}
		
		boardService.updateBoard(param);
		
		return "redirect:./readContentPage?board_no="+param.getBoard_no();
	}
	
	@RequestMapping("deleteContentProcess")
	public String deleteContentProcess(int board_no) {
		
		boardService.deleteContentPage(board_no);
		
		return "redirect:./mainPage";
		
	}
}
