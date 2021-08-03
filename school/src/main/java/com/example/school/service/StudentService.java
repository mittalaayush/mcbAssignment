package com.example.school.service;

import com.example.school.dto.ResponseMessage;
import com.example.school.dto.StudentDTO;
import com.example.school.exception.SchoolException;

import java.util.List;

public interface StudentService {

	StudentDTO getStudent(Integer studentId) throws SchoolException;

	ResponseMessage addNewStudent(StudentDTO studentDTO);
	
	ResponseMessage updateStudent(StudentDTO studentDTO);

	ResponseMessage deleteStudent(Integer studentId);

    List<StudentDTO> getAllStudent();
}
