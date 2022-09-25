package com.spring.novice.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.spring.novice.commons.MailSenderThread;
import com.spring.novice.commons.MessageDigestUtil;
import com.spring.novice.user.mapper.UserSQLMapper;
import com.spring.novice.vo.MailAuthVo;
import com.spring.novice.vo.QuestionVo;
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

		// 비밀번호 해싱...
		String password = param.getUser_pw();
		password = MessageDigestUtil.getPasswordHashCode(password);
		param.setUser_pw(password);

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
		text += "<a href='http://localhost:8181/user/mailAuthProcess?authKey=" + authKey + "'>메일 인증하기</a>";

		MailSenderThread mst = new MailSenderThread(javaMailSender, param.getUser_email(), text, "회원가입을 축하 드립니다.",
				"관리자");
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

		// 비밀번호 해싱...
		String password = param.getUser_pw();
		password = MessageDigestUtil.getPasswordHashCode(password);
		param.setUser_pw(password);

		return userSQLMapper.getUserByIdAndPw(param);
	}

	public void authMail(String key) {
		userSQLMapper.updateMailAuthComplete(key);
	}

	public ArrayList<QuestionVo> getJoinQuestionList() {

		ArrayList<QuestionVo> questionList = userSQLMapper.getJoinQuestionList();

		return questionList;
	}

	public HashMap<String, Object> getUserIdByNameAndEmail(UserVo param) {
		HashMap<String, Object> userInfo = userSQLMapper.getUserIdByNameAndEmail(param.getUser_nickname(),
				param.getUser_email());

		System.out.println("" + param.getUser_nickname());
		System.out.println("" + param.getUser_email());

		return userInfo;
	}

	public HashMap<String, Object> getUserQquestionById(UserVo param) {
		HashMap<String, Object> userInfo = userSQLMapper.getUserQuestionById(param.getUser_id());

		return userInfo;
	}

	public UserVo getUserPwByfindAnswer(UserVo vo) {

		return userSQLMapper.getUserPwByfindAnswer(vo);
	}

	public void getUserUpdatePw(UserVo vo) {

		userSQLMapper.getUserUpdatePw(vo);
	}

	public HashMap<String, Object> getUserInfoByUserNo(int userNo) {
		HashMap<String, Object> userInfo = userSQLMapper.getUserInfoByUserNo(userNo);

		return userInfo;
	}

	public void deleteUserInfoByUserNo(UserVo sessionUser) {
		userSQLMapper.deleteUserInfoByUserNo(sessionUser);
	}

	public int checkUser(UserVo vo) {

		return userSQLMapper.checkUser(vo);
	}

	public void recoveryUserByInfo(UserVo vo) {

		String password = vo.getUser_pw();
		password = MessageDigestUtil.getPasswordHashCode(password);
		vo.setUser_pw(password);
		userSQLMapper.recoveryUserByInfo(vo);
	}

	public void updateUserInfoByUserNo(UserVo vo) {
		String password = vo.getUser_pw();
		password = MessageDigestUtil.getPasswordHashCode(password);
		vo.setUser_pw(password);
		userSQLMapper.updateUserInfoByUserNo(vo);
	}
}
