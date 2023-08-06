package com.greatlearning.ems.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.ems.model.Employee;
import com.greatlearning.ems.service.EmployeeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@ApiOperation(value = "Fetch all the Employees", notes = "Fetches all the employees in the emloyees table")
	@GetMapping
	public Set<Employee> getAllEmployee() {

		return this.service.fetchAllEmployees();
	}
	
	@ApiOperation(value = "Fetch the Employee by their id", notes = "Fetches the Employee with the provided id")
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") long id) {

		return this.service.fetchEmployeeById(id);
	}

	@ApiOperation(value = "Add the Employee", notes = "Persist the employee")
	@PostMapping
	public Employee addEmployee(@Valid @RequestBody Employee emplooyee) {

		return this.service.addEmployee(emplooyee);
	}

	@ApiOperation(value = "Update the Employee", notes = "Update the employee in the employees table")
	@PutMapping
	public Employee updateEmployee(@Valid @RequestBody Employee employee) {

		return this.service.updateEmployee(employee);
	}

	@ApiOperation(value = "Delete the Employee", notes = "Delete the employee having the provided id")
	@DeleteMapping("/{id}")
	public String deleteEmployeeById(@PathVariable("id") long id) {

		this.service.deleteEmployeeById(id);
		
		return "Deleted employee id - " + id;
	}

	@ApiOperation(value = "Search for the Employee", notes = "Search for the employee by the provided first name")
	@GetMapping("/search/{firstName}")
	public Set<Employee> searchEmployeeByFirstName(@PathVariable("firstName") String firstName) {

		return this.service.fetchEmployeeByFirstName(firstName);
	}

	@ApiOperation(value = "Sort the Employee list", notes = "Sort the employees data by the provided direction")
	@GetMapping("/sort")
	public List<Employee> sortByFirstName(@RequestParam("order") String order) {

		return this.service.sortEmployeeByFirstName(order);
	}
}
