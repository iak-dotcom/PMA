package com.khan.pma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khan.pma.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

	@Override
	public List <Project> findAll();
}
