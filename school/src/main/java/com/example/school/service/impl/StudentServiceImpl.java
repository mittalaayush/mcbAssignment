package com.example.school.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.school.dto.ResponseMessage;
import com.example.school.dto.StudentDTO;
import com.example.school.exception.SchoolException;
import com.example.school.model.Groups;
import com.example.school.model.Student;
import com.example.school.model.Subject;
import com.example.school.repository.GroupRepository;
import com.example.school.repository.StudentRepository;
import com.example.school.repository.SubjectRepository;
import com.example.school.service.StudentService;
import com.example.school.utils.AppConstants;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentRepository studRepo;
	
	@Autowired
	GroupRepository groupRepo;
	
	@Override
    public ResponseMessage addNewStudent(StudentDTO studentDTO) {
		ResponseMessage responseMsg = new ResponseMessage();
        Student student = new Student();
        BeanUtils.copyProperties(studentDTO, student);
        Optional<Groups> group= groupRepo.findById(studentDTO.getGroupId());
        if(!group.isPresent()){
            throw new SchoolException(AppConstants.groupNotFound);
        }
        try {
        	student.setGroupId(group.get());
        	student = studRepo.save(student);
            responseMsg.setResponseMessage(""+student.getStudentId());
        } catch(Exception e) {
        	responseMsg.setResponseMessage(e.getMessage());
        }
        
        return responseMsg;
    }
	
	@Override
	public StudentDTO getStudent(Integer studentId) throws SchoolException {
		StudentDTO stud = new StudentDTO();
		Optional<Student> student = studRepo.findById(studentId);
		if(student.isPresent()) {
			BeanUtils.copyProperties(student.get(), stud);
			stud.setGroupId(student.get().getGroupId().getGroupId());
			return stud;
		} else {
			throw new SchoolException(AppConstants.notFoundErrorMessage);
		}
		
	}

	@Override
	public ResponseMessage updateStudent(StudentDTO studentDTO) {
		Optional<Student> stud = studRepo.findById(studentDTO.getStudentId());
		ResponseMessage responseMsg = new ResponseMessage();
		if(stud.isPresent()) {
			Student student = stud.get();
            BeanUtils.copyProperties(studentDTO, student);;
            	studRepo.save(student);            	
            	responseMsg.setResponseMessage(AppConstants.successMessage);
		} else {
			responseMsg.setResponseMessage(AppConstants.notFoundErrorMessage);
		}
		return responseMsg;
		
	}

	@Override
	public ResponseMessage deleteStudent(Integer studentId) throws SchoolException {
		Optional<Student> student = studRepo.findById(studentId);
		ResponseMessage rM = new ResponseMessage();
        if (student.isPresent()) {
            try{
            	studRepo.deleteById(studentId);
            	rM.setResponseMessage(AppConstants.successMessage);
            } catch (SchoolException e) {
                rM.setResponseMessage(e.getMessage());
            } catch (Exception e) {
                rM.setResponseMessage(AppConstants.generalErrorMessage);
            }            
        }
        else{
            throw new SchoolException(AppConstants.notFoundErrorMessage);
        }
        return rM;
		
	}

	@Override
	public List<StudentDTO> getAllStudent() {
		List<StudentDTO> studentList= new ArrayList<>();
		List<Student> studentsList = studRepo.findAll();
		for(Student student : studentsList) {
			StudentDTO s = new StudentDTO();
			BeanUtils.copyProperties(student,s);
			s.setGroupId(student.getGroupId().getGroupId());
			studentList.add(s);
		}
		return studentList;
	}

}
