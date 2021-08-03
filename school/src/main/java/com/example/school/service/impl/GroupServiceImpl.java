package com.example.school.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.school.dto.GroupDto;
import com.example.school.dto.ResponseMessage;
import com.example.school.exception.SchoolException;
import com.example.school.model.Groups;
import com.example.school.repository.GroupRepository;
import com.example.school.service.GroupService;
import com.example.school.utils.AppConstants;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	GroupRepository groupRepository;
	
	@Override
    public ResponseMessage addNewGroup(GroupDto groupDto) {
		ResponseMessage responseMsg = new ResponseMessage();
        Groups group = new Groups();
        BeanUtils.copyProperties(groupDto, group);
        try {
        group = groupRepository.save(group);
        responseMsg.setResponseMessage(""+group.getGroupId());
        } catch(Exception e) {
        	responseMsg.setResponseMessage(e.getMessage());
        }
        return responseMsg;
    }
	
	@Override
	public GroupDto getGroup(Integer groupId) throws SchoolException {
		GroupDto markDto = new GroupDto();
		Optional<Groups> group = groupRepository.findById(groupId);
		if(group.isPresent()) {
			BeanUtils.copyProperties(group.get(), markDto);
			return markDto;
		} else {
			throw new SchoolException(AppConstants.notFoundErrorMessage);
		}
		
	}

	@Override
	public ResponseMessage updateGroup(GroupDto groupDto) {
		Optional<Groups> groupFind = groupRepository.findById(groupDto.getGroupId());
		ResponseMessage responseMsg = new ResponseMessage();
		if(groupFind.isPresent()) {
			Groups group = groupFind.get();
            BeanUtils.copyProperties(groupDto, group);
            groupRepository.save(group);            	
            	responseMsg.setResponseMessage(AppConstants.successMessage);
		} else {
			responseMsg.setResponseMessage(AppConstants.notFoundErrorMessage);
		}
		return responseMsg;
		
	}

	@Override
	public ResponseMessage deleteGroup(Integer groupId) throws SchoolException {
		Optional<Groups> group = groupRepository.findById(groupId);
		ResponseMessage rM = new ResponseMessage();
        if (group.isPresent()) {
            try{
            	groupRepository.deleteById(groupId);
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
	public List<GroupDto> getAllGroup() {
		List<GroupDto> groupList= new ArrayList<>();
		List<Groups> groupsList = groupRepository.findAll();
		for(Groups group : groupsList) {
			GroupDto s = new GroupDto();
			BeanUtils.copyProperties(group,s);
			groupList.add(s);
		}
		return groupList;
	}
	
}
