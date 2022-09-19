package com.spring.novice.user.mapper;

import com.spring.novice.vo.UserVo;

public interface UserSQLMapper {

	/* 회원가입 테이블 */
	public void insertUser(UserVo param);
}
