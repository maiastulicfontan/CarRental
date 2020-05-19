package com.solvd.carRental.models;

import java.util.ArrayList;
import java.util.List;

public class CarRentalCompany {
	private Long id;
	private String name;
	private List<Location> locations = new ArrayList<Location>();
	
	
	public CarRentalCompany () {}
	
	public CarRentalCompany(Long id, String name) {
		this.id = id;
		this.name = name;
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


	public List<Location> getLocations() {
		return locations;
	}


	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

}
