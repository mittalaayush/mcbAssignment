package com.example.school.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SubjectTeacher {
	
	 @EmbeddedId
	 private SubjectTeacherKey id;


//  @ManyToOne
//  @JoinColumn(name = "teacher_id")
	@Column(name = "teacher_id")
    private Integer teacherId;
 
//	@ManyToOne
//	@JoinColumn(name = "subject_id")
//    private Subject subjectId;
	
//	@ManyToOne
//	@JoinColumn(name = "group_id")
//	private Groups groupId;

	public SubjectTeacher() {
	}

	public SubjectTeacherKey getId() {
		return id;
	}

	public void setId(SubjectTeacherKey id) {
		this.id = id;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
}
