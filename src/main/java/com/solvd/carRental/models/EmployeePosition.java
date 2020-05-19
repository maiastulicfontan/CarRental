package com.solvd.carRental.models;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	
}
