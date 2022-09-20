package com.spring.novice.vo;

import java.util.Date;

public class FileVo {

	private int file_no;
	private int board_no;
	private String org_file_name;
	private String stored_file_name;
	private int file_size;
	private Date upload_write_date;

	public FileVo() {
		super();
	}

	public FileVo(int file_no, int board_no, String org_file_name, String stored_file_name, int file_size,
			Date upload_write_date) {
		super();
		this.file_no = file_no;
		this.board_no = board_no;
		this.org_file_name = org_file_name;
		this.stored_file_name = stored_file_name;
		this.file_size = file_size;
		this.upload_write_date = upload_write_date;
	}

	public int getFile_no() {
		return file_no;
	}

	public void setFile_no(int file_no) {
		this.file_no = file_no;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getOrg_file_name() {
		return org_file_name;
	}

	public void setOrg_file_name(String org_file_name) {
		this.org_file_name = org_file_name;
	}

	public String getStored_file_name() {
		return stored_file_name;
	}

	public void setStored_file_name(String stored_file_name) {
		this.stored_file_name = stored_file_name;
	}

	public int getFile_size() {
		return file_size;
	}

	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}

	public Date getUpload_write_date() {
		return upload_write_date;
	}

	public void setUpload_write_date(Date upload_write_date) {
		this.upload_write_date = upload_write_date;
	}

	@Override
	public String toString() {
		return "FileVo [file_no=" + file_no + ", board_no=" + board_no + ", org_file_name=" + org_file_name
				+ ", stored_file_name=" + stored_file_name + ", file_size=" + file_size + ", upload_write_date="
				+ upload_write_date + "]";
	}

}
