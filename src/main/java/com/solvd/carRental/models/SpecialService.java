package com.solvd.carRental.models;

public class SpecialService {
	private Long id;
	private String name;
	private SpecialServiceType type;
	
	public SpecialService() {}
	
	public SpecialService(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public SpecialService(Long id, String name, SpecialServiceType type) {
		this.id = id;
		this.name = name;
		this.type = type;
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

	public SpecialServiceType getType() {
		return type;
	}

	public void setType(SpecialServiceType type) {
		this.type = type;
	}
	
}
