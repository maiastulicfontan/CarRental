package com.solvd.carRental.models;


import java.time.LocalDate;

public class DriverLicense {
	private Long id;
	private Customer customer;
	private Long number;
	private LocalDate validFrom;
	private LocalDate expiration;
	
	public DriverLicense () {}
	
	public DriverLicense(Long id, Customer customer, Long number, LocalDate validFrom, LocalDate expirationDate) {
		this.id = id;
		this.customer = customer;
		this.number = number;
		this.validFrom = validFrom;
		this.expiration = expirationDate;
	}

	public DriverLicense(Long id, Long number, LocalDate validFrom, LocalDate expirationDate) {
		super();
		this.id = id;
		this.number = number;
		this.validFrom = validFrom;
		this.expiration = expirationDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public LocalDate getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(LocalDate validFrom) {
		this.validFrom = validFrom;
	}

	public LocalDate getExpiration() {
		return expiration;
	}

	public void setExpiration(LocalDate expirationDate) {
		this.expiration = expirationDate;
	}
}
