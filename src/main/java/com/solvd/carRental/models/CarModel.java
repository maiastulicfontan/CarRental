package com.solvd.carRental.models;

import javax.xml.bind.annotation.XmlAttribute;  
import javax.xml.bind.annotation.XmlElement;  
import javax.xml.bind.annotation.XmlRootElement;  

@XmlRootElement (name = "car-model")
public class CarModel {
	private Long id;
	private String name;
	private String description;
	private String transmission;
	private Integer numberOfSeats;
	private String airbagInfo;
	private String luggageSpace;
	private String fuelConsumption;
	private CarBrand brand;
	private CarType type;
	private Double costPerDay;
	
	public CarModel() {}
	
	public CarModel(Long id, String name, String transmission, Integer numberOfSeats, String airbagInfo,
			String luggageSpace, String fuelConsumption, CarBrand brand, CarType type, Double costPerDay) {
		this.id = id;
		this.name = name;
		this.transmission = transmission;
		this.numberOfSeats = numberOfSeats;
		this.airbagInfo = airbagInfo;
		this.luggageSpace = luggageSpace;
		this.fuelConsumption = fuelConsumption;
		this.brand = brand;
		this.type = type;
		this.costPerDay = costPerDay;
	}


	public CarModel(Long id, String name, String description, String transmission, Integer numberOfSeats, String airbagInfo,
			String luggageSpace, String fuelConsumption, CarBrand brand, CarType type, Double costPerDay) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.transmission = transmission;
		this.numberOfSeats = numberOfSeats;
		this.airbagInfo = airbagInfo;
		this.luggageSpace = luggageSpace;
		this.fuelConsumption = fuelConsumption;
		this.brand = brand;
		this.type = type;
		this.costPerDay = costPerDay;
	}


	public CarModel(Long id, String name, String description, String transmission, Integer numberOfSeats, String airbagInfo,
			String luggageSpace, String fuelConsumption, Double costPerDay) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.transmission = transmission;
		this.numberOfSeats = numberOfSeats;
		this.airbagInfo = airbagInfo;
		this.luggageSpace = luggageSpace;
		this.fuelConsumption = fuelConsumption;
		this.costPerDay = costPerDay;
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

	@XmlElement
	public String getTransmission() {
		return transmission;
	}


	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	@XmlElement (name = "number-of-seats")
	public Integer getNumberOfSeats() {
		return numberOfSeats;
	}


	public void setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	@XmlElement (name = "airbag-info")
	public String getAirbagInfo() {
		return airbagInfo;
	}


	public void setAirbagInfo(String airbagInfo) {
		this.airbagInfo = airbagInfo;
	}

	@XmlElement (name = "luggage-space")
	public String getLuggageSpace() {
		return luggageSpace;
	}


	public void setLuggageSpace(String luggageSpace) {
		this.luggageSpace = luggageSpace;
	}

	@XmlElement (name = "fuel-consumption")
	public String getFuelConsumption() {
		return fuelConsumption;
	}


	public void setFuelConsumption(String fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}

	@XmlElement (name = "carbrand")
	public CarBrand getBrand() {
		return brand;
	}


	public void setBrand(CarBrand brand) {
		this.brand = brand;
	}

	@XmlElement (name = "car-type")
	public CarType getType() {
		return type;
	}


	public void setType(CarType type) {
		this.type = type;
	}

	@XmlElement (name = "cost-per-day")
	public Double getCostPerDay() {
		return costPerDay;
	}


	public void setCostPerDay(Double costPerDay) {
		this.costPerDay = costPerDay;
	}

	@Override
	public String toString() {
		return "CarModel [id=" + id + ", name= " + name + ", brand = "+ this.getBrand().getName() +", description=" + description + ", transmission="
				+ transmission + ", numberOfSeats=" + numberOfSeats + ", airbagInfo=" + airbagInfo + ", luggageSpace="
				+ luggageSpace + ", fuelConsumption=" + fuelConsumption + ", type =" + this.getType().getName() + ", costPerDay=" + costPerDay + "]";
	}
	
}
