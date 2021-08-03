package com.example.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.school.dto.ResponseMessage;
import com.example.school.dto.MarksDto;
import com.example.school.service.MarksService;

@RestController
@RequestMapping(value = "/api/v1/marks")
public class MarksController {

	@Autowired
	MarksService markService;
	
	@PostMapping("/add")
    public ResponseMessage addMarks(@RequestBody MarksDto marksDto) {
		return markService.addNewMarks(marksDto);
	}
	
	@PutMapping("/update")
    public ResponseMessage updateDetails(@RequestBody MarksDto marksDto) {        
          return markService.updateMarks(marksDto);
    }
	
	@GetMapping("/details/{markId}")
    public MarksDto getMarks(@PathVariable Integer markId) {
            return markService.getMarks(markId);
    }
	
	@GetMapping("/student/{studentId}")
    public List<MarksDto> getAllMarksOfStudent(@PathVariable int studentId) {
            return markService.getMarksByStudentId(studentId);
    }
	
	@GetMapping("/teacher/{teacherId}")
    public List<MarksDto> getMarksByTeacherId(@PathVariable int teacherId) {
            return markService.getMarksByTeacherId(teacherId);
    }
	
	@DeleteMapping("/delete/{markId}")
	public ResponseMessage deleteMarks(@PathVariable int markId){
		return markService.deleteMarks(markId);
		
	}	
	
	@GetMapping("/alldetails")
    public List<MarksDto> getMarksOfStudeny() {
            return markService.getAllMarks();
    }
	
}
