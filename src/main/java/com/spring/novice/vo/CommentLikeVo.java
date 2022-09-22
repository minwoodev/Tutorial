package com.spring.novice.vo;

import java.util.Date;

public class CommentLikeVo {

	private int comment_like_no;
	private int comment_no;
	private int user_no;
	private Date comment_like_date;

	public CommentLikeVo() {
		super();
	}

	public CommentLikeVo(int comment_like_no, int comment_no, int user_no, Date comment_like_date) {
		super();
		this.comment_like_no = comment_like_no;
		this.comment_no = comment_no;
		this.user_no = user_no;
		this.comment_like_date = comment_like_date;
	}

	public int getComment_like_no() {
		return comment_like_no;
	}

	public void setComment_like_no(int comment_like_no) {
		this.comment_like_no = comment_like_no;
	}

	public int getComment_no() {
		return comment_no;
	}

	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public Date getComment_like_date() {
		return comment_like_date;
	}

	public void setComment_like_date(Date comment_like_date) {
		this.comment_like_date = comment_like_date;
	}

	@Override
	public String toString() {
		return "CommentLikeVo [comment_like_no=" + comment_like_no + ", comment_no=" + comment_no + ", user_no="
				+ user_no + ", comment_like_date=" + comment_like_date + "]";
	}

}
