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
import com.solvd.carRental.dao.IBusinessEntityDAO;
import com.solvd.carRental.models.BusinessEntity;

public class BusinessEntityDAO implements IBusinessEntityDAO {
		private final static Logger LOGGER = LogManager.getLogger(BusinessEntityDAO.class);

		@Override
		public BusinessEntity getEntityById(Long id) {	
			ConnectionPool cp = ConnectionPool.getInstance();
			Connection c = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				c = cp.getConnection();
				ps = c.prepareStatement("select * from Business_Entities where id = ?");
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
				Class.forName("com.mysql.jdbc.Driver");
				c = cp.getConnection();
				ps = c.prepareStatement("select * from Business_Entities where id = ?");
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
		public void updateEntityById(BusinessEntity be) {	
			ConnectionPool cp = ConnectionPool.getInstance();
			Connection c = null;
			PreparedStatement ps = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				c = cp.getConnection();
				ps = c.prepareStatement("update Business_Entity set id = ?  where id = ?");
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
				Class.forName("com.mysql.jdbc.Driver");
				c = cp.getConnection();
				ps = c.prepareStatement("insert into Business_Entities (id) values(?)");
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
				Class.forName("com.mysql.jdbc.Driver");
				c = cp.getConnection();
				ps = c.prepareStatement("delete from Business_Entities where id = ?");
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

