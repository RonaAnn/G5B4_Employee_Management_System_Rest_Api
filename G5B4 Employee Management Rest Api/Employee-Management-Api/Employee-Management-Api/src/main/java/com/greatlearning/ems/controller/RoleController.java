package com.greatlearning.ems.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.ems.model.Role;
import com.greatlearning.ems.service.RoleService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/roles")
public class RoleController {

	@Autowired
	private RoleService service;

	@ApiOperation(value = "Add the Role", notes = "Add a new Role")
	@PostMapping
	public Role addRole(@Valid @RequestBody Role role) {

		role.setName("ROLE_" + role.getName());
		return this.service.addRole(role);
	}

}
