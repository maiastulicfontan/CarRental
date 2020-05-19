package com.solvd.carRental.dao;

import java.util.List;

import com.solvd.carRental.models.Car;

public interface ICarDAO extends IEntityDAO<Car>{
	public List<Car> getAllByLocationId(Long id);
}
