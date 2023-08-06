package com.greatlearning.ems.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greatlearning.ems.dao.UserRepository;
import com.greatlearning.ems.model.DomainUserDetails;

@Service
public class DomainUserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return this.repository.findByUsername(username)
				.map(DomainUserDetails::new)
				.orElseThrow();
	}

}
