package com.solvd.carRental.models;

import java.util.ArrayList;
import java.util.List;

public abstract class BusinessEntity {
	private Long id;
	private List <Address> addresses = new ArrayList<Address>();
	private List <PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
	
	public BusinessEntity () {}
	
	public BusinessEntity(long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	
}
