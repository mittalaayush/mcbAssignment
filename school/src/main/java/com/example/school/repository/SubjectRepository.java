package com.example.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.school.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {

}
