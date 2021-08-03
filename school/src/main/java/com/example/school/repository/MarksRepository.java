package com.example.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.school.model.Marks;

public interface MarksRepository extends JpaRepository<Marks, Integer>{
	
//	public final static String GET_LOAN_REPORTS = "SELECT lr FROM LoanReport lr WHERE product = :product";
	public final static String GET_MARKS_BY_STUDENT_ID = "SELECT m FROM Marks m WHERE student_id = :studentId";

	@Query(GET_MARKS_BY_STUDENT_ID)
	List<Marks> findByStudentId(@Param("studentId") Integer studentId);

//	@Query("select m from marks m WHERE m.student_id.id=?1")
//	public List<Marks> findByStudentId(Integer studentId);
}
