package com.khan.pma.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //this must be Auto
	private long projectId;
	@NotBlank(message="Must provide a project name")
	@Size(min=2, max =50)
	private String name;
	private String stage; // Non-started,completed,inprogress
	@NotBlank(message="Must provide a description")
	@Size(min=2, max =50)
	private String description;

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinTable(name = "project_employee", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
	@JsonIgnore
	private List<Employee> employees;

	public Project() {
	}

//unselect the id in constructor
	public Project(String name, String stage, String description) {
		super();
		this.name = name;
		this.stage = stage;
		this.description = description;
	}

}
