package com.spring.novice.main.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.novice.board.service.BoardService;

@Controller
public class MainController {

	@Autowired
	BoardService boardService;
	
	@RequestMapping(value = "/")
	public String mainPage(Model model) {	
		
		ArrayList<HashMap<String, Object>> dataList = boardService.getBoardList();
		
		model.addAttribute("dataList", dataList);
		
		return "board/mainPage";
	}
}
