package com.spring.novice.board.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.novice.board.mapper.BoardSQLMapper;
import com.spring.novice.user.mapper.UserSQLMapper;
import com.spring.novice.vo.BoardVo;
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

	public void insertBoard(BoardVo param) {
		boardSQLMapper.insertBoard(param);
	}
	
	public HashMap<String, Object> getBoard(int board_no) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		
		BoardVo boardVo = boardSQLMapper.getBoardByNo(board_no);
		UserVo userVo = userSQLMapper.getUserByNo(boardVo.getUser_no());
		
		map.put("userVo", userVo);
		map.put("boardVo", boardVo);
		
		return map;
	}
}
