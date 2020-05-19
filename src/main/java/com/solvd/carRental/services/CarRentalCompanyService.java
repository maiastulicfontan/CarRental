package com.solvd.carRental.services;

import com.solvd.carRental.dao.mysqlimpl.CarRentalCompanyDAO;
import com.solvd.carRental.dao.mysqlimpl.LocationDAO;
import com.solvd.carRental.models.CarRentalCompany;

public class CarRentalCompanyService {
	private LocationDAO locationDao;
	private CarRentalCompanyDAO carRentalCompanyDao;
	
	public CarRentalCompanyService() {
		this.locationDao = new LocationDAO();
		this.carRentalCompanyDao = new CarRentalCompanyDAO();
	}
	
	public CarRentalCompany getCarRentalCompanyById (Long id) {
		CarRentalCompany carRentalCompany = carRentalCompanyDao.getEntityById (id);
		this.update(carRentalCompany);
		return carRentalCompany;
	}
	
	public void update (CarRentalCompany carRentalCompany) {
		carRentalCompany.setLocations(locationDao.getAllByCarRentalCompanyId(carRentalCompany.getId()));
	}
}