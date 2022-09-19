package com.spring.novice.vo;

import java.util.Date;

public class ReadPageVo {

	private int read_page_no;
	private int board_no;
	private String client_ip;
	private Date read_write_date;

	public ReadPageVo() {
		super();
	}

	public ReadPageVo(int read_page_no, int board_no, String client_ip, Date read_write_date) {
		super();
		this.read_page_no = read_page_no;
		this.board_no = board_no;
		this.client_ip = client_ip;
		this.read_write_date = read_write_date;
	}

	public int getRead_page_no() {
		return read_page_no;
	}

	public void setRead_page_no(int read_page_no) {
		this.read_page_no = read_page_no;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getClient_ip() {
		return client_ip;
	}

	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}

	public Date getRead_write_date() {
		return read_write_date;
	}

	public void setRead_write_date(Date read_write_date) {
		this.read_write_date = read_write_date;
	}

	@Override
	public String toString() {
		return "ReadPageVo [read_page_no=" + read_page_no + ", board_no=" + board_no + ", client_ip=" + client_ip
				+ ", read_write_date=" + read_write_date + "]";
	}

}
