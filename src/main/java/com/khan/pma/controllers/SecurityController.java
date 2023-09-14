package com.khan.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.khan.pma.dao.UserAccountRepository;
import com.khan.pma.entities.UserAccount;

@Controller
public class SecurityController {
	@Autowired
	UserAccountRepository accountRepo; //create an interface in dao
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	@GetMapping("/register")
	public String register(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute("userAccount", userAccount);
		return "security/register";
	}
	//To save it into database
	@PostMapping("/register/save")
	public String saveUser(Model model,UserAccount user) {
		user.setPassword(bCryptEncoder.encode(user.getPassword()));   //here we will encrypt the password or envoke encoder
		accountRepo.save(user);
		return "redirect:/";
	}
}
