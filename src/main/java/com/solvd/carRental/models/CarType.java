package com.solvd.carRental.models;

import javax.xml.bind.annotation.XmlElement;  
import javax.xml.bind.annotation.XmlAttribute; 
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "car-type")
public class CarType {
	private Long id;
	private String name;
	private String description;
	
	public CarType() {}
	
	public CarType(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public CarType(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public CarType(String name, String description) {

		this.name = name;
		this.description = description;
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

	@Override
	public String toString() {
		return "CarType [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
}
