package com.solvd.carRental.models;

public class SpecialServiceType {
	private long id;
	private String name;
	private String description;
	private double cost;
	
	public SpecialServiceType(long id, String name, double cost) {
		this.id = id;
		this.name = name;
		this.cost = cost;
	}

	public SpecialServiceType(long id, String name, String description, double cost) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.cost = cost;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
}
