package com.spring.novice.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.novice.commons.MailSenderThread;
import com.spring.novice.commons.MessageDigestUtil;
import com.spring.novice.user.service.UserService;
import com.spring.novice.vo.QuestionVo;
import com.spring.novice.vo.UserVo;

@Controller
@RequestMapping("/user/*")

public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	JavaMailSender javaMailSender;

	@RequestMapping("userAgree")
	public String userAgree() {
		return "user/userAgree";
	}

	@RequestMapping("joinUserPage")
	public String joinUserPage(Model model, @ModelAttribute("userVo") UserVo param,
			@RequestParam(value = "agree", defaultValue = "false") boolean agree) {
		if (!agree) {
			return "user/userAgree";
		} else {

			HashMap<String, Object> data = new HashMap<String, Object>();

			ArrayList<QuestionVo> list = userService.getJoinQuestionList();

			data.put("list", list);

			model.addAttribute("data", data);

			return "user/joinUserPage";
		}
	}

	@RequestMapping("insertUserProcess")
	public String insertUserProcess(Model model, @Valid UserVo param, BindingResult result) {

		if (result.hasErrors()) {

			HashMap<String, Object> data = new HashMap<String, Object>();

			ArrayList<QuestionVo> list = userService.getJoinQuestionList();

			data.put("list", list);

			model.addAttribute("data", data);

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
			String state = sessionUser.getUser_status();
			if (state.equals("Inactive")) {
				data.put("result", "out");
			}
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

	@RequestMapping("findUserInfoPage")
	public String findUserInfoPage() {
		return "user/findUserInfoPage";
	}

	@ResponseBody
	@RequestMapping("getUserIdByNameAndEmail")
	public HashMap<String, Object> getUserIdByNameAndEmail(UserVo vo) {

		HashMap<String, Object> data = new HashMap<String, Object>();

		HashMap<String, Object> userInfo = userService.getUserIdByNameAndEmail(vo);

		if (userInfo == null) {
			data.put("result", "fail");
		} else {
			data.put("result", "success");
			data.put("userInfo", userInfo);
		}

		return data;
	}

	@ResponseBody
	@RequestMapping("getUserQuestionById")
	public HashMap<String, Object> getUserQuestionById(UserVo vo) {

		HashMap<String, Object> data = new HashMap<String, Object>();

		HashMap<String, Object> userInfo = userService.getUserQquestionById(vo);

		if (userInfo == null) {
			data.put("result", "fail");
		} else {
			data.put("result", "success");
			data.put("userInfo", userInfo);
		}

		System.out.println(data.get("userInfo").toString());

		return data;
	}

	@ResponseBody
	@RequestMapping("getUserPwByfindAnswer")
	public HashMap<String, Object> getUserPwByfindAnswer(UserVo param) {

		HashMap<String, Object> data = new HashMap<String, Object>();
		UserVo userInfo = userService.getUserPwByfindAnswer(param);
		System.out.println("@@" + param.getUser_id());
		System.out.println("@@" + param.getUser_findAnswer());

		if (userInfo == null) {
			data.put("result", "fail");
		} else {
			String pw = userInfo.getUser_pw();
			String email = userInfo.getUser_email();
			String name = userInfo.getUser_nickname();
			data.put("result", "success");

			Random random = new Random();
			int checkNum = random.nextInt(888888) + 111111;

			String setFrom = "관리자";
			String toMail = email;
			String title = "임시비밀번호 발급 메일입니다.";
			String content = "전자소송 홈페이지를 방문해주셔서 감사합니다." + "<br><br>" + name + "님의 임시비밀번호는 " + checkNum + "입니다." + "<br>"
					+ "로그인 후 비밀번호를 반드시 변경해주세요.";
			String num = "";

			num = Integer.toString(checkNum);
			param.setUser_pw(num);
			String password = param.getUser_pw();
			password = MessageDigestUtil.getPasswordHashCode(password);
			param.setUser_pw(password);
			userService.getUserUpdatePw(param);

			MailSenderThread mst = new MailSenderThread(javaMailSender, toMail, content, title, setFrom);
			mst.start();
		}

		return data;
	}

	@RequestMapping("userInfoPage")
	public String userInfoPage() {
		return "user/userInfoPage";
	}

	@ResponseBody
	@RequestMapping("checkSession")
	public HashMap<String, Object> checkSession(HttpSession session) {

		HashMap<String, Object> data = new HashMap<String, Object>();

		UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");

		if (sessionUser == null) {
			data.put("result", "fail");
		} else {
			data.put("result", "success");
			data.put("sessionUser", sessionUser);
		}

		return data;
	}

	@ResponseBody
	@RequestMapping("getUserInfoByUserNo")
	public HashMap<String, Object> getUserInfoByUserNo(int userNo) {

		HashMap<String, Object> data = new HashMap<String, Object>();

		HashMap<String, Object> userData = userService.getUserInfoByUserNo(userNo);

		data.put("userData", userData);

		return data;
	}

	@ResponseBody
	@RequestMapping("deleteUserInfoByUserNo")
	public HashMap<String, Object> deleteUserInfoByUserNo(UserVo vo, HttpSession session) {

		HashMap<String, Object> data = new HashMap<String, Object>();

		UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");
		String password = vo.getUser_pw();
		password = MessageDigestUtil.getPasswordHashCode(password);
		vo.setUser_pw(password);

		if (sessionUser.getUser_pw().equals(vo.getUser_pw())) {

			userService.deleteUserInfoByUserNo(sessionUser);
			session.invalidate();
			data.put("result", "success");
		} else {
			data.put("result", "fail");
		}
		return data;
	}

	@RequestMapping("userRecoveryPage")
	public String userRecoveryPage() {

		return "/user/userRecoveryPage";
	}
	
	@ResponseBody
	@RequestMapping("recoveryUserByInfo")
	public HashMap<String, Object> recoveryUserByInfo (UserVo vo){
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		
		int checkUser = userService.checkUser(vo);
        
        if ( checkUser == 1 ) {
        	userService.recoveryUserByInfo(vo);
			data.put("result", "success");
		} else {
			data.put("result", "fail");
		}
		
		return data;
	}

}