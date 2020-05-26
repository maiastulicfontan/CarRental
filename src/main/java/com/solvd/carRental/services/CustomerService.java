package com.solvd.carRental.services;


import java.util.ArrayList;
import java.util.List;

import com.solvd.carRental.dao.IAddressDAO;
import com.solvd.carRental.dao.ICreditCardDAO;
import com.solvd.carRental.dao.IDriverLicenseDAO;
import com.solvd.carRental.dao.IEntityDAO;
import com.solvd.carRental.dao.IPhoneNumberDAO;
import com.solvd.carRental.dao.mysqlimpl.AddressDAO;
import com.solvd.carRental.dao.mysqlimpl.CreditCardDAO;
import com.solvd.carRental.dao.mysqlimpl.CustomerDAO;
import com.solvd.carRental.dao.mysqlimpl.DriverLicenseDAO;
import com.solvd.carRental.dao.mysqlimpl.PhoneNumberDAO;
import com.solvd.carRental.models.Customer;

public class CustomerService {
	private IEntityDAO<Customer> customerDao;
	private ICreditCardDAO creditCardDao;
	private IAddressDAO addressDao;
	private IPhoneNumberDAO phoneNumberDao;
	private IDriverLicenseDAO driverLicenseDao;

	
	public CustomerService() {
		this.customerDao = new CustomerDAO();
		this.creditCardDao = new CreditCardDAO();
		this.addressDao = new AddressDAO();
		this.phoneNumberDao = new PhoneNumberDAO();
		this.driverLicenseDao = new DriverLicenseDAO();
	}
	
	public Customer getCustomerById (Long id) {
		Customer customer = customerDao.getEntityById(id);
		this.buildCustomer(customer);
		return customer;
	}
	
	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		customers = customerDao.getAll();
		customers.forEach (tmpCust -> this.buildCustomer(tmpCust));
		return customers;
	}
	
	public void buildCustomer (Customer customer) {
		customer.setCreditCards(creditCardDao.getAllByCustomerId(customer.getId()));
		customer.setAddresses(addressDao.getAllByBusinessEntityId(customer.getId()));
		customer.setPhoneNumbers(phoneNumberDao.getAllByBusinessEntityId(customer.getId()));
		customer.setLicense(driverLicenseDao.getByCustomerId(customer.getId()));
	}
}
