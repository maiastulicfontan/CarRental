package com.solvd.carRental.dao;

import java.util.List;

import com.solvd.carRental.models.Employee;

public interface IEmployeeDAO extends IEntityDAO<Employee> {
	public List<Employee> getAllByLocationId(Long id);

}
