package com.spring.novice.board.mapper;

import java.util.ArrayList;

import com.spring.novice.vo.BoardVo;

public interface BoardSQLMapper {
	
	/* 게시글 목록 출력 */
	public ArrayList<BoardVo> getBoardList();
	
	/* 게시글 작성*/
	public void insertBoard(BoardVo param);
}
