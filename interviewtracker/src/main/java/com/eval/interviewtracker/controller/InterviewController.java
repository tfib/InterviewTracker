package com.eval.interviewtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eval.interviewtracker.dto.InterviewDTO;
import com.eval.interviewtracker.dto.UserDTO;
import com.eval.interviewtracker.exception.InterviewTrackerException;
import com.eval.interviewtracker.service.InterviewService;

@RestController
@RequestMapping("/interviewtracker/interviewapi")
public class InterviewController {

	@Autowired
	private InterviewService interviewService;

	@GetMapping("/interviews")
	public List<InterviewDTO> getAllUsers() {
		return interviewService.getAllInterviews();
	}

	@PostMapping("/interviews")
	public InterviewDTO addInterview(@RequestBody InterviewDTO interview) throws InterviewTrackerException {
		InterviewDTO response = null;
		try {
			if (interview == null) {
				throw new InterviewTrackerException("Interview data is null");
			} else {
				response = interviewService.addNewInterview(interview);
			}
		} catch (InterviewTrackerException ex) {
			throw new InterviewTrackerException(ex.getMessage());
		}
		return response;
	}

	@DeleteMapping("/interviews/{interviewId}")
	public String deleteInterview(@PathVariable Integer interviewId) throws InterviewTrackerException {
		String response = null;
		try {
			if (interviewService.getAllInterviewIds().contains(interviewId)) {
				response = interviewService.deleteInterview(interviewId);
			} else {
				throw new InterviewTrackerException("Interview not found");
			}
		} catch (InterviewTrackerException ex) {
			throw new InterviewTrackerException(ex.getMessage());
		}
		return response;
	}

	@GetMapping("/interviews/count")
	public Integer getTotalInterviewCount() throws InterviewTrackerException {
		return interviewService.getTotalNumberOfInterviews();
	}

	@PutMapping("/interviews/interviewstatus/{interviewId}")
	public InterviewDTO updateInterviewStatus(@PathVariable Integer interviewId, @RequestBody InterviewDTO interview)
			throws InterviewTrackerException {
		InterviewDTO response = null;
		try {
			if ((interview == null) || (interviewId == null)) {
				throw new InterviewTrackerException("Interview data is null");
			} else {
				response = interviewService.updateInterviewStatus(interviewId, interview);
			}
		} catch (InterviewTrackerException ex) {
			throw new InterviewTrackerException(ex.getMessage());
		}
		return response;
	}

	@GetMapping("/interviews/{interviewName}/{interviewerName}")
	public List<InterviewDTO> getInterviewByInterviewAndInterviewer(@PathVariable String interviewName,
			@PathVariable String interviewerName) throws InterviewTrackerException {
		return interviewService.getInterviewByInterviewNameAndInterviewerName(interviewName, interviewerName);
	}

	@PutMapping("/interviews/attendees/{interviewId}")
	public InterviewDTO addAttendeeToTheInterview(@PathVariable int interviewId, @RequestBody InterviewDTO interview)
			throws InterviewTrackerException {
		try {
			return interviewService.addAttendeeToInterview(interviewId, interview);
		} catch (InterviewTrackerException ex) {
			throw new InterviewTrackerException(ex.getMessage());
		}
	}

	@GetMapping("/interviews/attendees/{interviewId}")
	public List<UserDTO> getAttendeesList(@PathVariable Integer interviewId) throws InterviewTrackerException {
		try {
			if (interviewId == null) {
				throw new InterviewTrackerException("Interview id is null");
			} else if (!interviewService.getAllInterviewIds().contains(interviewId)) {
				throw new InterviewTrackerException("Interview not found with the given Id");
			} else {
				return interviewService.getAllAttendess(interviewId);
			}
		} catch (InterviewTrackerException ex) {
			throw new InterviewTrackerException(ex.getMessage());
		}
	}

}
