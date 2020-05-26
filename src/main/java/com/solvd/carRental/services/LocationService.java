package com.solvd.carRental.services;

import java.util.ArrayList;
import java.util.List;


import com.solvd.carRental.dao.ICarDAO;
import com.solvd.carRental.dao.IEmployeeDAO;
import com.solvd.carRental.dao.ILocationDAO;
import com.solvd.carRental.dao.mysqlimpl.CarDAO;
import com.solvd.carRental.dao.mysqlimpl.EmployeeDAO;
import com.solvd.carRental.dao.mysqlimpl.LocationDAO;
import com.solvd.carRental.models.Location;

public class LocationService {
	private ILocationDAO locationDao;
	private IEmployeeDAO employeeDao;
	private ICarDAO carDao;

	
	public LocationService() {
		this.locationDao = new LocationDAO();
		this.employeeDao = new EmployeeDAO();
		this.carDao = new CarDAO();
	}
	
	public Location getLocationById (Long id) {
		Location location = locationDao.getEntityById (id);
		this.buildLocation(location);
		return location;		
	}
	
	public List<Location> getAllLocations() {
		List<Location> locations = new ArrayList<Location>();
		locations = locationDao.getAll();
		locations.forEach (tmpLoc -> this.buildLocation(tmpLoc));
		return locations;
	}
	
	
	public void buildLocation (Location location) {
		location.setEmployees(employeeDao.getAllByLocationId(location.getId()));
		location.setCars(carDao.getAllByLocationId(location.getId()));
	}
}
