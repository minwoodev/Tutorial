package com.spring.novice.vo;

public class QuestionVo {

	private int question_no;
	private String question_content;

	public QuestionVo() {
		super();
	}

	public QuestionVo(int question_no, String question_content) {
		super();
		this.question_no = question_no;
		this.question_content = question_content;
	}

	public int getQuestion_no() {
		return question_no;
	}

	public void setQuestion_no(int question_no) {
		this.question_no = question_no;
	}

	public String getQuestion_content() {
		return question_content;
	}

	public void setQuestion_content(String question_content) {
		this.question_content = question_content;
	}

	@Override
	public String toString() {
		return "QuestionVo [question_no=" + question_no + ", question_content=" + question_content + "]";
	}
}
