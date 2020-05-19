package com.solvd.carRental.dao;

import java.util.List;

import com.solvd.carRental.models.Address;

public interface IAddressDAO extends IEntityDAO<Address> {
	
	public List<Address> getAllByBusinessEntityId(Long id);
}
