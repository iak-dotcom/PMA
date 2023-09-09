package com.khan.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khan.pma.entities.Project;
import com.khan.pma.repository.ProjectRepository;


@Controller
@RequestMapping("/projects")
public class ProjectController {
	@Autowired
	ProjectRepository proRepo;
	
	//To activate and display the Link when you cick frm home
	@RequestMapping
	public String displayprojects(Model model) {
		List<Project>projects = proRepo.findAll();
		model.addAttribute("projects",projects);
		return "projects/list-projects";
	}
	
	@RequestMapping("/new")
	public String displayProjectForm(Model model) {
		Project aProject = new Project();
		model.addAttribute("project",aProject);
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Project project, Model model) {
		//this method would handle saving data in database
		proRepo.save(project);
		//redirect is used to prevent duplicate submissions
		return "redirect:/projects/new";
	}
	}
