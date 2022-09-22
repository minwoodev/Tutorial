package com.spring.novice.board.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.spring.novice.vo.BoardVo;
import com.spring.novice.vo.FileVo;
import com.spring.novice.vo.ReadPageVo;

public interface BoardSQLMapper {

	/* 게시글 목록 출력 */
	public ArrayList<BoardVo> getBoardList(@Param("pageNum") int pageNum);

	/* 게시글 작성 */
	public void insertBoard(BoardVo param);

	/* 게시글 불러오기 */
	public BoardVo getBoardByNo(int board_no);

	/* 게시글 수정하기 */
	public void updateBoard(BoardVo param);

	/* 게시글 삭제하기 */
	public void deleteContentPage(int board_no);

	/* 조회수 증가 중복 방지 */
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

	/* 조회수 증가 중복 삭제 */
	public void deleteReadPage(int board_no);

	/* 게시글 번호 증가 */
	public int createBoardPk();

	/* 첨부파일 */
	public void insertFile(FileVo param);

	/* 첨부파일 목록 */
	public ArrayList<HashMap<String, Object>> selectFileList(int board_no);

	/* 첨부파일 정보 */
	public Map<String, Object> selectFileInfo(Map<String, Object> map);

	/* 게시글 첨부파일 전체 삭제 */
	public void deleteAllFile(int board_no);

	/* 게시글 첨부 파일 수정 */
	public void updateFile(int file_no);

	/* 게시글 제목 검색 */
	public ArrayList<BoardVo> selectByTitle(@Param("title") String title, @Param("pageNum") int pageNum);

	/* 게시글 내용 검색 */
	public ArrayList<BoardVo> selectByContent(@Param("content") String content, @Param("pageNum") int pageNum);

	/* 게시글 작성자 검색 */
	public ArrayList<BoardVo> selectByNickName(@Param("user_nickname") String user_nickname,
			@Param("pageNum") int pageNum);

	/* 게시글 총 갯수 */
	public int getBoardCount(@Param("category") String category, @Param("keyword") String keyword);
}
