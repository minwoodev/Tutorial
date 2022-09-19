package com.spring.novice.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.novice.user.service.UserService;
import com.spring.novice.vo.UserVo;

@Controller
@RequestMapping("/user/*")

public class UserController {
	
	@Autowired
	UserService userService;

	@RequestMapping("userAgree")
	public String userAgree() {
		return "user/userAgree";
	}
	
	@RequestMapping("joinUserPage")
	public String joinUserPage(@ModelAttribute("userVo") UserVo param,
			@RequestParam(value = "agree", defaultValue = "false") boolean agree) {
		if (!agree) {
			return "user/userAgree";
		} else {
			return "user/joinUserPage";
		}
	}
	
	@RequestMapping("insertUserProcess")
	public String insertUserProcess(@Valid UserVo param, BindingResult result) {

		if (result.hasErrors()) {
			return "/user/joinUserPage";
		}

		userService.insertUser(param);

		return "user/joinUserCompletPage";
	}
}