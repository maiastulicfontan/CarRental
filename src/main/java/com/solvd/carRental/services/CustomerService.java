package com.solvd.carRental.services;


import java.util.ArrayList;
import java.util.List;

import com.solvd.carRental.dao.mysqlimpl.AddressDAO;
import com.solvd.carRental.dao.mysqlimpl.CreditCardDAO;
import com.solvd.carRental.dao.mysqlimpl.CustomerDAO;
import com.solvd.carRental.dao.mysqlimpl.DriverLicenseDAO;
import com.solvd.carRental.dao.mysqlimpl.PhoneNumberDAO;
import com.solvd.carRental.models.Customer;

public class CustomerService {
	private CustomerDAO customerDao;
	private CreditCardDAO creditCardDao;
	private AddressDAO addressDao;
	private PhoneNumberDAO phoneNumberDao;
	private DriverLicenseDAO driverLicenseDao;

	
	public CustomerService() {
		this.customerDao = new CustomerDAO();
		this.creditCardDao = new CreditCardDAO();
		this.addressDao = new AddressDAO();
		this.phoneNumberDao = new PhoneNumberDAO();
		this.driverLicenseDao = new DriverLicenseDAO();
	}
	
	public Customer getCustomerById (Long id) {
		Customer customer = customerDao.getEntityById(id);
		this.update(customer);
		return customer;
	}
	
	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		customers = customerDao.getAll();
		customers.forEach (tmpCust -> this.update(tmpCust));
		return customers;
	}
	
	public void update (Customer customer) {
		customer.setCreditCards(creditCardDao.getAllByCustomerId(customer.getId()));
		customer.setAddresses(addressDao.getAllByBusinessEntityId(customer.getId()));
		customer.setPhoneNumbers(phoneNumberDao.getAllByBusinessEntityId(customer.getId()));
		customer.setLicense(driverLicenseDao.getByCustomerId(customer.getId()));
	}
}
