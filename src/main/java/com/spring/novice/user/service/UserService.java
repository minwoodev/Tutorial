package com.spring.novice.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.novice.user.mapper.UserSQLMapper;
import com.spring.novice.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	UserSQLMapper userSQLMapper;
	
	public void insertUser(UserVo param) {
		userSQLMapper.insertUser(param);
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
}
