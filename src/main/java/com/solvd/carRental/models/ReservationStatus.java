package com.solvd.carRental.models;

public class ReservationStatus {
	private Long id;
	private String name;
	private String description;
	
	public ReservationStatus() {}
	
	public ReservationStatus(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public ReservationStatus(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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
}
