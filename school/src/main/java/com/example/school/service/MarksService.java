package com.example.school.service;

import java.util.List;

import com.example.school.dto.ResponseMessage;
import com.example.school.dto.MarksDto;
import com.example.school.exception.SchoolException;

public interface MarksService {

	MarksDto getMarks(Integer MarksId) throws SchoolException;

	ResponseMessage addNewMarks(MarksDto MarksDto);
	
	ResponseMessage updateMarks(MarksDto MarksDto);

	ResponseMessage deleteMarks(Integer MarksId);

    List<MarksDto> getAllMarks();

    List<MarksDto> getMarksByStudentId(int studentId);
    
    List<MarksDto> getMarksByTeacherId(int teacherId);
    
    
}
