package com.example.school.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.school.dto.ResponseMessage;
import com.example.school.dto.SubjectDto;
import com.example.school.exception.SchoolException;
import com.example.school.model.Subject;
import com.example.school.repository.SubjectRepository;
import com.example.school.service.SubjectService;
import com.example.school.utils.AppConstants;

@Service
public class SubjectServiceImpl implements SubjectService{

	@Autowired
	SubjectRepository subjectRepo;
	
	@Override
    public ResponseMessage addNewSubject(SubjectDto subjectDto) {
		ResponseMessage responseMsg = new ResponseMessage();
        Subject subject = new Subject();
        BeanUtils.copyProperties(subjectDto, subject);
        try {
        subject = subjectRepo.save(subject); 
        responseMsg.setResponseMessage(""+subject.getSubjectId());
        } catch(Exception e) {
        	responseMsg.setResponseMessage(e.getMessage());
        }
        return responseMsg;
    }
	
	@Override
	public SubjectDto getSubject(Integer subjectId) throws SchoolException {
		SubjectDto subjectDto = new SubjectDto();
		Optional<Subject> subject = subjectRepo.findById(subjectId);
		if(subject.isPresent()) {
			BeanUtils.copyProperties(subject.get(), subjectDto);
			return subjectDto;
		} else {
			throw new SchoolException(AppConstants.notFoundErrorMessage);
		}
		
	}

	@Override
	public ResponseMessage updateSubject(SubjectDto subjectDto) {
		Optional<Subject> subject = subjectRepo.findById(subjectDto.getSubjectId());
		ResponseMessage responseMsg = new ResponseMessage();
		if(subject.isPresent()) {
			Subject sub = subject.get();
            BeanUtils.copyProperties(subjectDto, sub);
            	subjectRepo.save(sub);       
            	
            	responseMsg.setResponseMessage(AppConstants.successMessage);
		} else {
			responseMsg.setResponseMessage(AppConstants.notFoundErrorMessage);
		}
		return responseMsg;
		
	}

	@Override
	public ResponseMessage deleteSubject(Integer subjectId) throws SchoolException {
		Optional<Subject> subject = subjectRepo.findById(subjectId);
		ResponseMessage rM = new ResponseMessage();
        if (subject.isPresent()) {
            try{
            	subjectRepo.deleteById(subjectId);
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
	public List<SubjectDto> getAllSubject() {
		List<SubjectDto> subjectList= new ArrayList<>();
		List<Subject> subjectsList = subjectRepo.findAll();
		for(Subject subject : subjectsList) {
			SubjectDto s = new SubjectDto();
			BeanUtils.copyProperties(subject,s);
			subjectList.add(s);
		}
		return subjectList;
	}

}
