package com.khan.pma.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khan.pma.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
	
}