package com.example.school.service;

import java.util.List;

import com.example.school.dto.ResponseMessage;
import com.example.school.dto.SubjectDto;
import com.example.school.exception.SchoolException;


public interface SubjectService {

	SubjectDto getSubject(Integer SubjectId) throws SchoolException;

	ResponseMessage addNewSubject(SubjectDto SubjectDto);
	
	ResponseMessage updateSubject(SubjectDto SubjectDto);

	ResponseMessage deleteSubject(Integer SubjectId);

    List<SubjectDto> getAllSubject();
}
