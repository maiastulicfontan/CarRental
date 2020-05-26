package com.solvd.carRental.models;

import javax.xml.bind.annotation.XmlElement;  
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlRootElement(name = "position")
@XmlType (propOrder= {"id", "name", "description", "salary"})
public class EmployeePosition {
	private Long id;
	private String name;
	private String description;
	private Double salary;
	private Department department;
	
	public EmployeePosition () {}
	
	public EmployeePosition(Long id, String name, Department department) {
		this.id = id;
		this.name = name;
		this.department = department;
	}
	
	public EmployeePosition(Long id, String name, String description, Double salary) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.salary = salary;
	}

	public EmployeePosition(Long id, String name, String description, Double salary, Department department) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.salary = salary;
		this.department = department;
	}

	@XmlAttribute
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@XmlElement
	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	@XmlTransient
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "EmployeePosition [id=" + id + ", name=" + name + ", description=" + description + ", salary=" + salary
				+ "]";
	}

	
}
