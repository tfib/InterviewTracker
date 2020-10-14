package com.eval.interviewtracker.service;

import java.util.List;

import com.eval.interviewtracker.dto.UserDTO;
import com.eval.interviewtracker.exception.InterviewTrackerException;

public interface UserService {

	public UserDTO addNewUser(UserDTO user) throws InterviewTrackerException;

	public List<UserDTO> getAllUsers();

	public String deleteUser(int userId);

	public UserDTO getUserById(int userId);

	public List<Integer> getAllUserIds();

}
