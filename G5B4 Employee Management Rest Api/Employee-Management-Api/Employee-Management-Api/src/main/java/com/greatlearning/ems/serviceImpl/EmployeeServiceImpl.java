package com.greatlearning.ems.serviceImpl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.greatlearning.ems.dao.EmployeeRepository;
import com.greatlearning.ems.model.Employee;
import com.greatlearning.ems.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository repository;

	@Override
	public Set<Employee> fetchAllEmployees() {

		return Set.copyOf(this.repository.findAll());
	}

	@Override
	public Employee addEmployee(Employee employee) {

		return this.repository.save(employee);
	}

	@Override
	public Employee fetchEmployeeById(long id) {

		return this.repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Invalid Employee Id passed."));
	}

	@Override
	public Employee updateEmployee(Employee updatedEmployee) {

		Employee employee = this.fetchEmployeeById(updatedEmployee.getId());

		employee.setFirstName(updatedEmployee.getFirstName());
		employee.setLastName(updatedEmployee.getLastName());
		employee.setEmail(updatedEmployee.getEmail());

		return this.repository.save(employee);
	}

	@Override
	public void deleteEmployeeById(long id) {

		this.repository.deleteById(id);
	}

	@Override
	public Set<Employee> fetchEmployeeByFirstName(String firstName) {
		 
		Set<Employee> employee = Set.copyOf(this.repository.findByFirstNameContainsAllIgnoreCase(firstName));
		if(!employee.isEmpty()) {
			return  employee;
		}
		
		 throw new NoSuchElementException("Employee does not exist.");		
	}

	@Override
	public List<Employee> sortEmployeeByFirstName(String order) {
		
		return this.repository.findAll(Sort.by(Direction.fromString(order), "firstName"));
	}

}
