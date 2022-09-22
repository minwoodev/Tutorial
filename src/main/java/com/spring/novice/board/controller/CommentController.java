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
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.novice.board.service.BoardService;
import com.spring.novice.board.service.CommentService;
import com.spring.novice.vo.CommentLikeVo;
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
	
	@RequestMapping("updateCommentContentPage")
	public String updateCommentContentPage(@ModelAttribute("commentVo") CommentVo param, int comment_no, Model model) {

		HashMap<String, Object> data = commentService.getComment(comment_no);

		model.addAttribute("data", data);

		return "board/updateCommentContentPage";

	}

	@RequestMapping("updateCommentContentProcess")
	public String updateCommentContentProcess(@Valid CommentVo param, BindingResult result) {
		if (result.hasErrors()) {
			return "board/updateCommentContentPage";
		}
		
		commentService.updateComment(param);
		
		return "redirect:./readContentPage??board_no=" + param.getBoard_no();
	}
	
	@RequestMapping("deleteCommentContentProcess")
	public String deleteCommentContentProcess(int comment_no, int board_no) {
		
		commentService.deleteComment(comment_no);
		
		return "redirect:./readContentPage??board_no=" + board_no;
	}
	
	@RequestMapping("commentLikeProcess")
	public String commentLikeProcess(@RequestParam(value="board_no", defaultValue = "1") int board_no, CommentLikeVo param, HttpSession session) {

		// 행위자 정보는 세션에서 꼭 뽑아오자...
		UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");
		int userNo = sessionUser.getUser_no();
		param.setUser_no(userNo);

		commentService.doCommentLike(param);

		return "redirect:./readContentPage?board_no=" + board_no;
	}

}
