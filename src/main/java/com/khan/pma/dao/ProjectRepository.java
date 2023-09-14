package com.khan.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.khan.pma.dto.ChartData;
import com.khan.pma.dto.EmployeeProject;
import com.khan.pma.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

	@Override
	public List <Project> findAll();
	
	@Query(nativeQuery=true, value="SELECT stage as label, COUNT(*) as value " +
	
			"FROM project " +
			"GROUP BY stage")
	
public List<ChartData> getProjectStatus();
}
