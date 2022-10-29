package com.spring.novice.note.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.novice.note.mapper.NoteSQLMapper;
import com.spring.novice.user.mapper.UserSQLMapper;
import com.spring.novice.vo.NoteVo;
import com.spring.novice.vo.UserVo;

@Service

public class NoteService {

	@Autowired
	NoteSQLMapper noteSQLMapper;
	
	@Autowired
	UserSQLMapper userSQLMapper;
	
	public ArrayList<HashMap<String, Object>> getReceiveNoteList(int user_no) {
		
		ArrayList<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
		
		ArrayList<NoteVo> noteVoList = noteSQLMapper.getNoteByList(user_no);
		
		for (NoteVo noteVo : noteVoList) {
			
			UserVo userVo = userSQLMapper.getUserByNo(user_no);
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			map.put("noteVo", noteVo);
			map.put("userVo", userVo);
			
			dataList.add(map);
		}
		
		return dataList;
	}
}
