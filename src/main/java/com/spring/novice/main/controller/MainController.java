package com.spring.novice.main.controller;

import java.net.URLEncoder;
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
	public String mainPage(Model model, String category, String keyword,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {

		ArrayList<HashMap<String, Object>> dataList = boardService.getBoardList(category, keyword, pageNum);
		int count = boardService.getBoardCount(category, keyword);

		int totalPageCount = (int) Math.ceil(count / 10.0);

		// 1 2 3 4 5 , 6 7 8 9 10
		int startPage = ((pageNum - 1) / 5) * 5 + 1;
		int endPage = ((pageNum - 1) / 5 + 1) * (5);
		if (endPage > totalPageCount) {
			endPage = totalPageCount;
		}

		// 페이징 링크 검색 추가 옵션...
		String additionalParam = "";

		if (category != null) {
			additionalParam += "&category=" + category;
		}

		if (keyword != null) {
			// URL encoding -> 영어 숫자 특수 문자 아닌 값이 존재 할때...
			try {
				keyword = URLEncoder.encode(keyword, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}

			additionalParam += "&keyword=" + keyword;
		}

		model.addAttribute("additionalParam", additionalParam);
		model.addAttribute("count", count);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("dataList", dataList);

		return "board/mainPage";
	}
}
