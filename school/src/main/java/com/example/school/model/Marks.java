package com.example.school.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "marks")
public class Marks {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mark_id")
    private Integer markId;
	
	@ManyToOne
    @JoinColumn(name = "student_id")
    private Student studentId;;
//
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subjectId;

    @Column
    private Date date;

    @Column
    private Integer mark;

	public Marks() {
		// TODO Auto-generated constructor stub
	}

	public Marks(Integer markId, Student studentId, Subject subjectId, Date date, Integer mark) {
		super();
		this.markId = markId;
		this.studentId = studentId;
		this.subjectId = subjectId;
		this.date = date;
		this.mark = mark;
	}

	public Integer getMarkId() {
		return markId;
	}

	public void setMarkId(Integer markId) {
		this.markId = markId;
	}

	public Student getStudentId() {
		return studentId;
	}

	public void setStudentId(Student studentId) {
		this.studentId = studentId;
	}

	public Subject getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Subject subjectId) {
		this.subjectId = subjectId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}
	
}
