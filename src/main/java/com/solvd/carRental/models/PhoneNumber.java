package com.solvd.carRental.models;

import javax.xml.bind.annotation.XmlElement;  
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;

@XmlRootElement(name ="phone-number")
public class PhoneNumber {
	private Long id;
	private String number;
	
	public PhoneNumber() {}
	
	public PhoneNumber(Long id, String number) {
		this.id = id;
		this.number = number;
	}
	
	@XmlAttribute
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@XmlElement
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "PhoneNumber [number=" + number + "]";
	}
	
	
}
