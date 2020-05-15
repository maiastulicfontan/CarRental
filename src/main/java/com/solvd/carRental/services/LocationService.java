package com.solvd.carRental.services;

import com.solvd.carRental.dao.mysqlimpl.CarDAO;
import com.solvd.carRental.dao.mysqlimpl.EmployeeDAO;
import com.solvd.carRental.dao.mysqlimpl.LocationDAO;
import com.solvd.carRental.models.Location;

public class LocationService {
	private LocationDAO locationDao;
	private EmployeeDAO employeeDao;
	private CarDAO carDao;
	
	public LocationService() {
		this.locationDao = new LocationDAO();
		this.employeeDao = new EmployeeDAO();
		this.carDao = new CarDAO();
	}
	
	public Location getLocationById (Long id) {
		Location location = locationDao.getEntityById (id);
		location.setEmployees(employeeDao.getAllByLocationId(id));
		location.setCars(carDao.getAllByLocationId(id));
		return location;		
	}
}
