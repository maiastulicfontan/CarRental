package com.solvd.carRental.models;

import java.time.LocalDate;

public class Payment {
	private Long id;
	private PaymentType type;
	private LocalDate date;
	private Rental rental;
	
	public Payment(Long id, PaymentType type, LocalDate date, Rental rental) {
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Rental getRental() {
		return rental;
	}

	public void setRental(Rental rental) {
		this.rental = rental;
	}
}
