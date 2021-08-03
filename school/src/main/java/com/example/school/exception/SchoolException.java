package com.example.school.exception;

public class SchoolException extends RuntimeException{

	public SchoolException() {
	}

	public SchoolException(String message) {
		super(message);
	}

	public SchoolException(Throwable cause) {
		super(cause);
	}
	
}
