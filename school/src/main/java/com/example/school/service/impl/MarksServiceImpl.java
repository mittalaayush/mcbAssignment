package com.example.school.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.school.dto.ResponseMessage;
import com.example.school.dao.MarksDao;
import com.example.school.dto.MarksDto;
import com.example.school.exception.SchoolException;
import com.example.school.model.Groups;
import com.example.school.model.Marks;
import com.example.school.model.Student;
import com.example.school.model.Subject;
import com.example.school.repository.MarksRepository;
import com.example.school.repository.StudentRepository;
import com.example.school.repository.SubjectRepository;
import com.example.school.service.MarksService;
import com.example.school.utils.AppConstants;

@Service
public class MarksServiceImpl implements MarksService{

	@Autowired
	MarksRepository marksRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	MarksDao marksDao;
	
	@Override
    public ResponseMessage addNewMarks(MarksDto marksDto) {
		ResponseMessage responseMsg = new ResponseMessage();
        Marks marks = new Marks();
        Optional<Subject> subject= subjectRepository.findById(marksDto.getSubjectId());
        if(!subject.isPresent()){
            throw new SchoolException(AppConstants.subjectNotFound);
        }
        Optional<Student> student= studentRepository.findById(marksDto.getStudentId());
        if(!student.isPresent()){
            throw new SchoolException(AppConstants.studentNotFound);
        }
        BeanUtils.copyProperties(marksDto, marks);
        try {
        marks.setSubjectId(subject.get());
        marks.setStudentId(student.get());
        marks = marksRepository.save(marks);
        responseMsg.setResponseMessage(""+marks.getMarkId());
        } catch(Exception e) {
        	responseMsg.setResponseMessage(e.getMessage());
        }
        return responseMsg;
    }
	
	@Override
	public MarksDto getMarks(Integer marksId) throws SchoolException {
		MarksDto markDto = new MarksDto();
		Optional<Marks> marks = marksRepository.findById(marksId);
		if(marks.isPresent()) {
			BeanUtils.copyProperties(marks.get(), markDto);
			return markDto;
		} else {
			throw new SchoolException(AppConstants.notFoundErrorMessage);
		}
		
	}

	@Override
	public ResponseMessage updateMarks(MarksDto marksDto) {
		Optional<Marks> mark = marksRepository.findById(marksDto.getMarkId());
		ResponseMessage responseMsg = new ResponseMessage();
		if(!mark.isPresent()) {
			responseMsg.setResponseMessage(AppConstants.notFoundErrorMessage);
			return responseMsg;
		} 
		Optional<Subject> subject= subjectRepository.findById(marksDto.getSubjectId());
		if(!subject.isPresent()){
			responseMsg.setResponseMessage(AppConstants.subjectNotFound);
			return responseMsg;
        }
        Optional<Student> student= studentRepository.findById(marksDto.getStudentId());
        if(!student.isPresent()){
            responseMsg.setResponseMessage(AppConstants.studentNotFound);
            return responseMsg;
        }		
		Marks marks = mark.get();
        BeanUtils.copyProperties(marksDto, marks);
        marksRepository.save(marks);            	
        responseMsg.setResponseMessage(AppConstants.successMessage);
		return responseMsg;
		
	}

	@Override
	public ResponseMessage deleteMarks(Integer marksId) throws SchoolException {
		Optional<Marks> marks = marksRepository.findById(marksId);
		ResponseMessage rM = new ResponseMessage();
        if (marks.isPresent()) {
            try{
            	marksRepository.deleteById(marksId);
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
	public List<MarksDto> getAllMarks() {
		List<MarksDto> marksList= new ArrayList<>();
		List<Marks> markssList = marksRepository.findAll();
		for(Marks marks : markssList) {
			MarksDto s = new MarksDto();
			BeanUtils.copyProperties(marks,s);
			s.setStudentId(marks.getStudentId().getStudentId());
			s.setSubjectId(marks.getSubjectId().getSubjectId());			
			marksList.add(s);
		}
		return marksList;
	}

	@Override
	public List<MarksDto> getMarksByStudentId(int studentId) {
		List<MarksDto> marksDtoList= new ArrayList<>();
		List<Marks> marksList = marksRepository.findByStudentId(studentId);
		for(Marks marks : marksList) {
			MarksDto m = new MarksDto();
			BeanUtils.copyProperties(marks,m);
			m.setSubjectId(marks.getStudentId().getStudentId());
			m.setStudentId(studentId);
			marksDtoList.add(m);
		}
		return marksDtoList;
	}

	@Override
	public List<MarksDto> getMarksByTeacherId(int teacherId) {
		return marksDao.getMarksListByTeacherId(teacherId);
	}

}
