package com.example.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import com.example.bank.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
         
    @Query("UPDATE User u SET u.failedAttempt = ?1 WHERE u.username = ?2")
    @Modifying
    public void updateFailedAttempts(int failAttempts, String username);
     
    public final static String GET_STUDENT_BY_USERNAME = "SELECT u FROM User u WHERE username = :username";

	@Query(GET_STUDENT_BY_USERNAME)
	List<User> findByUsername(@Param("username") String username);
	
	@Query("UPDATE User u SET u.failedAttempt = ?1")
    @Modifying
    public void unlockUsers(int failAttempts);
    
}