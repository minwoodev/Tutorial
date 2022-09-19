package com.spring.novice.board.mapper;

import java.util.ArrayList;

import com.spring.novice.vo.CommentVo;

public interface CommentSQLMapper {
	
	public ArrayList<CommentVo> getCommentByList();

}
