package com.eval.interviewtracker.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.eval.interviewtracker.dto.InterviewDTO;

@Entity
@Table
public class Interview {

	@Id
	private Integer interviewId;
	private String interviewerName;
	private String interviewName;
	private String usersSkills;
	private String time;
	private String date;
	private String interviewStatus;
	private String remarks;

	@ManyToMany
	@JoinTable(name = "interview_user", joinColumns = @JoinColumn(name = "interviewId"), inverseJoinColumns = @JoinColumn(name = "userId"))
	private List<User> users = new ArrayList<User>();

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

	public Interview(InterviewDTO interviewDTO) {
		this.interviewId = interviewDTO.getInterviewId();
		this.interviewerName = interviewDTO.getInterviewerName();
		this.interviewName = interviewDTO.getInterviewName();
		this.usersSkills = interviewDTO.getUsersSkills();
		this.time = interviewDTO.getTime();
		this.date = interviewDTO.getDate();
		this.interviewStatus = interviewDTO.getInterviewStatus();
		this.remarks = interviewDTO.getRemarks();
	}

	public Interview() {

	}

	@Override
	public String toString() {
		return "Interview [interviewId=" + interviewId + ", interviewerName=" + interviewerName + ", interviewName="
				+ interviewName + ", usersSkills=" + usersSkills + ", time=" + time + ", date=" + date
				+ ", interviewStatus=" + interviewStatus + ", remarks=" + remarks + "]";
	}

	public void addAttendeeToInterview(User user) {
		this.users.add(user);
	}

	public List<User> getAllAttendees() {
		return this.users;
	}
	
	public List<Integer> getAllAttendeeIds() {
		List<Integer> attendeeIds = new ArrayList<Integer>();
		for(User user: getAllAttendees()) {
			attendeeIds.add(user.getUserId());
		}
		return attendeeIds;
	}

}
