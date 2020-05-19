package com.solvd.carRental.services;

import java.util.ArrayList;
import java.util.List;

import com.solvd.carRental.dao.mysqlimpl.AddressDAO;
import com.solvd.carRental.dao.mysqlimpl.BusinessEntityDAO;
import com.solvd.carRental.dao.mysqlimpl.PhoneNumberDAO;
import com.solvd.carRental.models.BusinessEntity;

public class BusinessEntityService {
	private BusinessEntityDAO businessEntityDao;
	private AddressDAO addressDao;
	private PhoneNumberDAO phoneNumberDao;
	
	public BusinessEntityService () {
		this.businessEntityDao = new BusinessEntityDAO();
		this.addressDao = new AddressDAO();
		this.phoneNumberDao = new PhoneNumberDAO();
	}
	
	public BusinessEntity getBusinessEntityById (Long id) {
		BusinessEntity businessEntity = businessEntityDao.getEntityById(id);
		this.update(businessEntity);
		return businessEntity;
	}
	
	public List<BusinessEntity> getAllBusinessEntities(){
		List<BusinessEntity> bussinessEntities = new ArrayList<BusinessEntity>();
		bussinessEntities = businessEntityDao.getAll();
		bussinessEntities.forEach (tmpBE -> this.update(tmpBE));
		return bussinessEntities;
	}

	
	public void update (BusinessEntity businessEntity) {
		businessEntity.setAddresses(addressDao.getAllByBusinessEntityId(businessEntity.getId()));
		businessEntity.setPhoneNumbers(phoneNumberDao.getAllByBusinessEntityId(businessEntity.getId()));	
	}
}
