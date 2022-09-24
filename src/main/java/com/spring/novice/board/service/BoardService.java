package com.spring.novice.board.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.novice.board.mapper.BoardSQLMapper;
import com.spring.novice.board.mapper.CommentSQLMapper;
import com.spring.novice.user.mapper.UserSQLMapper;
import com.spring.novice.vo.BoardLikeVo;
import com.spring.novice.vo.BoardVo;
import com.spring.novice.vo.FileVo;
import com.spring.novice.vo.ReadPageVo;
import com.spring.novice.vo.UserVo;

@Service
public class BoardService {

	@Autowired
	BoardSQLMapper boardSQLMapper;

	@Autowired
	UserSQLMapper userSQLMapper;

	@Autowired
	CommentSQLMapper commentSQLMapper;

	public ArrayList<HashMap<String, Object>> getBoardList(String category, String keyword, int pageNum) {
		ArrayList<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();

		ArrayList<BoardVo> boardVoList = boardSQLMapper.getBoardList(pageNum);

		if (category != null || keyword != null) {
			switch (category) {
			case "title":
				boardVoList = boardSQLMapper.selectByTitle(keyword, pageNum);
				break;
			case "content":
				boardVoList = boardSQLMapper.selectByContent(keyword, pageNum);
				break;
			case "nick":
				boardVoList = boardSQLMapper.selectByNickName(keyword, pageNum);
				break;
			}
		}

		for (BoardVo boardVo : boardVoList) {

			int userNo = boardVo.getUser_no();
			int totalLikeCount = boardSQLMapper.getTotalLikeCount(boardVo.getBoard_no());
			int totalCommentCount = commentSQLMapper.getTotalCommentCount(boardVo.getBoard_no());
			UserVo userVo = userSQLMapper.getUserByNo(userNo);

			HashMap<String, Object> map = new HashMap<String, Object>();

			// 현재 글이...지금시간 -3 시간 보다 클때...
			Date writeDate = boardVo.getBoard_write_date();
			long writeTime = writeDate.getTime();

			long currentTime = System.currentTimeMillis();
			long targetTime = currentTime - 1000 * 60 * 60 * 3;

			if (writeTime > targetTime) {
				map.put("newKeyword", true);
			}

			map.put("boardVo", boardVo);
			map.put("userVo", userVo);
			map.put("totalLikeCount", totalLikeCount);
			map.put("totalCommentCount", totalCommentCount);

			dataList.add(map);

		}

		return dataList;
	}

	public int getBoardCount(String category, String keyword) {

		return boardSQLMapper.getBoardCount(category, keyword);
	}

	public void insertBoard(BoardVo param, ArrayList<FileVo> fileVoList) {

		int boardNo = boardSQLMapper.createBoardPk();
		param.setBoard_no(boardNo);

		boardSQLMapper.insertBoard(param);

		for (FileVo fileVo : fileVoList) {
			fileVo.setBoard_no(boardNo);

			boardSQLMapper.insertFile(fileVo);
		}

	}

	public HashMap<String, Object> getBoard(int board_no) {

		HashMap<String, Object> map = new HashMap<String, Object>();

		BoardVo boardVo = boardSQLMapper.getBoardByNo(board_no);
		UserVo userVo = userSQLMapper.getUserByNo(boardVo.getUser_no());

		String content = boardVo.getBoard_content();
		content = StringEscapeUtils.escapeHtml4(content);
		content = content.replaceAll("\n", "<br>");
		content = content.replaceAll(" ", "&nbsp;");
		boardVo.setBoard_content(content);

		map.put("userVo", userVo);
		map.put("boardVo", boardVo);

		return map;
	}

	public void updateBoard(BoardVo param, String file[], String fileNames[], ArrayList<FileVo> fileVoList) {
		boardSQLMapper.updateBoard(param);

		for (int i = 0; i < file.length; i++) {
			boardSQLMapper.updateFile(Integer.parseInt(file[i]));
		}

		for (int i = 0; i < fileVoList.size(); i++) {
			boardSQLMapper.insertFile(fileVoList.get(i));
		}

	}

	public void updateFile(int file_no) {
		boardSQLMapper.updateFile(file_no);
	}

	public void deleteContentPage(int board_no) {
		/* 게시글 삭제 */
		boardSQLMapper.deleteContentPage(board_no);

		/* 게시글 댓글 삭제 */
		commentSQLMapper.deleteAllComment(board_no);

		/* 게시글 중복 조회 증가 방지 삭제 */
		boardSQLMapper.deleteReadPage(board_no);

		/* 게시글 첨부파일 삭제 */
		boardSQLMapper.deleteAllFile(board_no);

	}

	public void insertReadPage(ReadPageVo param) {
		boardSQLMapper.insertReadPage(param);
	}

	public ArrayList<ReadPageVo> getReadPageList(int board_no) {
		return boardSQLMapper.getReadPageList(board_no);
	}

	public boolean isSelectReadClientIp(String client_ip) {
		return boardSQLMapper.selectByClientIp(client_ip) > 0;
	}

	public boolean isSelectReadBoardNo(int board_no) {
		return boardSQLMapper.selectByReadByBoardNo(board_no) > 0;
	}

	public boolean selectByReadPage(ReadPageVo param) {
		return boardSQLMapper.selectByReadPage(param) > 0;
	}

	public void increaseReadCount(int board_no) {
		boardSQLMapper.increaseReadCount(board_no);
	}

	public void updateReadPage(ReadPageVo param) {
		boardSQLMapper.updateReadPage(param);
	}

	public ArrayList<HashMap<String, Object>> selectFileList(int board_no) {
		ArrayList<HashMap<String, Object>> list = boardSQLMapper.selectFileList(board_no);

		return list;
	}

	public Map<String, Object> selectFileInfo(Map<String, Object> map) {
		Map<String, Object> list = boardSQLMapper.selectFileInfo(map);

		return list;
	}

	public void doLike(BoardLikeVo vo) {

		int count = boardSQLMapper.getMyLikeCount(vo);

		if (count > 0) {
			boardSQLMapper.deleteLike(vo);
		} else {
			boardSQLMapper.insertLike(vo);
		}
	}

	public int getTotalLikeCount(int boardNo) {
		return boardSQLMapper.getTotalLikeCount(boardNo);
	}

	public int getMyLikeCount(BoardLikeVo vo) {
		return boardSQLMapper.getMyLikeCount(vo);
	}
}
