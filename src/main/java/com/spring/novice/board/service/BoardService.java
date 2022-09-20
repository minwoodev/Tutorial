package com.spring.novice.board.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.novice.board.mapper.BoardSQLMapper;
import com.spring.novice.user.mapper.UserSQLMapper;
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

	public ArrayList<HashMap<String, Object>> getBoardList() {
		ArrayList<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();

		ArrayList<BoardVo> boardVoList = boardSQLMapper.getBoardList();

		for (BoardVo boardVo : boardVoList) {

			int userNo = boardVo.getUser_no();
			UserVo userVo = userSQLMapper.getUserByNo(userNo);

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("boardVo", boardVo);
			map.put("userVo", userVo);

			dataList.add(map);

		}

		return dataList;
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
		
		map.put("userVo", userVo);
		map.put("boardVo", boardVo);
		
		return map;
	}
	
	public void updateBoard(BoardVo param) {
		boardSQLMapper.updateBoard(param);
	}
	
	public void deleteContentPage(int board_no) {
		boardSQLMapper.deleteContentPage(board_no);
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
}
