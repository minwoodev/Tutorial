package com.spring.novice.board.mapper;

import java.util.ArrayList;

import com.spring.novice.vo.BoardVo;
import com.spring.novice.vo.ReadPageVo;

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
	
	/* 조회수 증가 중복 방지  */
	public void insertReadPage(ReadPageVo param);

	/* 조회수 증가 중복 방지 조회 */
	public ArrayList<ReadPageVo> getReadPageList(int board_no);

	/* 클라이언트 아이피 조회 쿼리 */
	public int selectByClientIp(String client_ip);

	/* 조회수 중복 증가 게시글 조회 */
	public int selectByReadByBoardNo(int board_no);

	/* 조회수 중복 증가 방지 조회 (게시글번호, 아이피로 조회) */
	public int selectByReadPage(ReadPageVo param);

	/* 조회수 증가 */
	public void increaseReadCount(int board_no);
	
	public void updateReadPage(ReadPageVo param);
	
}
