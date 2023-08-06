package com.greatlearning.ems.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider authPorvider = new DaoAuthenticationProvider();

		authPorvider.setUserDetailsService(userDetailsService);
		authPorvider.setPasswordEncoder(passwordEncoder());

		return authPorvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
		
		AuthenticationManagerBuilder authManager = http.getSharedObject(AuthenticationManagerBuilder.class);
		authManager.authenticationProvider(authenticationProvider());
		
		return authManager.build();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.cors().disable();
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		http.authorizeRequests()
			.antMatchers("/h2-console**","/login**","/logout**","/swagger-ui.html/**")
				.permitAll()			
			.antMatchers(HttpMethod.GET,"/api/employees**","/api/employees/**","/api/employees/search/**","/api/employees/sort**")
			 	.authenticated()
			.antMatchers(HttpMethod.PUT,"/api/employees**")
				.authenticated()
			.antMatchers(HttpMethod.POST,"/api/employees**","/api/users**","/api/roles**")
				.hasRole("ADMIN")
			.antMatchers(HttpMethod.DELETE,"/api/employees/**")
				.hasRole("ADMIN")
			.anyRequest()
				.fullyAuthenticated()
			.and()
			.formLogin()
			.and()
			.httpBasic();
					
		return http.build();
		
	}
}
