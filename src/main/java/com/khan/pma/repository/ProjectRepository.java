package com.khan.pma.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khan.pma.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

}
