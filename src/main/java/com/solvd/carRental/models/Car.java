package com.solvd.carRental.models;

import javax.xml.bind.annotation.XmlAttribute;  
import javax.xml.bind.annotation.XmlElement;  
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonSetter; 

@XmlRootElement (name="car")
public class Car {
	private Long id;
	private String licensePlate;
	private Integer modelYear;
	private String color;
	private CarModel model;
	
	public Car() {}
	
	public Car(Long id, String licensePlate, CarModel model) {
		this.id = id;
		this.licensePlate = licensePlate;
		this.model = model;
	}


	public Car(Long id, String licensePlate, Integer modelYear, String color, CarModel model) {
		this.id = id;
		this.licensePlate = licensePlate;
		this.modelYear = modelYear;
		this.color = color;
		this.model = model;
	}
	
	public Car(Long id, String licensePlate, Integer modelYear, String color) {
		this.id = id;
		this.licensePlate = licensePlate;
		this.modelYear = modelYear;
		this.color = color;
	}

	@XmlAttribute
	public Long getId() {
		return id;
	}

	@JsonSetter("id")
	public void setId(Long id) {
		this.id = id;
	}

	@XmlElement (name="license-plate")
	public String getLicensePlate() {
		return licensePlate;
	}

	@JsonSetter("license-plate")
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	@XmlElement (name="model-year")
	public Integer getModelYear() {
		return modelYear;
	}

	@JsonSetter("model-year")
	public void setModelYear(Integer modelYear) {
		this.modelYear = modelYear;
	}

	@XmlElement (name="color")
	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public CarModel getModel() {
		return model;
	}


	public void setModel(CarModel model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "Car [id: " + id + ", License Plate: " + licensePlate + ", Model Year: " + modelYear + ", Color: " + color
				+ "]";
	}
	
	
	
	
}
