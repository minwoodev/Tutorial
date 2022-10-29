package com.spring.novice.note.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.novice.note.service.NoteService;

@Controller
@RequestMapping("/note/*")

public class NoteController {
	
	@Autowired
	NoteService noteService;

	@RequestMapping(value="notePage", method = RequestMethod.GET)
	public String notePage(Model model, int user_no) {
		
		ArrayList<HashMap<String, Object>> dataList = noteService.getReceiveNoteList(user_no); 
		
		model.addAttribute("dataList", dataList);
		
		return "note/notePage";
		
	}
}
