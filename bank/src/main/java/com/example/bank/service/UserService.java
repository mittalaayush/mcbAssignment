package com.example.bank.service;

import com.example.bank.dto.LoginCredentials;
import com.example.bank.dto.UserDto;

public interface UserService {
	
	UserDto login(LoginCredentials userCred);
	
	UserDto register(UserDto userDto);

}
