package com.example.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.school.dto.ResponseMessage;
import com.example.school.dto.SubjectDto;
import com.example.school.service.SubjectService;

@RestController
@RequestMapping(value = "/api/v1/subject")
public class SubjectController {

	@Autowired
	SubjectService subjectService;
	
	@PostMapping("/add")
    public ResponseMessage addSubject(@RequestBody SubjectDto subjectDTO) {
		return subjectService.addNewSubject(subjectDTO);
	}
	
	@PutMapping("/update")
    public ResponseMessage updateDetails(@RequestBody SubjectDto subjectDTO) {        
          return subjectService.updateSubject(subjectDTO);
    }
	
	@GetMapping("/details/{subjectId}")
    public SubjectDto getSubject(@PathVariable Integer subjectId) {
            return subjectService.getSubject(subjectId);
    }
	
	@GetMapping("/alldetails")
    public List<SubjectDto> getAllSubjects() {
            return subjectService.getAllSubject();
    }
	
	@DeleteMapping("/delete/{subjectId}")
	public ResponseMessage deleteSubject(@PathVariable int subjectId){
		return subjectService.deleteSubject(subjectId);
		
	}	
	
}
