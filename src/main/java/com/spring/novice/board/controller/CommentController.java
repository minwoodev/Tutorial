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
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = "wirteCommentContentPage", method = RequestMethod.POST)
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

		System.out.println("댓글 작성 프로세스 실행");
		
		return "redirect:./readContentPage?board_no=" + param.getBoard_no();
	}

	@RequestMapping(value = "updateCommentContentPage", method = RequestMethod.POST)
	public String updateCommentContentPage(int comment_no, Model model, @ModelAttribute("commentVo") CommentVo param) {

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

	@RequestMapping(value = "deleteCommentContentProcess", method = RequestMethod.POST)
	public String deleteCommentContentProcess(CommentVo param) {

		commentService.deleteComment(param.getComment_no());

		System.out.println("comment_no : " + param.getComment_no());
		System.out.println("board_no : " + param.getBoard_no());

		return "redirect:./readContentPage?board_no=" + param.getBoard_no();
	}

	@RequestMapping("commentLikeProcess")
	public String commentLikeProcess(@RequestParam(value = "board_no", defaultValue = "1") int board_no,
			CommentLikeVo param, HttpSession session) {

		// 행위자 정보는 세션에서 꼭 뽑아오자...
		UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");
		int userNo = sessionUser.getUser_no();
		param.setUser_no(userNo);

		commentService.doCommentLike(param);

		return "redirect:./readContentPage?board_no=" + board_no;
	}

}
