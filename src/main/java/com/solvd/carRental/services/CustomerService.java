package com.solvd.carRental.services;


import com.solvd.carRental.dao.mysqlimpl.CreditCardDAO;
import com.solvd.carRental.dao.mysqlimpl.CustomerDAO;
import com.solvd.carRental.models.Customer;

public class CustomerService {
	private CustomerDAO customerDao;
	private CreditCardDAO creditCardDao;

	
	public CustomerService() {
		this.customerDao = new CustomerDAO();
		this.creditCardDao = new CreditCardDAO();
	}
	
	public Customer getCustomerById (Long id) {
		Customer customer = customerDao.getEntityById(id);
		customer.setCreditCards(creditCardDao.getAllByCustomerId(id));
		return customer;
	}
}
