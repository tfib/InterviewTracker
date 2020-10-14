package com.eval.interviewtracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eval.interviewtracker.dao.UserRepository;
import com.eval.interviewtracker.dto.UserDTO;
import com.eval.interviewtracker.entity.User;
import com.eval.interviewtracker.exception.InterviewTrackerException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDTO addNewUser(UserDTO user) throws InterviewTrackerException {
		String errors = "";
		if (user.getUserId() == null) {
			errors += "User Id should not be null; ";
		} else if (getAllUserIds().contains(user.getUserId())) {
			errors += "User Id already exists; ";
		}
		if ((user.getFirstName() == null) || (user.getFirstName().length() < 5)
				|| (user.getFirstName().length() > 30)) {
			errors += "First name is not valid, it should be within 5 to 30 characters; ";
		}
		if ((user.getLastName() == null) || (user.getLastName().length() < 3) || (user.getLastName().length() > 25)) {
			errors += "Last name is not valid, it should be within 3 to 25 characters; ";
		}
		if ((user.getEmail() == null)
				|| (!user.getEmail().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$"))) {
			errors += "Email is not in valid format; ";
		}
		if ((user.getMobile() == null) || (!user.getMobile().matches("(0/91)?[7-9][0-9]{9}"))) {
			errors += "Mobile number is not valid; ";
		}

		if (errors.isEmpty()) {
			return new UserDTO(userRepo.save(new User(user)));

		} else {
			throw new InterviewTrackerException(errors);
		}
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		List<User> users = userRepo.findAll();
		for (User user : users) {
			userDTOs.add(new UserDTO(user));
		}
		return userDTOs;
	}

	@Override
	public String deleteUser(int userId) {
		userRepo.deleteById(userId);
		return "User record deleted successfully";
	}

	@Override
	public UserDTO getUserById(int userId) {
		User user = userRepo.findById(userId).orElse(null);
		if (user != null) {
			return new UserDTO(user);
		} else {
			return null;
		}

	}

	@Override
	public List<Integer> getAllUserIds() {
		return userRepo.getAllUserIds();
	}
}
