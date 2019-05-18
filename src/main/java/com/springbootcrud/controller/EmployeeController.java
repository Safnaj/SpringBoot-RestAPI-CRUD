package com.springbootcrud.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootcrud.dao.EmployeeDAO;
import com.springbootcrud.model.Employee;

@RestController
@RequestMapping("/company")
public class EmployeeController {

	@Autowired
	EmployeeDAO employeeDAO; 
	
	//Save an Employee to DB
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee emp) {
		return employeeDAO.save(emp); 
	}
	
	//Get All Employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeDAO.findAll();
	}
	
	//Get an Employee using ID		//Video Minute 34.50
	@GetMapping("/employees/{id}")
	public  ResponseEntity<Employee> getEmployeeId(@PathVariable(value="id") Long empId){
		
		Employee emp = employeeDAO.findById(empId).orElse(null);
		
		if(emp==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(emp);
	}
	
	//Update an Employee using ID
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") long empId,@Valid @RequestBody Employee empDetails){
		
		Employee emp = employeeDAO.findById(empId).orElse(null);
		
		if(emp==null) {
			return ResponseEntity.notFound().build();
		}
		
		emp.setName(empDetails.getName());
		emp.setDesignation(empDetails.getDesignation());
		emp.setExpertise(empDetails.getExpertise());
		
		Employee updateEmployee = employeeDAO.save(emp);
		return ResponseEntity.ok().body(updateEmployee);
	}
	
	//Delete an Employee
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(value="id") Long empId){
		
		Employee emp = employeeDAO.findById(empId).orElse(null);
		
		if(emp==null) {
			return ResponseEntity.notFound().build();
		}	
		employeeDAO.delete(emp);
		return ResponseEntity.ok().build();
	}
}
