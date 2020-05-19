package com.solvd.carRental.models;

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

	@Override
	public String toString() {
		return "CarBrand [id=" + id + ", name=" + name + "]";
	}
	
}
