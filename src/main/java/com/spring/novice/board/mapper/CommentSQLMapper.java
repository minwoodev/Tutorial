package com.spring.novice.board.mapper;

import java.util.ArrayList;

import com.spring.novice.vo.CommentVo;

public interface CommentSQLMapper {

	/* 댓글 목록 불러오기 */
	public ArrayList<CommentVo> getCommentByList(int board_no);

	/* 댓글 작성 */
	public void insertComment(CommentVo param);

	/* 댓글 조회 */
	public CommentVo getCommentByNo(int comment_no);
	
	/* 댓글 수정 */
	public void updateComment(CommentVo param);
	
	/* 댓글 삭제 */
	public void deleteComment(int comment_no);
	
	/* 게시글 댓글 전체 삭제*/
	public void deleteAllComment(int board_no);
	
	
}
