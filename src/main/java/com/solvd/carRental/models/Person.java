package com.solvd.carRental.models;

import java.sql.Date;


public class Person {
	private Long id;
	private BusinessEntity be;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String nationalGovernmentId;
	
	public Person () {}
	
	public Person(Long id, String firstName, String lastName, Date birthDate, String nationalGovernmentId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.nationalGovernmentId = nationalGovernmentId;
	}

	public Person(BusinessEntity be, String firstName, String lastName, Date birthDate,String nationalGovernmentId) {
		this.be = be;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.nationalGovernmentId = nationalGovernmentId;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BusinessEntity getBe() {
		return be;
	}


	public void setBe(BusinessEntity be) {
		this.be = be;
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


	public Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public String getNationalGovernmentId() {
		return nationalGovernmentId;
	}


	public void setNationalGovernmentId(String nationalGovernmentId) {
		this.nationalGovernmentId = nationalGovernmentId;
	}
}
