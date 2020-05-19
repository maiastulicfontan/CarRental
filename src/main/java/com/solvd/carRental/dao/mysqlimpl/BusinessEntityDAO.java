package com.solvd.carRental.dao.mysqlimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.carRental.connectionpool.ConnectionPool;
import com.solvd.carRental.dao.IEntityDAO;
import com.solvd.carRental.models.BusinessEntity;

public class BusinessEntityDAO implements IEntityDAO<BusinessEntity> {
		private final static Logger LOGGER = LogManager.getLogger(BusinessEntityDAO.class);
		private final static String GET_BY_ID = "select * from Business_Entities where id = ?";
		private final static String GET_ALL = "select * from Business_Entities";
		private final static String INSERT = "insert into Business_Entities (id) values(?)" ;
		private final static String UPDATE = "update Business_Entity set id = ?  where id = ?";
		private final static String DELETE = "delete from Business_Entities where id = ?";

		@Override
		public BusinessEntity getEntityById(Long id) {	
			ConnectionPool cp = ConnectionPool.getInstance();
			Connection c = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				c = cp.getConnection();
				ps = c.prepareStatement(GET_BY_ID);
				ps.setLong(1, id);
				rs = ps.executeQuery();
				rs.next();
				BusinessEntity be = new BusinessEntity (
						rs.getLong("id")
						);
				return be;
			} catch (ClassNotFoundException e) {
				LOGGER.error(e);
			} catch (InterruptedException e) {
				LOGGER.error(e);
			} catch (SQLException e) {
				LOGGER.error(e);
			} finally {
				try {
					ps.close();
					rs.close();
					cp.releaseConnection(c);
				} catch (InterruptedException e) {
					LOGGER.error(e);
				} catch (SQLException e) {
					LOGGER.error(e);
				}
			}
			return null;
		}
		
		@Override
		public List<BusinessEntity> getAll() {
			ConnectionPool cp = ConnectionPool.getInstance();
			Connection c = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				c = cp.getConnection();
				ps = c.prepareStatement(GET_ALL);
				rs = ps.executeQuery();
				List <BusinessEntity> businessEntities = new ArrayList<BusinessEntity>();
				while (rs.next()) {
					BusinessEntity be = new BusinessEntity (
							rs.getLong("id")
							);
					businessEntities.add(be);
				}
				return businessEntities;
			} catch (ClassNotFoundException e) {
				LOGGER.error(e);
			} catch (InterruptedException e) {
				LOGGER.error(e);
			} catch (SQLException e) {
				LOGGER.error(e);
			} finally {
				try {
					ps.close();
					rs.close();
					cp.releaseConnection(c);
				} catch (InterruptedException e) {
					LOGGER.error(e);
				} catch (SQLException e) {
					LOGGER.error(e);
				}
			}
			return null;
		}
		
		@Override
		public void updateEntity(BusinessEntity be) {	
			ConnectionPool cp = ConnectionPool.getInstance();
			Connection c = null;
			PreparedStatement ps = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				c = cp.getConnection();
				ps = c.prepareStatement(UPDATE);
				ps.setLong(1, be.getId());
				ps.executeUpdate();
			} catch (ClassNotFoundException e) {
				LOGGER.error(e);
			} catch (InterruptedException e) {
				LOGGER.error(e);
			} catch (SQLException e) {
				LOGGER.error(e);
			} finally {
				try {
					ps.close();
					cp.releaseConnection(c);
				} catch (InterruptedException e) {
					LOGGER.error(e);
				} catch (SQLException e) {
					LOGGER.error(e);
				}
			}
		}
		
		@Override
		public void saveEntity(BusinessEntity be) {
			ConnectionPool cp = ConnectionPool.getInstance();
			Connection c = null;
			PreparedStatement ps = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				c = cp.getConnection();
				ps = c.prepareStatement(INSERT);
				ps.setLong(1, be.getId());
				ps.executeUpdate();
			} catch (ClassNotFoundException e) {
				LOGGER.error(e);
			} catch (InterruptedException e) {
				LOGGER.error(e);
			} catch (SQLException e) {
				LOGGER.error(e);
			} finally {
				try {
					ps.close();
					cp.releaseConnection(c);
				} catch (InterruptedException e) {
					LOGGER.error(e);
				} catch (SQLException e) {
					LOGGER.error(e);
				}
			}
		}

		@Override
		public void deleteEntityById(Long id) {
			ConnectionPool cp = ConnectionPool.getInstance();
			Connection c = null;
			PreparedStatement ps = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				c = cp.getConnection();
				ps = c.prepareStatement(DELETE);
				ps.setLong(1, id);
				ps.executeUpdate();
			} catch (ClassNotFoundException e) {
				LOGGER.error(e);
			} catch (InterruptedException e) {
				LOGGER.error(e);
			} catch (SQLException e) {
				LOGGER.error(e);
			} finally {
				try {
					ps.close();
					cp.releaseConnection(c);
				} catch (InterruptedException e) {
					LOGGER.error(e);
				} catch (SQLException e) {
					LOGGER.error(e);
				}
			}
		}
	}

