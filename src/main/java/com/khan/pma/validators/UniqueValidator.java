package com.khan.pma.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.khan.pma.dao.EmployeeRepository;
import com.khan.pma.entities.Employee;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {

	@Autowired
	EmployeeRepository empRepo;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
	Employee emp = empRepo.findByEmail(value);
			
			if(emp!=null) 
	return false;
	else
		return true;
	
	}

	@Override
	public void initialize(UniqueValue constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}
	}
