package com.solvd.carRental.models;

import java.sql.Date;

//import java.time.LocalDate;

public class Payment {
	private Long id;
	private PaymentType type;
	private Date date;
	private Rental rental;
	
	public Payment () {}
	
	public Payment(Long id,Date date) {
		super();
		this.id = id;
		this.date = date;
	}

	public Payment(Long id, PaymentType type,Date date, Rental rental) {
		this.id = id;
		this.type = type;
		this.date = date;
		this.rental = rental;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Rental getRental() {
		return rental;
	}

	public void setRental(Rental rental) {
		this.rental = rental;
	}
}
