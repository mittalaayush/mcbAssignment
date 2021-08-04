package com.example.bank.dto;

public class UserDto {

	public int id;
	public String password;
	public String firstName;
	public String lastName;
	public String username;
	public String token;
	public String userType;
	public int failedAttempt;
	public String userResponseMessage;
	
	public UserDto() {
		// TODO Auto-generated constructor stub
	}
	
	public UserDto(int id, String password, String firstName, String lastName, String username, String token,
			String userType, int failedAttempt, String userResponseMessage) {
		super();
		this.id = id;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.token = token;
		this.userType = userType;
		this.failedAttempt = 0;
		this.userResponseMessage = userResponseMessage;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public int getFailedAttempt() {
		return failedAttempt;
	}
	public void setFailedAttempt(int failedAttempt) {
		this.failedAttempt = failedAttempt;
	}
	public String getUserResponseMessage() {
		return userResponseMessage;
	}
	public void setUserResponseMessage(String userResponseMessage) {
		this.userResponseMessage = userResponseMessage;
	}
	
	
}
