package com.solvd.carRental.models;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private Person person;
	private String emailAddress;
	private List <CreditCard> creditCards = new ArrayList<CreditCard>();
	
	public Customer(Person person, String emailAddress) {
		this.person = person;
		this.emailAddress = emailAddress;
	}

	public Customer(String emailAddress) {
		super();
		this.emailAddress = emailAddress;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
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
	
	
}
