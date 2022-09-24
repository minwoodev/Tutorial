package com.spring.novice.vo;

import java.util.Date;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

public class UserVo {

	private int user_no;

	private int question_no;

	@NotNull
	@Pattern(regexp = "^(?=.*[a-zA-z])(?=.*[0-9])(?!.*[^a-zA-z0-9]).{5,10}")
	private String user_id;

	@NotNull
	@Pattern(regexp = "^(?=.*[a-zA-z])(?=.*[0-9])(?!.*[^a-zA-z0-9]).{5,20}")
	private String user_pw;

	@NotNull
	@Length(min = 1, max = 15)
	@Pattern(regexp = "^[가-힣]*$")
	private String user_nickname;

	@NotNull
	private String user_gender;

	@NotNull
	@Length(max = 13)
	@Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$")
	private String user_phone;

	@NotNull
	@Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")
	private String user_email;

	@NotNull
	@Length(min = 1, max = 100)
	private String user_findAnswer;

	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date user_birth;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date user_join_date;
	private String user_status;

	public UserVo() {
		super();
	}

	public UserVo(int user_no, int question_no, String user_id, String user_pw, String user_nickname,
			String user_gender, String user_phone, String user_email, String user_findAnswer, Date user_birth,
			Date user_join_date, String user_status) {
		super();
		this.user_no = user_no;
		this.question_no = question_no;
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_nickname = user_nickname;
		this.user_gender = user_gender;
		this.user_phone = user_phone;
		this.user_email = user_email;
		this.user_findAnswer = user_findAnswer;
		this.user_birth = user_birth;
		this.user_join_date = user_join_date;
		this.user_status = user_status;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public int getQuestion_no() {
		return question_no;
	}

	public void setQuestion_no(int question_no) {
		this.question_no = question_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public String getUser_gender() {
		return user_gender;
	}

	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_findAnswer() {
		return user_findAnswer;
	}

	public void setUser_findAnswer(String user_findAnswer) {
		this.user_findAnswer = user_findAnswer;
	}

	public Date getUser_birth() {
		return user_birth;
	}

	public void setUser_birth(Date user_birth) {
		this.user_birth = user_birth;
	}

	public Date getUser_join_date() {
		return user_join_date;
	}

	public void setUser_join_date(Date user_join_date) {
		this.user_join_date = user_join_date;
	}

	public String getUser_status() {
		return user_status;
	}

	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}

	@Override
	public String toString() {
		return "UserVo [user_no=" + user_no + ", question_no=" + question_no + ", user_id=" + user_id + ", user_pw="
				+ user_pw + ", user_nickname=" + user_nickname + ", user_gender=" + user_gender + ", user_phone="
				+ user_phone + ", user_email=" + user_email + ", user_findAnswer=" + user_findAnswer + ", user_birth="
				+ user_birth + ", user_join_date=" + user_join_date + ", user_status=" + user_status + "]";
	}

}
