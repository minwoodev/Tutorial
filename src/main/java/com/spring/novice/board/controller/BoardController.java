package com.spring.novice.board.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.novice.board.service.BoardService;
import com.spring.novice.vo.BoardVo;
import com.spring.novice.vo.UserVo;

@Controller
@RequestMapping("/board/*")

public class BoardController {

	@Autowired
	BoardService boardService;

	@RequestMapping("mainPage")
	public String mainPage(Model model) {

		ArrayList<HashMap<String, Object>> dataList = boardService.getBoardList();

		model.addAttribute("dataList", dataList);

		return "board/mainPage";
	}

	@RequestMapping("writeContentPage")
	public String writeContentPage(@ModelAttribute("boardVo") BoardVo vo) {
		return "board/writeContentPage";
	}

	@RequestMapping("writeContentProcess")
	public String writeContentProcess(@Valid BoardVo param, BindingResult result, HttpSession session) {

		if (result.hasErrors()) {
			return "board/writeContentPage";
		}

		UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");
		param.setUser_no(sessionUser.getUser_no());

		boardService.insertBoard(param);

		return "redirect:./mainPage";

	}
	
	@RequestMapping("readContentPage")
	public String readContentPage(@RequestParam(value="board_no", defaultValue="0") int no, Model model) {
		
		HashMap<String, Object> data = boardService.getBoard(no);
		
		model.addAttribute("data", data);
		
		return "board/readContentPage";
		
	}

}
