package com.khan.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khan.pma.dao.EmployeeRepository;
import com.khan.pma.dto.EmployeeProject;
import com.khan.pma.entities.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository empRepo;
	
	
	public Employee save(Employee employee) {
		return empRepo.save(employee);
	}


	public Iterable<Employee> getAll() {
		return empRepo.findAll();
	}


	public List<EmployeeProject> employeeProjects() {
		return empRepo.employeeProjects();
	}


	public Employee findByEmployeeId(long theId) {
		// TODO Auto-generated method stub
		return empRepo.findByEmployeeId(theId);
	}


	public void delete(Employee theEmp) {
		empRepo.delete(theEmp);
		
	}


	public Employee updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return empRepo.save(employee);
	}


	public boolean isEmailAlreadyInUse(String email) {
		// TODO Auto-generated method stub
		return false;
	}

}