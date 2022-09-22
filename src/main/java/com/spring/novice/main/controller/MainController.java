package com.spring.novice.main.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.novice.board.service.BoardService;

@Controller
public class MainController {

	@Autowired
	BoardService boardService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String mainPage(Model model, String category, String keyword, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {	
		
		ArrayList<HashMap<String, Object>> dataList = boardService.getBoardList(category, keyword, pageNum);
		
		model.addAttribute("dataList", dataList);
		
		return "board/mainPage";
	}
}
