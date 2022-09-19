package com.spring.novice.board.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.novice.board.service.BoardService;
import com.spring.novice.board.service.CommentService;
import com.spring.novice.vo.CommentVo;
import com.spring.novice.vo.UserVo;

@Controller
@RequestMapping("/board/*")

public class CommentController {

	@Autowired
	BoardService boardService;

	@Autowired
	CommentService commentService;

	@RequestMapping("wirteCommentContentPage")
	public String wirteCommentContentPage(int board_no, @ModelAttribute("commentVo") CommentVo comment, Model model) {

		HashMap<String, Object> data = boardService.getBoard(board_no);

		model.addAttribute("data", data);

		return "board/writeCommentContentPage";
	}

	@RequestMapping("writeCommentContentProcess")
	public String writeCommentContentProcess(int board_no, @Valid CommentVo param, BindingResult result,
			HttpSession session) {

		if (result.hasErrors()) {
			return "board/writeCommentContentPage";
		}

		UserVo userVo = (UserVo) session.getAttribute("sessionUser");
		param.setUser_no(userVo.getUser_no());

		commentService.insertComment(param);

		return "redirect:./readContentPage?board_no=" + param.getBoard_no();
	}

}
