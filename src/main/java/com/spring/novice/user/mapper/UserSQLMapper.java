package com.spring.novice.user.mapper;

import com.spring.novice.vo.UserVo;

public interface UserSQLMapper {

	/* 회원가입 테이블 */
	public void insertUser(UserVo param);
	
	/* 아이디 중복확인 쿼리 */
	public int selectById(String user_id);

	/* 닉네임 중복확인 쿼리 */
	public int selectByNickName(String user_nickname);

	/* 휴대폰번호 중복확인 쿼리 */
	public int selectByPhoneNumber(String user_phone);

	/* 이메일 중복확인 쿼리 */
	public int selectByEmail(String user_email);
	
	/* 로그인 쿼리*/
	public UserVo getUserByIdAndPw(UserVo param);
	
	public UserVo getUserByNo(int no);
	
}
