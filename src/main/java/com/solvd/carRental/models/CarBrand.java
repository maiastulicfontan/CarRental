package com.solvd.carRental.models;

import javax.xml.bind.annotation.XmlElement;  
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;

@XmlRootElement (name ="carbrand")
public class CarBrand {
	private Long id;
	private String name;
	
	public CarBrand () {}
	
	public CarBrand(String name) {
		this.name = name;
	}

	public CarBrand(Long id, String name) {
		this.id = id;
		this.name = name;
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

	@Override
	public String toString() {
		return "CarBrand [id=" + id + ", name=" + name + "]";
	}
	
}
