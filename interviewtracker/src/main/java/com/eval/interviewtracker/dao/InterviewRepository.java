package com.eval.interviewtracker.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eval.interviewtracker.entity.Interview;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Integer> {

	@Transactional
	@Modifying
	@Query("update Interview i set i.interviewStatus = :status where i.interviewId = :id")
	public Integer updateStatusOfInterview(@Param("id") Integer interviewId, @Param("status") String interviewStatus);

	@Query("select i from Interview i where i.interviewName = :interviewName and i.interviewerName = :interviewerName")
	public List<Interview> getInterviewByNames(@Param("interviewName") String interviewName,
			@Param("interviewerName") String interviewerName);
	
	@Query("select count(i) from Interview i")
	public Integer getTotalInterviewCount();
	
	@Query("select i.interviewId from Interview i")
	public List<Integer> getAllInterviewIds();

}
