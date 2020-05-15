package com.solvd.carRental.models;

public class Rental {
	private Long id;
	private Reservation reservation;
	private Employee employee;
	
	public Rental(Long id, Reservation reservation, Employee employee) {
		this.id = id;
		this.reservation = reservation;
		this.employee = employee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
