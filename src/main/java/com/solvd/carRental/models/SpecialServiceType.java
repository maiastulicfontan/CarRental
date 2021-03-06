package com.solvd.carRental.models;

public class SpecialServiceType {
	private Long id;
	private String name;
	private String description;
	private Double cost;
	
	public SpecialServiceType () {}
	
	public SpecialServiceType(Long id, String name, Double cost) {
		this.id = id;
		this.name = name;
		this.cost = cost;
	}

	public SpecialServiceType(Long id, String name, String description, Double cost) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.cost = cost;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}
}
