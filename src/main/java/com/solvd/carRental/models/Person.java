package com.solvd.carRental.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.solvd.carRental.parsers.jaxb.adapters.LocalDateAdapter; 

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public abstract class Person {
	private Long id;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String nationalGovernmentId;
	private List <Address> addresses = new ArrayList<Address>();
	private List <PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
	
	public Person () {}
	
	public Person (Long id) {
		this.id = id;
	}
	
	public Person(Long id, String firstName, String lastName, LocalDate birthDate, String nationalGovernmentId) {
		this.id = id;
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

	public Long getId() {
		return id;
	}

	@JsonSetter("id")
	public void setId(Long id) {
		this.id = id;
	}


	@XmlElement (name = "first-name")
	public String getFirstName() {
		return firstName;
	}

	@JsonSetter("first-name")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@XmlElement (name = "last-name")
	public String getLastName() {
		return lastName;
	}

	@JsonSetter("last-name")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	@XmlElement (name = "birth-date")
	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	@JsonDeserialize(using= LocalDateDeserializer.class)
	@JsonSerialize(using= LocalDateSerializer.class)
	@JsonSetter("birth-date")
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@XmlElement (name = "national-gvt-id")
	public String getNationalGovernmentId() {
		return nationalGovernmentId;
	}

	@JsonSetter("national-gvt-id")
	public void setNationalGovernmentId(String nationalGovernmentId) {
		this.nationalGovernmentId = nationalGovernmentId;
	}
	

	@XmlElementWrapper (name = "addresses")
	@XmlElement (name = "address")
	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
	@XmlElementWrapper (name = "phone-numbers")
	@XmlElement (name = "phone-number")
	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}
	
	@JsonSetter("phone-numbers")
	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
}
