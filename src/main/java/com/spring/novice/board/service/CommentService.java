package com.spring.novice.board.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.novice.board.mapper.BoardSQLMapper;
import com.spring.novice.board.mapper.CommentSQLMapper;
import com.spring.novice.user.mapper.UserSQLMapper;
import com.spring.novice.vo.BoardVo;
import com.spring.novice.vo.CommentLikeVo;
import com.spring.novice.vo.CommentVo;
import com.spring.novice.vo.UserVo;

@Service

public class CommentService {

	@Autowired
	UserSQLMapper userSQLMapper;

	@Autowired
	BoardSQLMapper boardSQLMapper;

	@Autowired
	CommentSQLMapper commentSQLMapper;

	public void insertComment(CommentVo param) {
		commentSQLMapper.insertComment(param);
	}

	public ArrayList<HashMap<String, Object>> getCommentList(int board_no) {

		ArrayList<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();

		ArrayList<CommentVo> commentVoList = commentSQLMapper.getCommentByList(board_no);

		for (CommentVo commentVo : commentVoList) {

			int userNo = commentVo.getUser_no();
			UserVo userVo = userSQLMapper.getUserByNo(userNo);
			BoardVo boardVo = boardSQLMapper.getBoardByNo(commentVo.getBoard_no());

			int totalCommentLikeCount = commentSQLMapper.getTotalCommentLikeCount(commentVo.getComment_no());
			int myCommentLikeCount = commentSQLMapper.getMyCommentLikeCount(commentVo.getComment_no(), commentVo.getUser_no());
			int totalCommentCount = commentSQLMapper.getTotalCommentCount(board_no); 
			
			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("totalCommentLikeCount", totalCommentLikeCount);
			map.put("myCommentLikeCount", myCommentLikeCount);
			map.put("totalCommentCount", totalCommentCount);
			map.put("boardVo", boardVo);
			map.put("userVo", userVo);
			map.put("commentVo", commentVo);

			dataList.add(map);
		}

		return dataList;
	}

	public HashMap<String, Object> getComment(int comment_no) {

		CommentVo commentVo = commentSQLMapper.getCommentByNo(comment_no);

		HashMap<String, Object> map = new HashMap<String, Object>();

		int userNo = commentVo.getUser_no();
		BoardVo boardVo = boardSQLMapper.getBoardByNo(commentVo.getBoard_no());
		UserVo userVo = userSQLMapper.getUserByNo(userNo);

		map.put("commentVo", commentVo);
		map.put("userVo", userVo);
		map.put("boardVo", boardVo);

		return map;

	}

	public void updateComment(CommentVo param) {
		commentSQLMapper.updateComment(param);
	}

	public void deleteComment(int comment_no) {
		commentSQLMapper.deleteComment(comment_no);
	}

	public void doCommentLike(CommentLikeVo param) {

		int count = commentSQLMapper.getMyCommentLikeCount(param.getComment_no(), param.getUser_no());

		if (count > 0) {
			commentSQLMapper.deleteCommentLike(param);
		} else {
			commentSQLMapper.insertCommentLike(param);
		}
	}

	public int getTotalCommentLikeCount(int commentNo) {
		return commentSQLMapper.getTotalCommentLikeCount(commentNo);
	}

	public int getMyCommentLikeCount(CommentLikeVo param) {
		return commentSQLMapper.getMyCommentLikeCount(param.getComment_no(), param.getUser_no());
	}
}
