package com.solvd.carRental.models;

import java.sql.Date;

public class DriverLicense {
	private Long id;
	private Customer customer;
	private Long number;
	private Date validFrom;
	private Date expiration;
	
	public DriverLicense () {}
	
	public DriverLicense(Long id, Customer customer, Long number, Date validFrom, Date expirationDate) {
		this.id = id;
		this.customer = customer;
		this.number = number;
		this.validFrom = validFrom;
		this.expiration = expirationDate;
	}

	public DriverLicense(Long id, Long number, Date validFrom, Date expirationDate) {
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

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expirationDate) {
		this.expiration = expirationDate;
	}
}
