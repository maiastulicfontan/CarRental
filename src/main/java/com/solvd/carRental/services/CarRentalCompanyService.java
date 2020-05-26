package com.solvd.carRental.services;

import com.solvd.carRental.dao.IEntityDAO;
import com.solvd.carRental.dao.ILocationDAO;
import com.solvd.carRental.dao.mysqlimpl.CarRentalCompanyDAO;
import com.solvd.carRental.dao.mysqlimpl.LocationDAO;
import com.solvd.carRental.models.CarRentalCompany;

public class CarRentalCompanyService {
	private ILocationDAO locationDao;
	private IEntityDAO<CarRentalCompany> carRentalCompanyDao;
	
	public CarRentalCompanyService() {
		this.locationDao = new LocationDAO();
		this.carRentalCompanyDao = new CarRentalCompanyDAO();
	}
	
	public CarRentalCompany getCarRentalCompanyById (Long id) {
		CarRentalCompany carRentalCompany = carRentalCompanyDao.getEntityById (id);
		this.buildCarRentalCompany(carRentalCompany);
		return carRentalCompany;
	}
	
	public void buildCarRentalCompany (CarRentalCompany carRentalCompany) {
		carRentalCompany.setLocations(locationDao.getAllByCarRentalCompanyId(carRentalCompany.getId()));
	}
}
