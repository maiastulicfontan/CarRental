package com.solvd.carRental.dao;

import java.util.List;



public interface IEntityDAO <T> {
	public T getEntityById (Long id);

	public List<T> getAll();
	
	public void deleteEntityById (Long id);
	
	public void updateEntityById (T t);

	public void saveEntity(T t);
	
}
