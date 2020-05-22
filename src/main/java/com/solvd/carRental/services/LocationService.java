package com.solvd.carRental.services;

import java.util.ArrayList;
import java.util.List;

import com.solvd.carRental.dao.mysqlimpl.AddressDAO;
import com.solvd.carRental.dao.mysqlimpl.CarDAO;
import com.solvd.carRental.dao.mysqlimpl.EmployeeDAO;
import com.solvd.carRental.dao.mysqlimpl.LocationDAO;
import com.solvd.carRental.dao.mysqlimpl.PhoneNumberDAO;
import com.solvd.carRental.models.Location;

public class LocationService {
	private LocationDAO locationDao;
	private EmployeeDAO employeeDao;
	private CarDAO carDao;
	private AddressDAO addressDao;
	private PhoneNumberDAO phoneNumberDao;
	
	public LocationService() {
		this.locationDao = new LocationDAO();
		this.employeeDao = new EmployeeDAO();
		this.carDao = new CarDAO();
		this.addressDao = new AddressDAO();
		this.phoneNumberDao = new PhoneNumberDAO();
	}
	
	public Location getLocationById (Long id) {
		Location location = locationDao.getEntityById (id);
		this.update(location);
		return location;		
	}
	
	public List<Location> getAllLocations() {
		List<Location> locations = new ArrayList<Location>();
		locations = locationDao.getAll();
		locations.forEach (tmpLoc -> this.update(tmpLoc));
		return locations;
	}
	
	
	public void update (Location location) {
		location.setEmployees(employeeDao.getAllByLocationId(location.getId()));
		location.setCars(carDao.getAllByLocationId(location.getId()));
		location.setAddresses(addressDao.getAllByBusinessEntityId(location.getId()));
		location.setPhoneNumbers(phoneNumberDao.getAllByBusinessEntityId(location.getId()));
	}
}
