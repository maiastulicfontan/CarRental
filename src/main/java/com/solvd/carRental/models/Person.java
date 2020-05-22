package com.solvd.carRental.models;

import java.time.LocalDate;


public abstract class Person extends BusinessEntity {
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String nationalGovernmentId;
	
	public Person () {}
	
	public Person (Long id) {
		super(id);
	}
	
	public Person(Long id, String firstName, String lastName, LocalDate birthDate, String nationalGovernmentId) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.nationalGovernmentId = nationalGovernmentId;
	}

	public Person(String firstName, String lastName, LocalDate birthDate,String nationalGovernmentId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.nationalGovernmentId = nationalGovernmentId;
	}

	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public LocalDate getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}


	public String getNationalGovernmentId() {
		return nationalGovernmentId;
	}


	public void setNationalGovernmentId(String nationalGovernmentId) {
		this.nationalGovernmentId = nationalGovernmentId;
	}
}
