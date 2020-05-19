package com.solvd.carRental.models;

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


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getLicensePlate() {
		return licensePlate;
	}


	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}


	public Integer getModelYear() {
		return modelYear;
	}


	public void setModelYear(Integer modelYear) {
		this.modelYear = modelYear;
	}


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
	
	
	
	
}
