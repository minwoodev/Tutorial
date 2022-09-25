package com.spring.novice.user.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Param;

import com.spring.novice.vo.MailAuthVo;
import com.spring.novice.vo.QuestionVo;
import com.spring.novice.vo.UserVo;

public interface UserSQLMapper {

	public int createUserPk();
	
	/* 회원가입 테이블 */
	public void insertUser(UserVo param);
	
	/* 아이디 중복확인 쿼리 */
	public int selectById(String user_id);

	/* 닉네임 중복확인 쿼리 */
	public int selectByNickName(String user_nickname);

	/* 휴대폰번호 중복확인 쿼리 */
	public int selectByPhoneNumber(String user_phone);

	/* 이메일 중복확인 쿼리 */
	public int selectByEmail(String user_eamil);
	
	/* 로그인 쿼리*/
	public UserVo getUserByIdAndPw(UserVo param);
	
	public UserVo getUserByNo(int no);
	
	//메일 인증 T
	public void insertMailAuth(MailAuthVo vo);
	public void updateMailAuthComplete(String authKey);
	
	/* 비밀번호 찾기 힌트 */
	public ArrayList<QuestionVo> getJoinQuestionList();
	
	/* 아이디 찾기 */
	public HashMap<String, Object> getUserIdByNameAndEmail(@Param("user_nickname") String user_nickname, @Param("user_email") String user_email);
	
	/* 비밀번호 찾기 질문 */
	public HashMap<String, Object> getUserQuestionById(@Param("user_id") String user_id);
	
	/* 비밀번호 찾기 질문 답변 */	
	public UserVo getUserPwByfindAnswer(UserVo param);
	
	/* 임시 비밀번호 변경 */
	public void getUserUpdatePw(UserVo param);
	
	public HashMap<String, Object> getUserInfoByUserNo(@Param("user_no") int user_no);

	/* 회원탈퇴 */
	public void deleteUserInfoByUserNo(UserVo sessionUser);
	
	/* 유저 체크 */
	public int checkUser(UserVo vo);
	
	/* 계정 활성화 */
	public void recoveryUserByInfo(UserVo vo);
	
	/* 계정 정보 수정*/
	public void updateUserInfoByUserNo(UserVo vo);
}
