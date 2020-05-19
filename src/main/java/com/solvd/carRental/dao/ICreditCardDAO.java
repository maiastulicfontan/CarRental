package com.solvd.carRental.dao;

import java.util.List;

import com.solvd.carRental.models.CreditCard;

public interface ICreditCardDAO extends IEntityDAO<CreditCard>{
	public List<CreditCard> getAllByCustomerId (Long id);
}
