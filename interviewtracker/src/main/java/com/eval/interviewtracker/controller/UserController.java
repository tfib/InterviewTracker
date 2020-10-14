package com.eval.interviewtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eval.interviewtracker.dto.UserDTO;
import com.eval.interviewtracker.exception.InterviewTrackerException;
import com.eval.interviewtracker.service.UserService;

@RestController
@RequestMapping("/interviewtracker/userapi")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<UserDTO> getAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping("/users")
	public UserDTO addUser(@RequestBody UserDTO user) throws InterviewTrackerException {
		UserDTO response = null;
		try {
			if (user == null) {
				throw new InterviewTrackerException("User data is null");
			} else {
				response = userService.addNewUser(user);
			}
		} catch (InterviewTrackerException ex) {
			throw new InterviewTrackerException(ex.getMessage());
		}
		return response;
	}

	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable Integer userId) throws InterviewTrackerException {
		String response = null;
		try {
			if (userService.getAllUserIds().contains(userId)) {
				response = userService.deleteUser(userId);
			} else {
				throw new InterviewTrackerException("User not found");
			}
		} catch (InterviewTrackerException ex) {
			throw new InterviewTrackerException(ex.getMessage());
		}
		return response;
	}

	@GetMapping("/users/{userId}")
	public UserDTO getUserByUserId(@PathVariable Integer userId) throws InterviewTrackerException {
		UserDTO response = null;
		try {
			if (userService.getAllUserIds().contains(userId)) {
				response = userService.getUserById(userId);
			} else {
				throw new InterviewTrackerException("User not found");
			}
		} catch (InterviewTrackerException ex) {
			throw new InterviewTrackerException(ex.getMessage());
		}
		return response;
	}

}
