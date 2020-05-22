package com.solvd.carRental.models;

import java.time.LocalDate;


public class Employee extends Person {
	private LocalDate hireDate;
	private EmployeePosition position;
	
	public Employee () {}
	
	public Employee(EmployeePosition position) {
		this.position = position;
	}
	
	public Employee(Long id, String firstName, String lastName, LocalDate birthDate, String nationalGovernmentId, LocalDate hireDate) {
		super(id, firstName, lastName, birthDate, nationalGovernmentId);
		this.hireDate = hireDate;
	}

	public Employee(Long id, String firstName, String lastName, LocalDate birthDate, String nationalGovernmentId, LocalDate hireDate, EmployeePosition position) {
		super(id, firstName, lastName, birthDate, nationalGovernmentId);
		this.hireDate = hireDate;
		this.position = position;
	}

	public Employee(Long id, LocalDate hireDate) {
		super(id);
		this.hireDate = hireDate;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public EmployeePosition getPosition() {
		return position;
	}

	public void setPosition(EmployeePosition position) {
		this.position = position;
	}
	
	@Override
	public String toString() {
		return "Employee [id: " + getId() + ", First Name: " + getFirstName() + ", Last Name: "
				+ getLastName() + ", Birth Date: " + getBirthDate() + ", National Government Id: "
				+ getNationalGovernmentId() + ", Hire Date: " + hireDate + "]";
	
	}

}
