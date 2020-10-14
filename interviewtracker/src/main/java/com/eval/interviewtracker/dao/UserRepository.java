package com.eval.interviewtracker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eval.interviewtracker.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("select u.userId from User u")
	public List<Integer> getAllUserIds();
	
}
