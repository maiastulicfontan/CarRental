package com.solvd.carRental;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.carRental.dao.mysqlimpl.CarBrandDAO;
import com.solvd.carRental.dao.mysqlimpl.CreditCardDAO;
import com.solvd.carRental.dao.mysqlimpl.CustomerDAO;
import com.solvd.carRental.jaxb.CarJaxbParser;
import com.solvd.carRental.jaxb.CarModelJaxbParser;
import com.solvd.carRental.jaxb.EmployeeJaxbParser;
import com.solvd.carRental.models.Car;
import com.solvd.carRental.models.CarBrand;
import com.solvd.carRental.models.CarModel;
import com.solvd.carRental.models.Customer;
import com.solvd.carRental.models.Employee;
import com.solvd.carRental.services.CustomerService;
import com.solvd.carRental.xmldomparser.CarModelParser;
import com.solvd.carRental.xmldomparser.CarParser;
import com.solvd.carRental.xmldomparser.EmployeeParser;

public class CarRentalRunner {
	private static Logger LOGGER = LogManager.getLogger(CarRentalRunner.class);
	
	public static void main(String[] args) {
		EmployeeJaxbParser empJaxbParser = new EmployeeJaxbParser();
		CarJaxbParser carJaxbParser = new CarJaxbParser();
		CarModelJaxbParser carModelJaxbParser = new CarModelJaxbParser();
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
		
		
		EmployeeParser empParser = new EmployeeParser();
		List<Employee> employees = empParser.parseEmployees();
		LOGGER.info(employees.get(0).getPosition());
		
		CarParser carParser = new CarParser();
		List<Car> cars = carParser.parseCars();
		LOGGER.info(cars);
		
		CarModelParser carModParser = new CarModelParser();
		List<CarModel> carModels = carModParser.parseCarModels();
		LOGGER.info(carModels);
		
		LOGGER.info(empJaxbParser.jaxbXmlToEmployee());
		
		LOGGER.info(carJaxbParser.jaxbXmlToCar());
		
		LOGGER.info(carModelJaxbParser.jaxbXmlToCarModel());
	}

}
