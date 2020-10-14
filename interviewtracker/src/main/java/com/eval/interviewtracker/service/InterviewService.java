package com.eval.interviewtracker.service;

import java.util.List;

import com.eval.interviewtracker.dto.InterviewDTO;
import com.eval.interviewtracker.dto.UserDTO;
import com.eval.interviewtracker.exception.InterviewTrackerException;

public interface InterviewService {

	public InterviewDTO addNewInterview(InterviewDTO interview) throws InterviewTrackerException;

	public List<InterviewDTO> getAllInterviews();

	public String deleteInterview(int interviewId);

	public InterviewDTO updateInterviewStatus(int interviewId, InterviewDTO interview) throws InterviewTrackerException;

	public List<InterviewDTO> getInterviewByInterviewNameAndInterviewerName(String interviewName,
			String interviewerName) throws InterviewTrackerException;

	public Integer getTotalNumberOfInterviews();

	public List<Integer> getAllInterviewIds();

	public InterviewDTO addAttendeeToInterview(int interviewId, InterviewDTO interview) throws InterviewTrackerException;

	public List<UserDTO> getAllAttendess(int interviewId);
}
