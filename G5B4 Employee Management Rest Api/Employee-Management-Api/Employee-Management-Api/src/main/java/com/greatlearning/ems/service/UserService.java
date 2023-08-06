package com.greatlearning.ems.service;

import java.util.List;

import com.greatlearning.ems.model.User;


public interface UserService {

	public User addUser(User user);
	public List<User> getUser();
}
