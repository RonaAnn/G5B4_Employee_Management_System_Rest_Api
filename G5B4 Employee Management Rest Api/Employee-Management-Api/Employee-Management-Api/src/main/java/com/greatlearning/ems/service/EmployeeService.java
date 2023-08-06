package com.greatlearning.ems.service;

import java.util.List;
import java.util.Set;

import com.greatlearning.ems.model.Employee;

public interface EmployeeService {
	
	public Set<Employee> fetchAllEmployees();
	
	public Employee addEmployee(Employee employee);
	
	public Employee fetchEmployeeById(long id);
	
	public Employee updateEmployee(Employee updatedEmployee);
	
	public void deleteEmployeeById(long id);
	
	public Set<Employee> fetchEmployeeByFirstName(String firstName);
	
	public List<Employee> sortEmployeeByFirstName(String order);

}
