package com.example.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.school.dto.ResponseMessage;
import com.example.school.dto.StudentDTO;
import com.example.school.model.Student;
import com.example.school.service.StudentService;



@RestController
@RequestMapping(value = "/api/v1/student")
public class StudentController {

	@Autowired
	StudentService studService;
	
	@PostMapping("/add")
    public ResponseMessage addStudent(@RequestBody StudentDTO studentDTO) {
		return studService.addNewStudent(studentDTO);
	}
	
	@PutMapping("/update")
    public ResponseMessage updateDetails(@RequestBody StudentDTO studentDTO) {        
          return studService.updateStudent(studentDTO);
    }
	
	@GetMapping("/details/{studentId}")
    public StudentDTO getStudent(@PathVariable Integer studentId) {
            return studService.getStudent(studentId);
    }
	
	@GetMapping("/alldetails")
    public List<StudentDTO> getAllStudents() {
            return studService.getAllStudent();
    }
	
	@DeleteMapping("/delete/{studentId}")
	public ResponseMessage deleteStudent(@PathVariable int studentId){
		return studService.deleteStudent(studentId);
		
	}
	
	
	
	
	
}
