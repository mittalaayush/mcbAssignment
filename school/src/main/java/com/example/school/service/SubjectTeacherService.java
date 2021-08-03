package com.example.school.service;

import java.util.List;

import com.example.school.dto.SubjectTeacherDto;
import com.example.school.dto.ResponseMessage;

public interface SubjectTeacherService {

	ResponseMessage addNewSubjectTeacher(SubjectTeacherDto SubjectTeacherDto);
	
	ResponseMessage updateSubjectTeacher(SubjectTeacherDto SubjectTeacherDto);

//	ResponseMessage deleteSubjectTeacher(Integer SubjectTeacherId);

    List<SubjectTeacherDto> getAllSubjectTeacher();

}
