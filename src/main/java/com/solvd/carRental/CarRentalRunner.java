package com.solvd.carRental;


import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.carRental.dao.mysqlimpl.CarBrandDAO;
import com.solvd.carRental.dao.mysqlimpl.CreditCardDAO;
import com.solvd.carRental.dao.mysqlimpl.CustomerDAO;
import com.solvd.carRental.models.Car;
import com.solvd.carRental.models.CarBrand;
import com.solvd.carRental.models.CarModel;
import com.solvd.carRental.models.Customer;
import com.solvd.carRental.models.Employee;
import com.solvd.carRental.parsers.jaxb.CarJaxbParser;
import com.solvd.carRental.parsers.jaxb.CarModelJaxbParser;
import com.solvd.carRental.parsers.jaxb.EmployeeJaxbParser;
import com.solvd.carRental.parsers.jaxb.GenericJaxbListParser;
import com.solvd.carRental.parsers.jaxb.GenericJaxbParser;
import com.solvd.carRental.parsers.json.GenericJsonParser;
import com.solvd.carRental.parsers.xmldomparser.CarModelParser;
import com.solvd.carRental.parsers.xmldomparser.CarParser;
import com.solvd.carRental.parsers.xmldomparser.EmployeeParser;
import com.solvd.carRental.services.CustomerService;

public class CarRentalRunner {
	private static Logger LOGGER = LogManager.getLogger(CarRentalRunner.class);
	
	public static void main(String[] args) {
		EmployeeJaxbParser empJaxbParser = new EmployeeJaxbParser();
		CarJaxbParser carJaxbParser = new CarJaxbParser();
		CarModelJaxbParser carModelJaxbParser = new CarModelJaxbParser();
		
		//generic jaxb parser for employee
		GenericJaxbParser<Employee> genericEmpParser = new GenericJaxbParser<Employee>();
		
		//generic jaxb parser for list of cars
		GenericJaxbListParser<Car> genericCarListParser = new GenericJaxbListParser<Car>();
		
		//generic json parser for cars
		GenericJsonParser<Car> carJsonParser = new GenericJsonParser<Car>();
		
		CustomerDAO custDao = new CustomerDAO();
		CustomerService custService = new CustomerService();
		CreditCardDAO cardDao = new CreditCardDAO();
		CarBrandDAO carBrandDao = new CarBrandDAO();
		/*Customer cust = (custService.getCustomerById(2L));
		
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
		LOGGER.info(carModels);*/
		
		
		
		//LOGGER.info(carJaxbParser.jaxbXmlToCar());
		
		//LOGGER.info(carModelJaxbParser.jaxbXmlToCarModel());
		
		
		LOGGER.info(empJaxbParser.jaxbXmlToEmployee());
		LOGGER.info(genericEmpParser.jabxXmlToObject(Employee.class, "src/main/resources/employees.xml"));
		
		List<Car> cars = new ArrayList<Car>();
		cars = genericCarListParser.jabxXmlToObject(Car.class, "src/main/resources/cars.xml");
		LOGGER.info("Cars from XML file: "+cars);
		
		//genericCarListParser.jabxObjectToXml(Car.class, cars, "src/main/resources/cars-output.xml", "cars");
		
		List<Car> carsJson = new ArrayList<Car>();
		carsJson = carJsonParser.jsonToObjectList("src/main/resources/cars.json");
		LOGGER.info("Cars from JSON file:"+carsJson);
		
		carJsonParser.objectListToJson(carsJson, "src/main/resources/cars-output.json");
	}

}
