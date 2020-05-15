package com.solvd.carRental.models;

public class ReservationStatus {
	private long id;
	private String name;
	private String description;
	
	public ReservationStatus(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public ReservationStatus(long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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
}
