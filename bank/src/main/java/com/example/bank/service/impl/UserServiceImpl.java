package com.example.bank.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.dto.LoginCredentials;
import com.example.bank.dto.UserDto;
import com.example.bank.model.User;
import com.example.bank.repository.UserRepository;
import com.example.bank.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	public static final int MAX_FAILED_ATTEMPTS = 10;
	
	 @Autowired
	 private UserRepository repo;
	 
	 public void increaseFailedAttempts(User user) {
	        int newFailAttempts = user.getFailedAttempt() + 1;
	        repo.updateFailedAttempts(newFailAttempts, user.getEmail());
	    }

	@Override
	public UserDto login(LoginCredentials userCred) {
		UserDto userDto = new UserDto();
		User u = repo.findByUsername(userCred.username);
		if(u!=null) {
			userDto.userResponseMessage = "No Such User Found";		
		}
		
		String password = u.getPassword();
		
		if( password !=null && password.equals(userCred.password) && u.getFailedAttempt() < MAX_FAILED_ATTEMPTS) {
			BeanUtils.copyProperties(u, userDto);
			userDto.userResponseMessage = "Success";
			return userDto;
		} else {
			if(u.getFailedAttempt()<MAX_FAILED_ATTEMPTS-1) {
				userDto.userResponseMessage = "Incorrect Credentials";
			} else {
				userDto.userResponseMessage = "Incorrect Credentials : Account Locked";
			}
			
			increaseFailedAttempts(u);
			return userDto;	
		}
			
	}
	
	@Override
	public UserDto register(UserDto userDto) {
		User u = new User();
		userDto.failedAttempt = 0;
		BeanUtils.copyProperties(userDto,u);		
		u = repo.save(u);
		BeanUtils.copyProperties(u, userDto);
		return userDto;		
	}
	
}
