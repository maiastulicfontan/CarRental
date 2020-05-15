package com.solvd.carRental.models;

public class Address {
	private Long id;
	private String addressFirstLine;
	private String addressSecondLine;
	private String city;
	private String zipCode;
	private Region region;
	
	public Address(Long id, String addressFirstLine, String city, String zipCode, Region region) {
		this.id = id;
		this.addressFirstLine = addressFirstLine;
		this.city = city;
		this.zipCode = zipCode;
		this.region = region;
	}
	
	public Address(Long id, String addressFirstLine, String addressSecondLine, String city, String zipCode,
			Region region) {
		super();
		this.id = id;
		this.addressFirstLine = addressFirstLine;
		this.addressSecondLine = addressSecondLine;
		this.city = city;
		this.zipCode = zipCode;
		this.region = region;
	}

	public Address(Long id, String addressFirstLine, String addressSecondLine, String city, String zipCode) {
		super();
		this.id = id;
		this.addressFirstLine = addressFirstLine;
		this.addressSecondLine = addressSecondLine;
		this.city = city;
		this.zipCode = zipCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddressFirstLine() {
		return addressFirstLine;
	}

	public void setAddressFirstLine(String addressFirstLine) {
		this.addressFirstLine = addressFirstLine;
	}

	public String getAddressSecondLine() {
		return addressSecondLine;
	}

	public void setAddressSecondLine(String addressSecondLine) {
		this.addressSecondLine = addressSecondLine;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

}
