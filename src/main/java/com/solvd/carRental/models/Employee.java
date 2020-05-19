package com.solvd.carRental.models;

import java.sql.Date;


public class Employee {
	private Long id;
	private Person person;
	private Date hireDate;
	private EmployeePosition position;
	
	public Employee () {}
	
	public Employee(EmployeePosition position) {
		this.position = position;
	}

	public Employee(Date hireDate, EmployeePosition position) {
		this.hireDate = hireDate;
		this.position = position;
	}


	public Employee(Person person, Date hireDate, EmployeePosition position) {
		super();
		this.person = person;
		this.hireDate = hireDate;
		this.position = position;
	}

	public Employee(Long id, Date hireDate) {
		this.id = id;
		this.hireDate = hireDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public EmployeePosition getPosition() {
		return position;
	}

	public void setPosition(EmployeePosition position) {
		this.position = position;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
