package com.khan.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khan.pma.dao.ProjectRepository;
import com.khan.pma.dto.ChartData;
import com.khan.pma.dto.TimeChartData;
import com.khan.pma.entities.Project;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository proRepo;
	
	
	public Project save(Project project) {
		return proRepo.save(project);
	}


	public List<Project> getAll() {
		return proRepo.findAll();
	}
	
	public List<ChartData> getProjectStatus(){
		return proRepo.getProjectStatus();
	}


	public Project findByProjectId(long theId) {
		// TODO Auto-generated method stub
		return proRepo.findByProjectId(theId);
	}


	public void delete(Project thePro) {
		proRepo.delete(thePro);
		
	}
	
	
	public List<TimeChartData> getTimeData(){
		return proRepo.getTimeData();
	}
}