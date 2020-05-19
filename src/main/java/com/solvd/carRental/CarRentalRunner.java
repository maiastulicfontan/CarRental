package com.solvd.carRental;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.carRental.dao.mysqlimpl.CarBrandDAO;
import com.solvd.carRental.dao.mysqlimpl.CreditCardDAO;
import com.solvd.carRental.dao.mysqlimpl.CustomerDAO;
import com.solvd.carRental.models.CarBrand;
import com.solvd.carRental.models.Customer;
import com.solvd.carRental.services.CustomerService;

public class CarRentalRunner {
	private static Logger LOGGER = LogManager.getLogger(CarRentalRunner.class);
	
	public static void main(String[] args) {
		CustomerDAO custDao = new CustomerDAO();
		CustomerService custService = new CustomerService();
		CreditCardDAO cardDao = new CreditCardDAO();
		CarBrandDAO carBrandDao = new CarBrandDAO();
		Customer cust = (custService.getCustomerById(2L));
		
		LOGGER.info(custService.getAllCustomers());
		
		cust.setEmailAddress("johndoe@mail.com");
		
		custDao.updateEntity(cust);
		
		LOGGER.info(custService.getCustomerById(cust.getId()));
		
		LOGGER.info(cardDao.getAllByCustomerId(2L));
		
		CarBrand bmw = new CarBrand ("Toyota");
		
		carBrandDao.saveEntity(bmw);
		
		LOGGER.info(carBrandDao.getAll());
		
		carBrandDao.deleteEntityById(3L);
		
		LOGGER.info(carBrandDao.getAll());
		
		
	}

}
