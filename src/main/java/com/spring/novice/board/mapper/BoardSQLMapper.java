package com.spring.novice.board.mapper;

import java.util.ArrayList;

import com.spring.novice.vo.BoardVo;

public interface BoardSQLMapper {
	
	/* 게시글 목록 출력 */
	public ArrayList<BoardVo> getBoardList();
	
	/* 게시글 작성 */
	public void insertBoard(BoardVo param);
	
	/* 게시글 불러오기 */
	public BoardVo getBoardByNo(int board_no);
	
	/* 게시글 수정하기 */
	public void updateBoard(BoardVo param);
	
	/* 게시글 삭제하기 */
	public void deleteContentPage(int board_no);
	
}
