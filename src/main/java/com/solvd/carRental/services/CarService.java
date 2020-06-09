package com.solvd.carRental.services;

import java.util.ArrayList;
import java.util.List;

import com.solvd.carRental.dao.ICarDAO;
import com.solvd.carRental.dao.ICarModelDAO;
import com.solvd.carRental.models.Car;
import com.solvd.carRental.mybatis.MyBatisConnectionFactory;

public class CarService {
	private ICarDAO carDao;
	private ICarModelDAO carModelDao;
	
	public CarService () {
		carDao = MyBatisConnectionFactory.getSqlSessionFactory().openSession(true).getMapper(ICarDAO.class);
		carModelDao = MyBatisConnectionFactory.getSqlSessionFactory().openSession(true).getMapper(ICarModelDAO.class);
	}
	
	public Car getCarById (Long id) {
		Car car = carDao.getEntityById(id);
		this.buildCar(car);
		return car;
	}
	
	public List<Car> getAllCars () {
		List<Car> cars = new ArrayList<Car> (carDao.getAll());
		cars.forEach(c -> this.buildCar(c));
		return cars;
	}
	
	public void saveCar (Car car) {
		carDao.saveEntity(car);
	}
	
	public void deleteCarById (Long id) {
		carDao.deleteEntityById(id);
	}
	
	public void buildCar (Car car) {
		car.setModel(carModelDao.getEntityById(car.getModel().getId()));
	}
}
