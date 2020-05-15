package com.solvd.carRental.models;

public class CreditCard {
	private Long id;
	private Integer number;
	private String validFrom;
	private String expiration;
	private CreditCardType type;
	
	public CreditCard(Long id, Integer number, String validFrom, String expiration, CreditCardType type) {
		this.id = id;
		this.number = number;
		this.validFrom = validFrom;
		this.expiration = expiration;
		this.type = type;
	}

	public CreditCard(Long id, Integer number, String validFrom, String expiration) {
		super();
		this.id = id;
		this.number = number;
		this.validFrom = validFrom;
		this.expiration = expiration;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	public CreditCardType getType() {
		return type;
	}

	public void setType(CreditCardType type) {
		this.type = type;
	}
}
	
	
