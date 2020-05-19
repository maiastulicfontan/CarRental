package com.solvd.carRental.services;


import java.util.ArrayList;
import java.util.List;

import com.solvd.carRental.dao.mysqlimpl.CreditCardDAO;
import com.solvd.carRental.dao.mysqlimpl.CustomerDAO;
import com.solvd.carRental.models.Customer;

public class CustomerService {
	private CustomerDAO customerDao;
	private CreditCardDAO creditCardDao;
	//private PersonDAO personDao;

	
	public CustomerService() {
		this.customerDao = new CustomerDAO();
		this.creditCardDao = new CreditCardDAO();
	}
	
	public Customer getCustomerById (Long id) {
		Customer customer = customerDao.getEntityById(id);
		this.update(customer);
		return customer;
	}
	
	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		customers = customerDao.getAll();
		customers.forEach (tmpCust -> tmpCust.setCreditCards(creditCardDao.getAllByCustomerId(tmpCust.getId())));
		return customers;
	}
	
	public void update (Customer customer) {
		customer.setCreditCards(creditCardDao.getAllByCustomerId(customer.getId()));	
	}
}
