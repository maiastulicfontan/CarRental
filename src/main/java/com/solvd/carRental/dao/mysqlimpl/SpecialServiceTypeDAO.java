
package com.solvd.carRental.dao.mysqlimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.carRental.connectionpool.ConnectionPool;
import com.solvd.carRental.dao.IEntityDAO;
import com.solvd.carRental.models.SpecialServiceType;

public class SpecialServiceTypeDAO implements IEntityDAO<SpecialServiceType> {
	private final static Logger LOGGER = LogManager.getLogger(SpecialServiceTypeDAO.class);
	private final static String GET_BY_ID = "select * from Special_Service_Types where id = ?";
	private final static String GET_ALL = "select * from Special_Service_Types";
	private final static String INSERT = "insert into Special_Service_Types (name, description, cost) values (?,?,?) ";
	private final static String UPDATE = "update Special_Service_Types set name = ?, description = ?, cost = ? where id = ?";
	private final static String DELETE = "delete from Special_Service_Types where id = ?";
	
	@Override
	public SpecialServiceType getEntityById(Long id) {	
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = cp.getConnection();
			ps = c.prepareStatement(GET_BY_ID);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			rs.next();
			return this.buildEntity(rs);
		} catch (InterruptedException e) {
			LOGGER.error(e);
		} catch (SQLException e) {
			LOGGER.error(e);
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				LOGGER.error(e);
			} finally {
				try { 
					ps.close();
				} catch (SQLException e) {
					LOGGER.error(e);
				} finally {
					try {
						cp.releaseConnection(c);
					} catch (InterruptedException e) {
						LOGGER.error(e);
					}
				}
			}
		}
		return null;
	}
	
	@Override
	public List<SpecialServiceType> getAll() {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = cp.getConnection();
			ps = c.prepareStatement(GET_ALL);
			rs = ps.executeQuery();
			List <SpecialServiceType> specialServiceTypes = new ArrayList<SpecialServiceType>();
			while (rs.next()) {
				specialServiceTypes.add(this.buildEntity(rs));
			}
			return specialServiceTypes;
		} catch (InterruptedException e) {
			LOGGER.error(e);
		} catch (SQLException e) {
			LOGGER.error(e);
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				LOGGER.error(e);
			} finally {
				try { 
					ps.close();
				} catch (SQLException e) {
					LOGGER.error(e);
				} finally {
					try {
						cp.releaseConnection(c);
					} catch (InterruptedException e) {
						LOGGER.error(e);
					}
				}
			}
		}
		return null;
	}
	
	@Override
	public void updateEntity(SpecialServiceType specialServiceType) {	
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = cp.getConnection();
			ps = c.prepareStatement(UPDATE);
			ps.setString(1, specialServiceType.getName());
			ps.setString(2, specialServiceType.getDescription());
			ps.setDouble(3, specialServiceType.getCost());
			ps.setLong(4, specialServiceType.getId()); 
			ps.executeUpdate();
		} catch (InterruptedException e) {
			LOGGER.error(e);
		} catch (SQLException e) {
			LOGGER.error(e);
		} finally {
			try { 
				ps.close();
			} catch (SQLException e) {
				LOGGER.error(e);
			} finally {
				try {
					cp.releaseConnection(c);
				} catch (InterruptedException e) {
					LOGGER.error(e);
				}
			}
		}
	}
	
	@Override
	public void saveEntity(SpecialServiceType specialServiceType) {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet generatedKeys = null;
		try {
			c = cp.getConnection();
			ps = c.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, specialServiceType.getName());
			ps.setString(2, specialServiceType.getDescription());
			ps.setDouble(3, specialServiceType.getCost());
			ps.executeUpdate();
			generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				specialServiceType.setId(generatedKeys.getLong(1));
			} else {
				throw new SQLException("Could not get id, fail in creating record");
			}
		} catch (InterruptedException e) {
			LOGGER.error(e);
		} catch (SQLException e) {
			LOGGER.error(e);
		} finally {
			try {
				generatedKeys.close();
			} catch (SQLException e) {
				LOGGER.error(e);
			} finally {
				try { 
					ps.close();
				} catch (SQLException e) {
					LOGGER.error(e);
				} finally {
					try {
						cp.releaseConnection(c);
					} catch (InterruptedException e) {
						LOGGER.error(e);
					}
				}
			}
		}
	}

	@Override
	public void deleteEntityById(Long id) { 
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = cp.getConnection();
			ps = c.prepareStatement(DELETE);
			ps.setLong(1, id);
			ps.executeUpdate();
		} catch (InterruptedException e) {
			LOGGER.error(e);
		} catch (SQLException e) {
			LOGGER.error(e);
		} finally {
			try { 
				ps.close();
			} catch (SQLException e) {
				LOGGER.error(e);
			} finally {
				try {
					cp.releaseConnection(c);
				} catch (InterruptedException e) {
					LOGGER.error(e);
				}
			}
		}
	}

	@Override
	public SpecialServiceType buildEntity(ResultSet rs) throws SQLException {
		SpecialServiceType specialServiceType = new SpecialServiceType (
				rs.getLong("id"),
				rs.getString("name"),
				rs.getString("description"),
				rs.getDouble("cost")
				);
		return specialServiceType;
	}


}
