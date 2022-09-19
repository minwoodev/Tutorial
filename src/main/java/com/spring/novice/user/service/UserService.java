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

}
