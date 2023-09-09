package com.khan.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.khan.pma.entities.Employee;
import com.khan.pma.entities.Project;
import com.khan.pma.repository.EmployeeRepository;
import com.khan.pma.repository.ProjectRepository;

@Controller
public class HomeController {
	@Autowired
	ProjectRepository proRepo;
	@Autowired
	EmployeeRepository empRepo;

	@GetMapping("/")
	public String displayHome(Model model) {
		// we are quering the database for projects
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projectsList", projects);

		// we are quering the database for employees
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("employeesList", employees);
		return "main/home";

	}

}
