package com.greatlearning.ems.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatlearning.ems.dao.EmployeeRepository;
import com.greatlearning.ems.dao.UserRepository;
import com.greatlearning.ems.model.Employee;
import com.greatlearning.ems.model.Role;
import com.greatlearning.ems.model.User;

@Configuration
public class BootStrapAppData {

	@Autowired
	private EmployeeRepository empRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@EventListener(ApplicationReadyEvent.class)
	public void loadEmployees(ApplicationReadyEvent event) {

		Employee emp1 = new Employee("Emma", "Watson", "em.watsom@gmail.com");
		Employee emp2 = new Employee("Daniel", "RadCliff", "dan_cliff@outlook.com");
		Employee emp3 = new Employee("Rupert", "Grint", "rupertGrint97@hotmail.com");
		Employee emp4 = new Employee("Robert", "Pattinson", "rob.pattinson@gmail.com");
		Employee emp5 = new Employee("Jeon", "Jungkook", "jungkookJeon97@gmail.com");
		Employee emp6 = new Employee("Nivin", "Pauly", "pauly_nivin@outlook.com");
		Employee emp7 = new Employee("Kate", "Winslet", "katiewins@gmail.com");
		Employee emp8 = new Employee("Sai", "Pallavi", "sai.pallavi@hotmail.com");
		Employee emp9 = new Employee("Emma", "Storm", "emm.storm@hotmail.com");
		Employee emp10 = new Employee("Emma", "Roberts", "emma_roberts@hotmail.com");

		this.empRepository.save(emp1);
		this.empRepository.save(emp2);
		this.empRepository.save(emp3);
		this.empRepository.save(emp4);
		this.empRepository.save(emp5);
		this.empRepository.save(emp6);
		this.empRepository.save(emp7);
		this.empRepository.save(emp8);
		this.empRepository.save(emp9);
		this.empRepository.save(emp10);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void loadUsers(ApplicationReadyEvent event) {

		Role role1 = new Role("ROLE_ADMIN");
		Role role2 = new Role("ROLE_USER");


		User user1 = new User("admin", this.passwordEncoder.encode("admin"));
		User user2 = new User("user", this.passwordEncoder.encode("user"));


		user1.addRole(role1);
		user2.addRole(role2);


		this.userRepository.save(user1);
		this.userRepository.save(user2);


	}
}
