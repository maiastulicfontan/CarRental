package com.solvd.carRental.models;

import java.util.ArrayList;
import java.util.List;

public class Location {
	private BusinessEntity be;
	private String name;
	private String hoursOfOperation;
	private String additionalInformation;
	private List<Employee> employees = new ArrayList<Employee>();
	private List<Car> cars = new ArrayList<Car>();
	
	public Location(BusinessEntity be, String name) {
		this.be = be;
		this.name = name;
	}

	public Location(BusinessEntity be, String name, String hoursOfOperation, String additionalInformation) {
		this.be = be;
		this.name = name;
		this.hoursOfOperation = hoursOfOperation;
		this.additionalInformation = additionalInformation;
	}

	public Location(String name, String hoursOfOperation, String additionalInformation) {
		super();
		this.name = name;
		this.hoursOfOperation = hoursOfOperation;
		this.additionalInformation = additionalInformation;
	}

	public BusinessEntity getBe() {
		return be;
	}

	public void setBe(BusinessEntity be) {
		this.be = be;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHoursOfOperation() {
		return hoursOfOperation;
	}

	public void setHoursOfOperation(String hoursOfOperation) {
		this.hoursOfOperation = hoursOfOperation;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
	
}
