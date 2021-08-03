package com.example.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.school.dto.ResponseMessage;
import com.example.school.dto.GroupDto;
import com.example.school.service.GroupService;

@RestController
@RequestMapping(value = "/api/v1/group")
public class GroupController {

	@Autowired
	GroupService groupService;
	
	@PostMapping("/add")
    public ResponseMessage addGroups(@RequestBody GroupDto groupDto) {
		return groupService.addNewGroup(groupDto);
	}
	
	@PutMapping("/update")
    public ResponseMessage updateDetails(@RequestBody GroupDto groupDto) {        
          return groupService.updateGroup(groupDto);
    }
	
	@GetMapping("/details/{groupId}")
    public GroupDto getGroups(@PathVariable Integer groupId) {
            return groupService.getGroup(groupId);
    }
	
	@GetMapping("/alldetails")
    public List<GroupDto> getAllGroupss() {
            return groupService.getAllGroup();
    }
	
	@DeleteMapping("/delete/{groupId}")
	public ResponseMessage deleteGroups(@PathVariable int groupId){
		return groupService.deleteGroup(groupId);
		
	}	
	
}
