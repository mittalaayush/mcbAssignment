package com.example.bank.model;

import javax.persistence.*;

@Entity
@Table
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column()
	private String password;

	@Column()
	private String firstName;

	@Column()
	private String lastName;

	@Column(unique=true)
	private String username;
	
	@Column()
	private String token;
	
	@Column()
	private String userType;

	@Column(name = "failed_attempt")
	private int failedAttempt;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(Integer id, String password, String firstName, String lastName, String username,
			String token, String userType, int failedAttempt) {
		super();
		this.id = id;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.token = token;
		this.userType = userType;
		this.failedAttempt = failedAttempt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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


}
