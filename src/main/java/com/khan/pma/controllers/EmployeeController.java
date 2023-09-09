package com.khan.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khan.pma.entities.Employee;
import com.khan.pma.repository.EmployeeRepository;
@Controller
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	EmployeeRepository empRepo;
	
	//To activate and display the Link when you cick frm home
	@RequestMapping
	public String displayEmployee(Model model) {
		List<Employee>employees = empRepo.findAll();
		model.addAttribute("employees",employees);
		return "employees/list-employees";
	}

	@RequestMapping("/new")
	public String displayEmployeeForm(Model model) {
		Employee anEmployee= new Employee();
		model.addAttribute("employee",anEmployee);
		return "employees/new-employee";
	}
	@PostMapping("/save")
	public String createEmployee(Model model,Employee employee) {
	//save to the database using an employee crud repository
		//=>create interface in dao package EmployeeRepository
		empRepo.save(employee);
		return "redirect:/employees/new";
			}
}
