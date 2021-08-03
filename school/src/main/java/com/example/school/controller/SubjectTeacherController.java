package com.example.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.school.dto.ResponseMessage;
import com.example.school.dto.SubjectTeacherDto;
import com.example.school.service.SubjectTeacherService;

@RestController
@RequestMapping(value = "/api/v1/subjectteacher")
public class SubjectTeacherController {

	@Autowired
	SubjectTeacherService subjectTeacherService;
	
	@PostMapping("/add")
    public ResponseMessage addSubject(@RequestBody SubjectTeacherDto subjectTeacherDTO) {
		return subjectTeacherService.addNewSubjectTeacher(subjectTeacherDTO);
	}
	
	@PutMapping("/update")
    public ResponseMessage updateDetails(@RequestBody SubjectTeacherDto subjectTeacherDTO) {        
          return subjectTeacherService.updateSubjectTeacher(subjectTeacherDTO);
    }
	
//	@GetMapping("/details/{subjectId}")
//    public SubjectDto getSubject(@PathVariable Integer subjectId) {
//            return subjectTeacherService.getSubjectTeacher((subjectId);
//    }
	
	@GetMapping("/alldetails")
    public List<SubjectTeacherDto> getAllSubjectTeachers() {
            return subjectTeacherService.getAllSubjectTeacher();
    }
	
//	@DeleteMapping("/delete-subject-teacher/{subjectTeacherId}")
//	public ResponseMessage deleteSubjectTeacher(@PathVariable int subjectTeacherId){
//		return subjectTeacherService.deleteSubjectTeacher(subjectTeacherId);
//		
//	}	
}
