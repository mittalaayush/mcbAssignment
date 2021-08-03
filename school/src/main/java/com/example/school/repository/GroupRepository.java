package com.example.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.school.model.Groups;

public interface GroupRepository extends JpaRepository<Groups, Integer>{

}
