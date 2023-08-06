package com.greatlearning.ems.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.greatlearning.ems.dao.RoleRepository;
import com.greatlearning.ems.dao.UserRepository;
import com.greatlearning.ems.model.Role;
import com.greatlearning.ems.model.User;
import com.greatlearning.ems.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public User addUser(User user) {

		Set<Role> roles = new HashSet<>();
		for (Role role : user.getRoles()) {

			Role temp = createRoleIfNotPresent("ROLE_" + role.getName());
			temp.getUsers().add(user);

			roles.add(temp); 

		}
		user.setRoles(roles);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		return this.userRepository.save(user);
	}

	@Transactional
	private Role createRoleIfNotPresent(String name) {

		Role role = this.roleRepository.findByName(name);

		if (role == null) {
			role = this.roleRepository.save(new Role(name));
			return role;
		}
		return role;
	}

	@Override
	public List<User> getUser() {
		List<User> user=this.userRepository.findAll();
		return user;
	}

}
