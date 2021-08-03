package com.example.school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Student {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer studentId;
	
	@Column (name = "first_name")
    private String firstName;
	
	@Column (name = "last_name")
    private String lastName;
	
	@ManyToOne
    @JoinColumn(name = "group_id")
    private Groups groupId;

	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(Integer studentId, String firstName, String lastName, Groups groupId) {
		super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.groupId = groupId;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
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

	public Groups getGroupId() {
		return groupId;
	}

	public void setGroupId(Groups groupId) {
		this.groupId = groupId;
	}
	
}
