package com.spring.novice.commons;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailSenderThread extends Thread {

	private JavaMailSender javaMailSender;
	private String to;
	private String message;
	private String title;
	private String from;
	
	public MailSenderThread(JavaMailSender javaMailSender , String to , String message, String title, String from) {
		this.javaMailSender = javaMailSender;
		this.to = to;
		this.message = message;
		this.title = title;
		this.from = from;
	}
	
	
	public void run() {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
			
			mimeMessageHelper.setSubject(title);
			mimeMessageHelper.setText(message, true);
			
			mimeMessageHelper.setFrom("qwer", from);
			mimeMessageHelper.setTo(to);
			
			javaMailSender.send(mimeMessage);
			
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
}
