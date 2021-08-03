package com.example.school.service;

import java.util.List;

import com.example.school.dto.GroupDto;
import com.example.school.dto.ResponseMessage;
import com.example.school.exception.SchoolException;

public interface GroupService {

		GroupDto getGroup(Integer GroupId) throws SchoolException;

		ResponseMessage addNewGroup(GroupDto GroupDto);
		
		ResponseMessage updateGroup(GroupDto GroupDto);

		ResponseMessage deleteGroup(Integer GroupId);

	    List<GroupDto> getAllGroup();

}
