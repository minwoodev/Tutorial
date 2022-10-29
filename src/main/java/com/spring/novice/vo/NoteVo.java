package com.spring.novice.vo;

import java.util.Date;

public class NoteVo {

	private int note_no;
	private int user_no;
	private String note_nickname;
	private String note_content;
	private Date note_writedate;	
	
	public NoteVo() {
		super();
	}

	public NoteVo(int note_no, int user_no, String note_nickname, String note_content, Date note_writedate) {
		super();
		this.note_no = note_no;
		this.user_no = user_no;
		this.note_nickname = note_nickname;
		this.note_content = note_content;
		this.note_writedate = note_writedate;
	}

	public int getNote_no() {
		return note_no;
	}

	public void setNote_no(int note_no) {
		this.note_no = note_no;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getNote_nickname() {
		return note_nickname;
	}

	public void setNote_nickname(String note_nickname) {
		this.note_nickname = note_nickname;
	}

	public String getNote_content() {
		return note_content;
	}

	public void setNote_content(String note_content) {
		this.note_content = note_content;
	}

	public Date getNote_writedate() {
		return note_writedate;
	}

	public void setNote_writedate(Date note_writedate) {
		this.note_writedate = note_writedate;
	}
	
	
}
