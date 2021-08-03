package com.example.school.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class SubjectTeacherKey implements Serializable{
	
	@ManyToOne
    @JoinColumn(name = "group_id")
    private Groups group;
	
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

	public SubjectTeacherKey() {
	}

	public Groups getGroup() {
		return group;
	}

	public void setGroup(Groups group) {
		this.group = group;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public SubjectTeacherKey(Groups group, Subject subject) {
		super();
		this.group = group;
		this.subject = subject;
	}
    
    
    
}
