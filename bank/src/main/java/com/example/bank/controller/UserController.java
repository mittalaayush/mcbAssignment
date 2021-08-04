package com.example.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.dto.LoginCredentials;
import com.example.bank.dto.UserDto;
import com.example.bank.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
    private UserService userService;

	@PostMapping("/login")
    public UserDto addStudent(@RequestBody LoginCredentials userCred) {
		return userService.login(userCred);
	}
	
	@PostMapping("/register")
    public UserDto addStudent(@RequestBody UserDto userData) {
		return userService.register(userData);
	}

}
