package com.springbootcrud.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootcrud.model.Employee;
import com.springbootcrud.repository.EmployeeRepository;

@Service
public class EmployeeDAO {

	@Autowired
	EmployeeRepository employeeRepository;
	
	//Add an Employee
	public Employee save(Employee emp) {
		return employeeRepository.save(emp);
	}
	
	//Search Employee
	public List<Employee> findAll(){
		return employeeRepository.findAll();
	}
	
	//Get an Employee
	public Optional<Employee> findById(Long empId) {
		return employeeRepository.findById(empId);
	}
	
	//Delete an Employee
	public void delete(Employee emp) {
		employeeRepository.delete(emp);
	}
}
