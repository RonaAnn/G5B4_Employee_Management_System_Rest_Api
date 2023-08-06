package com.greatlearning.ems.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.ems.dao.RoleRepository;
import com.greatlearning.ems.model.Role;
import com.greatlearning.ems.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository repository;

	@Override
	@Transactional
	public Role addRole(Role newRole) {
		
		Role role = this.repository.findByName(newRole.getName());
		
		if(role == null) {
			this.repository.save(newRole);
			return newRole;
		}
		
		return role;
	}

}
