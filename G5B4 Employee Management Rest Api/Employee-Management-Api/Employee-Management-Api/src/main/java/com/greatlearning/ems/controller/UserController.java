package com.greatlearning.ems.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.ems.model.User;
import com.greatlearning.ems.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserService service;

	@ApiOperation(value = "ADD the User", notes = "Add a new User along wiht their role")
	@PostMapping
	public User addUser(@Valid @RequestBody User user) {

		return this.service.addUser(user);
	}
	
	@GetMapping
	public List<User> getUser(){
		return this.service.getUser();
	}

}
