package com.solvd.carRental.services;

import java.util.ArrayList;
import java.util.List;

import com.solvd.carRental.dao.ICarTypeDAO;
import com.solvd.carRental.models.CarType;
import com.solvd.carRental.mybatis.MyBatisConnectionFactory;

public class CarTypeService {
	private ICarTypeDAO carTypeDao;
	
	public CarTypeService() {
		carTypeDao = MyBatisConnectionFactory.getSqlSessionFactory().openSession(true).getMapper(ICarTypeDAO.class);
	}
	
	public CarType getCarTypeById (Long id) {
		return carTypeDao.getEntityById(id);
	}
	
	public List<CarType> getAllCarTypes (){
		List<CarType> carTypes = new ArrayList<CarType>();
		carTypes = carTypeDao.getAll();
		return carTypes;
 	}
	
	public void saveCarType (CarType carType) {
		carTypeDao.saveEntity(carType);
	}
	
	public void deleteCarTypeById (Long id) {
		carTypeDao.deleteEntityById(id);
	}
}
