package com.eval.interviewtracker.dto;

import java.time.LocalDateTime;

public class InterviewTrackerErrorResponseDTO {

	private int statusCode;
	private String statusMessage;
	private LocalDateTime timeStamp;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public InterviewTrackerErrorResponseDTO(int statusCode, String statusMessage, LocalDateTime timeStamp) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.timeStamp = timeStamp;
	}

	public InterviewTrackerErrorResponseDTO() {

	}

}
