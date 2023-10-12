package com.khan.pma.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khan.pma.entities.Employee;
import com.khan.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeService empService;

	@GetMapping
	public String displayEmployees(Model model) {
		Iterable<Employee> employees = empService.getAll();
		model.addAttribute("employees", employees);
		return "employees/list-employees";
	}

	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {

		Employee anEmployee = new Employee();

		model.addAttribute("employee", anEmployee);

		return "employees/new-employee";
	}

	@PostMapping("/save")
	public String createEmployee(Model model, @Valid Employee employee, Errors errors) {

		if (errors.hasErrors())
			return "employees/new-employee";

		// save to the database using an employee crud repository
		empService.save(employee);

		return "redirect:/employees";
	}

	@GetMapping("/update")
	public String displayEmployeeUpdateForm(@RequestParam("id") long theId, Model model) {
		// Retrieve the existing employee from the database by ID
		Employee theEmp = empService.findByEmployeeId(theId);

		// Populate the model with the existing employee data
		model.addAttribute("employee", theEmp);

		// Return the update-employee view
		return "employees/update-employee";
	}

	@PostMapping("/update")
	public String updateEmployee(@ModelAttribute("employee") @Valid Employee employee, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			// If there are validation errors, return to the update form
			return "employees/update-employee";
		}

		// Update the employee in the database
		empService.updateEmployee(employee);

		// Redirect to the list-employees view after successful update
		return "redirect:/employees";
	}

	@GetMapping("delete")
	public String deleteEmployee(@RequestParam("id") long theId, Model model) {
		Employee theEmp = empService.findByEmployeeId(theId);
		empService.delete(theEmp);
		return "redirect:/employees";
	}

}