package com.khan.pma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khan.pma.dao.EmployeeRepository;
import com.khan.pma.dao.ProjectRepository;
import com.khan.pma.entities.Employee;
import com.khan.pma.entities.Project;
import com.khan.pma.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	@Autowired
	ProjectRepository proRepo;

	@Autowired
	EmployeeRepository empRepo;

	@Autowired
	ProjectService proService;

	// To activate and display the Link when you cick frm home
	@RequestMapping
	public String displayprojects(Model model) {
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projects", projects);
		return "projects/list-projects";
	}

	@RequestMapping("/new")
	public String displayProjectForm(Model model) {
		Project aProject = new Project();
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees", employees);
		return "projects/new-project";
	}

	@PostMapping("/save")
	public String createProject(@Valid Project project, Errors errors, Model model) {
/*		the following two line fixed the error when the validation showed up employee list was
		disappearing.when you click enter project on blank form.*/
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("allEmployees", employees);
		
		if (errors.hasErrors())

			return "projects/new-project";
		// this method would handle saving data in database
		proRepo.save(project);

		return "redirect:/projects";
	}

	@GetMapping("/update")
	public String displayProjectUpdateForm(@RequestParam("id") long theId, Model model) {
		Project thePro = proService.findByProjectId(theId);
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("project", thePro);// this will bind the data in the field
		model.addAttribute("allEmployees", employees);
		return "projects/new-project";
	}

	@GetMapping("/delete")
	public String deleteProject(@RequestParam("id") long theId, Model model) {
		Project thePro = proService.findByProjectId(theId);
		proService.delete(thePro);
		return "redirect:/projects";
	}

}
