package com.solvd.carRental.models;

import java.time.LocalDate; 
import javax.xml.bind.annotation.XmlElement;  
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.solvd.carRental.parsers.jaxb.adapters.LocalDateAdapter;  

@XmlRootElement (name = "employee")
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
	
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	@XmlElement (name = "hire-date")
	public LocalDate getHireDate() {
		return hireDate;
	}
	
	@JsonDeserialize(using= LocalDateDeserializer.class)
	@JsonSerialize(using= LocalDateSerializer.class)
	@JsonSetter("hire-date")
	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}
	
	@XmlElement
	public EmployeePosition getPosition() {
		return position;
	}
	
	@JsonSetter("position")
	public void setPosition(EmployeePosition position) {
		this.position = position;
	}
	
	@Override
	public String toString() {
		return "Employee [id: " + getId() + ", First Name: " + getFirstName() + ", Last Name: "
				+ getLastName() + ", Birth Date: " + getBirthDate() + ", National Government Id: "
				+ getNationalGovernmentId() + ", Hire Date: " + hireDate + ", Phone Numbers: "+ this.getPhoneNumbers()+ "]";
	
	}

}
