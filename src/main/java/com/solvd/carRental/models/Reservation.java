package com.solvd.carRental.models;


import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservation {
	private Long id;
	private LocalDateTime pickupDateTime;
	private LocalDateTime returnDateTime;
	private Double cost;
	private Long confirmationNumber;
	private LocalDate creationDate;
	private Location pickupLocation;
	private Location returnLocation;
	private Customer customer;
	private Car car;
	private ReservationStatus status;
	private List<SpecialService> specialServices = new ArrayList<SpecialService>();
	
	public Reservation () {}
	
	
	public Reservation(Long id, LocalDateTime pickupDateTime, LocalDateTime returnDateTime, Double cost,
			Long confirmationNumber, LocalDate creationDate) {
		this.id = id;
		this.pickupDateTime = pickupDateTime;
		this.returnDateTime = returnDateTime;
		this.cost = cost;
		this.confirmationNumber = confirmationNumber;
		this.creationDate = creationDate;
	}


	public Reservation(Long id, LocalDateTime pickupDateTime, LocalDateTime returnDateTime, Double cost,
			Long confirmationNumber, LocalDate creationDate, Location pickupLocation, Customer customer, Car car,
			ReservationStatus status) {
		this.id = id;
		this.pickupDateTime = pickupDateTime;
		this.returnDateTime = returnDateTime;
		this.cost = cost;
		this.confirmationNumber = confirmationNumber;
		this.creationDate = creationDate;
		this.pickupLocation = pickupLocation;
		this.customer = customer;
		this.car = car;
		this.status = status;
	}


	public Reservation(Long id, LocalDateTime pickupDateTime, LocalDateTime returnDateTime, Double cost,
			Long confirmationNumber, LocalDate creationDate, Location pickupLocation, Location returnLocation,
			Customer customer, Car car, ReservationStatus status) {
		super();
		this.id = id;
		this.pickupDateTime = pickupDateTime;
		this.returnDateTime = returnDateTime;
		this.cost = cost;
		this.confirmationNumber = confirmationNumber;
		this.creationDate = creationDate;
		this.pickupLocation = pickupLocation;
		this.returnLocation = returnLocation;
		this.customer = customer;
		this.car = car;
		this.status = status;
	}


	public long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDateTime getPickupDateTime() {
		return pickupDateTime;
	}


	public void setPickupDateTime(LocalDateTime pickupDateTime) {
		this.pickupDateTime = pickupDateTime;
	}


	public LocalDateTime getReturnDateTime() {
		return returnDateTime;
	}


	public void setReturnDateTime(LocalDateTime returnDateTime) {
		this.returnDateTime = returnDateTime;
	}


	public Double getCost() {
		return cost;
	}


	public void setCost(Double cost) {
		this.cost = cost;
	}


	public Long getConfirmationNumber() {
		return confirmationNumber;
	}


	public void setConfirmationNumber(Long confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}


	public LocalDate getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}


	public Location getPickupLocation() {
		return pickupLocation;
	}


	public void setPickupLocation(Location pickupLocation) {
		this.pickupLocation = pickupLocation;
	}


	public Location getReturnLocation() {
		return returnLocation;
	}


	public void setReturnLocation(Location returnLocation) {
		this.returnLocation = returnLocation;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Car getCar() {
		return car;
	}


	public void setCar(Car car) {
		this.car = car;
	}


	public ReservationStatus getStatus() {
		return status;
	}


	public void setStatus(ReservationStatus status) {
		this.status = status;
	}


	public List<SpecialService> getSpecialServices() {
		return specialServices;
	}


	public void setSpecialServices(List<SpecialService> specialServices) {
		this.specialServices = specialServices;
	}
	
}
