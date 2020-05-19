package com.solvd.carRental.dao;

import java.util.List;

import com.solvd.carRental.models.Location;

public interface ILocationDAO extends IEntityDAO<Location> {
	public List<Location> getAllByCarRentalCompanyId(Long id);

}
