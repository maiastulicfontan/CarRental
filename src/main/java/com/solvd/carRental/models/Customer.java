package com.solvd.carRental.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
	private String emailAddress;
	private List <CreditCard> creditCards = new ArrayList<CreditCard>();
	private DriverLicense license;
	
	public Customer () {}
	
	public Customer(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Customer(Long id, String firstName, String lastName, LocalDate birthDate, String nationalGovernmentId, String emailAddress) {
		super(id, firstName, lastName, birthDate, nationalGovernmentId);
		this.emailAddress = emailAddress;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public List<CreditCard> getCreditCards() {
		return creditCards;
	}

	public void setCreditCards(List<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}

	public DriverLicense getLicense() {
		return license;
	}

	public void setLicense(DriverLicense license) {
		this.license = license;
	}

	@Override
	public String toString() {
		return "Customer [id: " + getId() + ", First Name: " + getFirstName() + ", Last Name: "
				+ getLastName() + ", Email Address: " + emailAddress + "]";
	}
}
