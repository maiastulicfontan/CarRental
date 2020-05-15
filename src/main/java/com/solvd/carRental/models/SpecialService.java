package com.solvd.carRental.models;

public class SpecialService {
	private long id;
	private String name;
	private SpecialServiceType type;
	
	public SpecialService(long id, String name, SpecialServiceType type) {
		this.id = id;
		this.name = name;
		this.type = type;
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

	public SpecialServiceType getType() {
		return type;
	}

	public void setType(SpecialServiceType type) {
		this.type = type;
	}
	
}
