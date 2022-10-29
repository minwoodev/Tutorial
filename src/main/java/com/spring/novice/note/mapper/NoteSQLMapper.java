package com.spring.novice.note.mapper;

import java.util.ArrayList;

import com.spring.novice.vo.NoteVo;

public interface NoteSQLMapper {

	public ArrayList<NoteVo> getNoteByList(int user_no);
}
