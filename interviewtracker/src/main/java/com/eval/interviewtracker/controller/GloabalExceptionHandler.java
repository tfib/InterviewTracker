package com.eval.interviewtracker.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.eval.interviewtracker.dto.InterviewTrackerErrorResponseDTO;
import com.eval.interviewtracker.exception.InterviewTrackerException;

@RestControllerAdvice
public class GloabalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<InterviewTrackerErrorResponseDTO> handleException(InterviewTrackerException exception) {
		InterviewTrackerErrorResponseDTO errorResponse = new InterviewTrackerErrorResponseDTO();
		errorResponse.setStatusMessage(exception.getMessage());
		errorResponse.setTimeStamp(LocalDateTime.now());
		if (exception.getMessage().contains("not found")) {
			errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		} else if (exception.getMessage().contains("unable")) {
			errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}

	}
}
