package com.spring.novice.user.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.spring.novice.commons.MailSenderThread;
import com.spring.novice.user.mapper.UserSQLMapper;
import com.spring.novice.vo.MailAuthVo;
import com.spring.novice.vo.UserVo;

@Service
public class UserService {

	@Autowired
	UserSQLMapper userSQLMapper;

	@Autowired
	private JavaMailSender javaMailSender;

	public void insertUser(UserVo param) {

		int userNo = userSQLMapper.createUserPk();
		
		param.setUser_no(userNo);

		userSQLMapper.insertUser(param);		
		
		// 메일 인증....관련...
		UUID uuid = UUID.randomUUID();
		String authKey = uuid.toString();

		MailAuthVo mailAuthVo = new MailAuthVo();
		mailAuthVo.setMailauth_key(authKey);
		mailAuthVo.setUser_no(param.getUser_no());

		userSQLMapper.insertMailAuth(mailAuthVo);

		// 키를 메일로 보낸다....
		String text = "";
		text += "회원가입을 축하드립니다. 아래 링크를 클릭하셔서 메일 인증 완료를 부탁드립니다.<br>";
		text += "<a href='http://localhost:8181/user/mailAuthProcess?authKey=" + authKey
				+ "'>메일 인증하기</a>";

		MailSenderThread mst = new MailSenderThread(javaMailSender, param.getUser_email(), text);
		mst.start(); // 쓰레드 실행.... 클래스의 run 메소드가 쓰레드로 실행된다...


		
		
	}

	public boolean isSelectById(String user_id) {
		return userSQLMapper.selectById(user_id) > 0;
	}

	public boolean isSelectByNickName(String user_nickname) {
		return userSQLMapper.selectByNickName(user_nickname) > 0;
	}

	public boolean isSelectByPhone(String user_phone) {
		return userSQLMapper.selectByPhoneNumber(user_phone) > 0;
	}

	public boolean isSelctByEmail(String user_email) {
		return userSQLMapper.selectByEmail(user_email) > 0;
	}

	public UserVo selectByIdAndPw(UserVo param) {
		return userSQLMapper.getUserByIdAndPw(param);
	}
	
	public void authMail(String key) {
		userSQLMapper.updateMailAuthComplete(key);
	}
}
