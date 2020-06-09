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
import com.solvd.carRental.models.CarType;
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
import com.solvd.carRental.services.CarBrandService;
import com.solvd.carRental.services.CarTypeService;
import com.solvd.carRental.services.CustomerService;

public class CarRentalRunner {
	private static Logger LOGGER = LogManager.getLogger(CarRentalRunner.class);
	
	public static void main(String[] args) {
		EmployeeJaxbParser empJaxbParser = new EmployeeJaxbParser();
		
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
		
		
		
		
		
		// ------------ JAXB parser ---------
		/*LOGGER.info(empJaxbParser.jaxbXmlToEmployee()); //this method retrieves only one object and doesn't work with the tag "employees"
		LOGGER.info("Employees from XML file: "+GenericJaxbListParser.jabxXmlToObjectList(Employee.class, "src/main/resources/employees.xml"));
		
		List<CarModel> carModels = new ArrayList<CarModel>();
		carModels = GenericJaxbListParser.jabxXmlToObjectList(CarModel.class, "src/main/resources/car-models.xml");
		LOGGER.info("Car models from XML file: "+ carModels);
		
		
		List<Car> cars = new ArrayList<Car>();
		cars = GenericJaxbListParser.jabxXmlToObjectList(Car.class, "src/main/resources/cars.xml");
		LOGGER.info("Cars from XML file: "+cars);
		GenericJaxbListParser.jabxObjectListToXml(Car.class, cars, "src/main/resources/cars-output.xml", "cars");
		
		//---------- JSON parser ------------
		List<Car> carsJson = new ArrayList<Car>();
		carsJson = GenericJsonParser.jsonToObjectList(Car.class, "src/main/resources/cars.json");
		LOGGER.info("Cars from JSON file:"+carsJson);
		
		GenericJsonParser.objectListToJson(carsJson, "src/main/resources/cars-output.json");
		
		List<Employee> employeesJson = new ArrayList<Employee>();
		employeesJson = GenericJsonParser.jsonToObjectList(Employee.class, "src/main/resources/employees.json");
		LOGGER.info("Employees from JSON file: "+employeesJson);
		LOGGER.info(employeesJson.get(0).getBirthDate());
		
		GenericJsonParser.objectListToJson(employeesJson, "src/main/resources/employees-output.json");*/
		
		// ---------- MyBatis ----------
		CarBrandService carBrandService = new CarBrandService();
		LOGGER.info(carBrandService.getAllCarBrands());
		
		//CarTypeService carTypeService = new CarTypeService ();
		//CarType carType = new CarType ("American Collection", "From the East to the West Coast, fun is guaranteed with the best of our all-american cars");
		//carTypeService.saveCarType(carType);
		
		
	}

}
