package com.eval.interviewtracker.dto;

import java.util.List;

import com.eval.interviewtracker.entity.Interview;

public class InterviewDTO {

	private Integer interviewId;
	private String interviewerName;
	private String interviewName;
	private String usersSkills;
	private String time;
	private String date;
	private String interviewStatus;
	private String remarks;
	private List<Integer> userIds;

	public Integer getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(Integer interviewId) {
		this.interviewId = interviewId;
	}

	public String getInterviewerName() {
		return interviewerName;
	}

	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}

	public String getInterviewName() {
		return interviewName;
	}

	public void setInterviewName(String interviewName) {
		this.interviewName = interviewName;
	}

	public String getUsersSkills() {
		return usersSkills;
	}

	public void setUsersSkills(String usersSkills) {
		this.usersSkills = usersSkills;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getInterviewStatus() {
		return interviewStatus;
	}

	public void setInterviewStatus(String interviewStatus) {
		this.interviewStatus = interviewStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public InterviewDTO(Interview interview) {
		this.interviewId = interview.getInterviewId();
		this.interviewerName = interview.getInterviewerName();
		this.interviewName = interview.getInterviewName();
		this.usersSkills = interview.getUsersSkills();
		this.time = interview.getTime();
		this.date = interview.getDate();
		this.interviewStatus = interview.getInterviewStatus();
		this.remarks = interview.getRemarks();
		this.userIds = interview.getAllAttendeeIds();
	}

	public InterviewDTO() {

	}

	public List<Integer> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<Integer> userIds) {
		this.userIds = userIds;
	}

}
