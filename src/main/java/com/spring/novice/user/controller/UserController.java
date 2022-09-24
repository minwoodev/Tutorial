package com.spring.novice.user.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@ResponseBody
	@RequestMapping("checkId")
	public HashMap<String, Object> checkId(String user_id) {

		HashMap<String, Object> data = new HashMap<String, Object>();

		boolean check = userService.isSelectById(user_id);

		if (check) {
			data.put("result", "fail");
		} else {
			data.put("result", "success");
		}

		return data;
	}

	@ResponseBody
	@RequestMapping("checkNickName")
	public HashMap<String, Object> checkNickName(String user_nickname) {

		HashMap<String, Object> data = new HashMap<String, Object>();

		boolean check = userService.isSelectByNickName(user_nickname);

		if (check) {
			data.put("result", "fail");
		} else {
			data.put("result", "success");
		}

		return data;
	}

	@ResponseBody
	@RequestMapping("checkPhoneNumber")
	public HashMap<String, Object> checkPhoneNumber(String user_phone) {

		HashMap<String, Object> data = new HashMap<String, Object>();

		boolean check = userService.isSelectByPhone(user_phone);

		if (check) {
			data.put("result", "fail");
		} else {
			data.put("result", "success");
		}

		return data;
	}

	@ResponseBody
	@RequestMapping("checkEmail")
	public HashMap<String, Object> checkEmail(String user_email) {

		HashMap<String, Object> data = new HashMap<String, Object>();

		boolean check = userService.isSelctByEmail(user_email);

		if (check) {
			data.put("result", "fail");
		} else {
			data.put("result", "success");
		}

		return data;
	}

	@RequestMapping("loginPage")
	public String loginPage() {
		return "user/loginPage";
	}

	@ResponseBody
	@RequestMapping("userLoginParocess")
	public HashMap<String, Object> userLoginParocess(UserVo param, HttpSession session) {

		HashMap<String, Object> data = new HashMap<String, Object>();

		UserVo sessionUser = userService.selectByIdAndPw(param);

		if (sessionUser != null) {
			session.setAttribute("sessionUser", sessionUser);
			data.put("result", "success");
		} else {
			data.put("result", "fail");
		}

		return data;
	}

	@RequestMapping("logoutUserPorcess")
	public String logoutUserPorcess(HttpServletRequest request) {

		request.getSession().invalidate();
		request.getSession(true);

		return "redirect:../board/mainPage";
	}

	@RequestMapping("mailAuthProcess")
	public String mailAuthProcess(String authKey) {

		userService.authMail(authKey);

		return "user/authMailProcessComplete";
	}

}