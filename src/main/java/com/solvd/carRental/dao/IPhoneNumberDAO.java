package com.solvd.carRental.dao;

import java.util.List;

import com.solvd.carRental.models.PhoneNumber;

public interface IPhoneNumberDAO extends IEntityDAO<PhoneNumber>{
	public List<PhoneNumber> getAllByBusinessEntityId (Long id);

}
