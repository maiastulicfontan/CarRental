package com.solvd.carRental.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IEntityDAO<T> {
	public T getEntityById (Long id);
	public List<T> getAll ();
	public void updateEntity (T t);
	public void saveEntity (T t);
	public void deleteEntityById (Long id);
	public T buildEntity (ResultSet rs) throws SQLException;

}
