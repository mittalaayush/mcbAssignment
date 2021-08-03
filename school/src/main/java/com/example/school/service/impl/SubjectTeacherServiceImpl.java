package com.example.school.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.school.dto.SubjectTeacherDto;
import com.example.school.dto.ResponseMessage;
import com.example.school.exception.SchoolException;
import com.example.school.model.Groups;
import com.example.school.model.Subject;
import com.example.school.model.SubjectTeacher;
import com.example.school.model.SubjectTeacherKey;
import com.example.school.repository.GroupRepository;
import com.example.school.repository.SubjectRepository;
import com.example.school.repository.SubjectTeacherRepository;
import com.example.school.service.SubjectTeacherService;
import com.example.school.utils.AppConstants;

@Service
public class SubjectTeacherServiceImpl implements SubjectTeacherService{

	@Autowired
	SubjectTeacherRepository subjectTeacherRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	GroupRepository grpRepo;

	@Override
	public ResponseMessage updateSubjectTeacher(SubjectTeacherDto subjectTeacherDto) {
		ResponseMessage responseMsg = new ResponseMessage();
		Optional<Groups> group = grpRepo.findById(subjectTeacherDto.getGroupId());
		if(!group.isPresent()) {
			responseMsg.setResponseMessage(AppConstants.groupNotFound);
			return responseMsg;
		}
		Optional<Subject> subject = subjectRepository.findById(subjectTeacherDto.getSubjectId());
		if(!subject.isPresent()) {
			responseMsg.setResponseMessage(AppConstants.subjectNotFound);
			return responseMsg;
		}
		try {
			SubjectTeacher subjectTeacher = new SubjectTeacher();
			subjectTeacher.setTeacherId(subjectTeacherDto.getTeacherId());
			SubjectTeacherKey key = new SubjectTeacherKey();
			key.setGroup(group.get());
			key.setSubject(subject.get());
			subjectTeacher.setId(key);
            subjectTeacherRepository.save(subjectTeacher);  
            responseMsg.setResponseMessage(AppConstants.successMessage);
		} catch (SchoolException e) {
			responseMsg.setResponseMessage(e.getMessage());
        } catch (Exception e) {
        	responseMsg.setResponseMessage(AppConstants.generalErrorMessage);
        } 
		return responseMsg;
		
	}

//	@Override
//	public ResponseMessage deleteSubjectTeacher(Integer subjectTeacherId) throws SchoolException {
//		Optional<SubjectTeacher> subjectTeacher = subjectTeacherRepository.findById(subjectTeacherId);
//		ResponseMessage rM = new ResponseMessage();
//        if (subjectTeacher.isPresent()) {
//            try{
//            	subjectTeacherRepository.deleteById(subjectTeacherId);
//            	rM.setResponseMessage(AppConstants.successMessage);
//            } catch (SchoolException e) {
//                rM.setResponseMessage(e.getMessage());
//            } catch (Exception e) {
//                rM.setResponseMessage(AppConstants.generalErrorMessage);
//            }            
//        }
//        else{
//            throw new SchoolException(AppConstants.notFoundErrorMessage);
//        }
//        return rM;
//		
//	}

	@Override
	public List<SubjectTeacherDto> getAllSubjectTeacher() {
		List<SubjectTeacherDto> subjectTeacherList= new ArrayList<>();
		List<SubjectTeacher> subjectTeachersList = subjectTeacherRepository.findAll();
		for(SubjectTeacher subjectTeacher : subjectTeachersList) {
			SubjectTeacherDto s = new SubjectTeacherDto();
			BeanUtils.copyProperties(subjectTeacher,s);
			s.setGroupId(subjectTeacher.getId().getGroup().getGroupId());
			s.setSubjectId(subjectTeacher.getId().getSubject().getSubjectId());
			subjectTeacherList.add(s);
		}
		return subjectTeacherList;
	}

	@Override
	public ResponseMessage addNewSubjectTeacher(SubjectTeacherDto subjectTeacherDto) {
		ResponseMessage responseMsg = new ResponseMessage();
		Optional<Groups> group = grpRepo.findById(subjectTeacherDto.getGroupId());
		if(!group.isPresent()) {
			responseMsg.setResponseMessage(AppConstants.groupNotFound);
			return responseMsg;
		}
		Optional<Subject> subject = subjectRepository.findById(subjectTeacherDto.getSubjectId());
		if(!subject.isPresent()) {
			responseMsg.setResponseMessage(AppConstants.subjectNotFound);
			return responseMsg;
		}
		SubjectTeacher subjTeacher = new SubjectTeacher();
		
        try {
        	SubjectTeacherKey sTKey = new SubjectTeacherKey();
        	sTKey.setSubject(subject.get());
        	sTKey.setGroup(group.get());
        	subjTeacher.setTeacherId(subjectTeacherDto.getTeacherId());
        	subjTeacher.setId(sTKey);
        	subjectTeacherRepository.save(subjTeacher);
        	responseMsg.setResponseMessage(AppConstants.successMessage);
        } catch(Exception e) {
        	responseMsg.setResponseMessage(e.getMessage());
        }
        return responseMsg;
	}
	
}
