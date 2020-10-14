package com.eval.interviewtracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eval.interviewtracker.dao.InterviewRepository;
import com.eval.interviewtracker.dao.UserRepository;
import com.eval.interviewtracker.dto.InterviewDTO;
import com.eval.interviewtracker.dto.UserDTO;
import com.eval.interviewtracker.entity.Interview;
import com.eval.interviewtracker.entity.User;
import com.eval.interviewtracker.exception.InterviewTrackerException;

@Service
public class InterviewServiceImpl implements InterviewService {

	@Autowired
	private InterviewRepository interviewRepo;

	@Autowired
	private UserRepository userRepo;

	private String interviewStatusBusinessValidation(String interviewStatus, String errors) {
		if ((interviewStatus == null) || (interviewStatus.length() < 5) || (interviewStatus.length() > 100)) {
			errors += "Interview Status is not valid, it should be within 5 to 100 characters; ";
		}

		return errors;
	}

	@Override
	public InterviewDTO addNewInterview(InterviewDTO interview) throws InterviewTrackerException {
		String errors = "";
		if (interview.getInterviewId() == null) {
			errors += "Interview Id should not be null; ";
		} else if (getAllInterviewIds().contains(interview.getInterviewId())) {
			errors += "Interview Id already exists; ";
		}
		if ((interview.getInterviewerName() == null) || (interview.getInterviewerName().length() < 5)
				|| (interview.getInterviewerName().length() > 30)) {
			errors += "Interviewer name is not valid, it should be within 5 to 30 characters; ";
		}
		if ((interview.getInterviewName() == null) || (interview.getInterviewName().length() < 3)
				|| (interview.getInterviewName().length() > 30)) {
			errors += "Interview name is not valid, it should be within 3 to 30 characters; ";
		}
		if ((interview.getUsersSkills() == null) || (interview.getUsersSkills().length() < 5)
				|| (interview.getUsersSkills().length() > 30)) {
			errors += "Users Skills is not valid, it should be within 5 to 30 characters; ";
		}
		if ((interview.getRemarks() == null) || (interview.getRemarks().length() < 5)
				|| (interview.getRemarks().length() > 100)) {
			errors += "Remarks is not valid, it should be within 5 to 100 characters; ";
		}
		errors = interviewStatusBusinessValidation(interview.getInterviewStatus(), errors);
		if (errors.isEmpty()) {
			return new InterviewDTO(interviewRepo.save(new Interview(interview)));

		} else {
			throw new InterviewTrackerException(errors);
		}
	}

	@Override
	public List<InterviewDTO> getAllInterviews() {
		List<InterviewDTO> interviewDTOs = new ArrayList<InterviewDTO>();
		List<Interview> interviews = interviewRepo.findAll();
		for (Interview interview : interviews) {
			interviewDTOs.add(new InterviewDTO(interview));
		}
		return interviewDTOs;
	}

	@Override
	public String deleteInterview(int interviewId) {
		interviewRepo.deleteById(interviewId);
		return "Interview record deleted successfully";
	}

	public Interview getInterviewById(int interviewId) {
		Interview interview = interviewRepo.findById(interviewId).orElse(null);
		if (interview != null) {
			return interview;
		} else {
			return null;
		}
	}

	@Override
	public InterviewDTO updateInterviewStatus(int interviewId, InterviewDTO interview)
			throws InterviewTrackerException {
		String errors = "";
		if (getAllInterviewIds().contains(interviewId)) {
			errors = interviewStatusBusinessValidation(interview.getInterviewStatus(), errors);
		} else {
			errors += "Interview record doesn't exist; ";
		}

		if (errors.isEmpty()) {
			if (interviewRepo.updateStatusOfInterview(interviewId, interview.getInterviewStatus()) > 0) {
				return new InterviewDTO(getInterviewById(interviewId));
			} else {
				throw new InterviewTrackerException("Unable to update status");
			}
		} else {
			throw new InterviewTrackerException(errors);
		}
	}

	@Override
	public List<InterviewDTO> getInterviewByInterviewNameAndInterviewerName(String interviewName,
			String interviewerName) throws InterviewTrackerException {
		List<InterviewDTO> interviewDTOs = new ArrayList<InterviewDTO>();
		List<Interview> interviews;
		if ((interviewName == null) || (interviewerName == null)) {
			throw new InterviewTrackerException("Interview Name/Interviewer name is null");
		} else {
			interviews = interviewRepo.getInterviewByNames(interviewName, interviewerName);
		}
		for (Interview interview : interviews) {
			interviewDTOs.add(new InterviewDTO(interview));
		}
		return interviewDTOs;
	}

	@Override
	public Integer getTotalNumberOfInterviews() {
		return interviewRepo.getTotalInterviewCount();
	}

	@Override
	public List<Integer> getAllInterviewIds() {
		return interviewRepo.getAllInterviewIds();
	}

	@Override
	public InterviewDTO addAttendeeToInterview(int interviewId, InterviewDTO interview)
			throws InterviewTrackerException {
		String errors = "";
		if (!interview.getUserIds().isEmpty()) {
			User user = userRepo.findById(interview.getUserIds().get(0)).orElse(null);
			if (user != null) {
				Interview existingInterview = interviewRepo.findById(interviewId).orElse(null);
				if (existingInterview == null) {
					errors += "Interview doesn't exist with the given Id";
				} else {
					if (existingInterview.getAllAttendeeIds().contains(user.getUserId())) {
						errors += "Attendee is already associated with the interview";
					} else {
						existingInterview.addAttendeeToInterview(user);
						return new InterviewDTO(interviewRepo.save(existingInterview));
					}
				}
			} else {
				errors += "User doesn't exist with this id " + interview.getUserIds().get(0);
			}
		} else {
			errors += "Invalid user id or user doesn't exist";
		}
		throw new InterviewTrackerException(errors);
	}

	@Override
	public List<UserDTO> getAllAttendess(int interviewId) {
		List<UserDTO> users = new ArrayList<UserDTO>();
		Interview interview = getInterviewById(interviewId);
		for (User user : interview.getAllAttendees()) {
			users.add(new UserDTO(user));
		}
		return users;
	}

}
