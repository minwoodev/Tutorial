package com.spring.novice.board.mapper;

import java.util.ArrayList;

import com.spring.novice.vo.CommentVo;

public interface CommentSQLMapper {
	
	/* 댓글 목록 불러오기 */
	public ArrayList<CommentVo> getCommentByList();

	/* 댓글 작성 */
	public void insertComment(CommentVo param);
}
