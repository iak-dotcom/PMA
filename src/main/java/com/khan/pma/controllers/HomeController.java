package com.khan.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.khan.pma.dao.EmployeeRepository;
import com.khan.pma.dao.ProjectRepository;
import com.khan.pma.dto.ChartData;
import com.khan.pma.dto.EmployeeProject;
import com.khan.pma.entities.Project;

@Controller
public class HomeController {
	
	@Value("${version}")
	private String ver;
	
	@Autowired
	ProjectRepository proRepo;
	@Autowired
	EmployeeRepository empRepo;

	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		model.addAttribute("versionNumber", ver);		
		
		Map<String , Object> map = new HashMap<>();
		// we are quering the database for projects
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projectsList", projects);

		List<ChartData> projectData = proRepo.getProjectStatus();
		//Let`s convert projectData object into a json structure for use in javascript
				ObjectMapper objectMapper = new ObjectMapper();
				String jsonString = objectMapper.writeValueAsString(projectData);
				model.addAttribute("projectStatusCnt", jsonString);

		
		
		
		
		
		// we are quering the database for employees
//		List<Employee> employees = empRepo.findAll();
//		model.addAttribute("employeesList", employees);
//		return "main/home";

		List<EmployeeProject> employeesProjectCnt = empRepo.employeeProjects();
		model.addAttribute("employeesListProjectsCnt", employeesProjectCnt);
		return "main/home";

	
	
	}

}
