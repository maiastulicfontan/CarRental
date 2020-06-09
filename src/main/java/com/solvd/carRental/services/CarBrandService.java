package com.solvd.carRental.services;

import java.util.ArrayList;
import java.util.List;

import com.solvd.carRental.dao.ICarBrandDAO;
import com.solvd.carRental.models.CarBrand;
import com.solvd.carRental.mybatis.MyBatisConnectionFactory;

public class CarBrandService {
	private ICarBrandDAO carBrandDao;
	
	public CarBrandService() {
		carBrandDao = MyBatisConnectionFactory.getSqlSessionFactory().openSession(true).getMapper(ICarBrandDAO.class);
	}
	
	public CarBrand getCarBrandById (Long id) {
		return carBrandDao.getEntityById(id);
	}
	
	public List<CarBrand> getAllCarBrands (){
		List<CarBrand> carBrands = new ArrayList<CarBrand>();
		carBrands = carBrandDao.getAll();
		return carBrands;
 	}
	
	public void saveCarBrand (CarBrand carBrand) {
		carBrandDao.saveEntity(carBrand);
	}
	
	public void deleteCarBrandById (Long id) {
		carBrandDao.deleteEntityById(id);
	}
}
