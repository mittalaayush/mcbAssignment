package com.example.school.dao;

import java.util.List;

import com.example.school.dto.MarksDto;
import com.example.school.exception.SchoolException;

public interface MarksDao {
	
	String getMarksByTeacherIdQuery = "Select m from Marks m  "
			+ "where m.subjectId.subjectId in (select st.id.subject.subjectId from SubjectTeacher st where st.teacherId=?1)";
	
	List<MarksDto> getMarksListByTeacherId(Integer teacherId) ;
	
}
