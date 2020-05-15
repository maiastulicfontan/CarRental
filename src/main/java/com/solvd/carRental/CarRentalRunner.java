package com.solvd.carRental;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.carRental.dao.mysqlimpl.CarDAO;
import com.solvd.carRental.models.Car;
import com.solvd.carRental.services.CustomerService;

public class CarRentalRunner {
	private static Logger LOGGER = LogManager.getLogger(CarRentalRunner.class);
	
	public static void main(String[] args) {
		CarDAO carDao = new CarDAO();
		CustomerService custService = new CustomerService();
		
		LOGGER.info(custService.getCustomerById(new Long(1)));
		
		Car car = new Car (new Long(5), "ABC1234", 2017, "Electric blue");
		carDao.saveEntity(car);
		
		carDao.getAll().forEach(carTmp -> LOGGER.info(carTmp));
		
		car.setColor("Dark blue");
		
		carDao.updateEntityById(car);
		
		carDao.getAll().forEach(carTmp -> LOGGER.info(carTmp));
		
		carDao.deleteEntityById(car.getId());
		
		carDao.getAll().forEach(carTmp -> LOGGER.info(carTmp));
	}

}
