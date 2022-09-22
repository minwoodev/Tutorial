package com.spring.novice.vo;

import java.util.Date;

public class BoardLikeVo {

	private int like_no;
	private int board_no;
	private int user_no;
	private Date like_write_date;

	public BoardLikeVo() {
		super();
	}

	public BoardLikeVo(int like_no, int board_no, int user_no, Date like_write_date) {
		super();
		this.like_no = like_no;
		this.board_no = board_no;
		this.user_no = user_no;
		this.like_write_date = like_write_date;
	}

	public int getLike_no() {
		return like_no;
	}

	public void setLike_no(int like_no) {
		this.like_no = like_no;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public Date getLike_write_date() {
		return like_write_date;
	}

	public void setLike_write_date(Date like_write_date) {
		this.like_write_date = like_write_date;
	}

	@Override
	public String toString() {
		return "BoardLikeVo [like_no=" + like_no + ", board_no=" + board_no + ", user_no=" + user_no
				+ ", like_write_date=" + like_write_date + "]";
	}

}
