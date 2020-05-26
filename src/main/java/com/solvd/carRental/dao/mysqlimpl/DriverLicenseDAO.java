package com.solvd.carRental.dao.mysqlimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.carRental.connectionpool.ConnectionPool;
import com.solvd.carRental.dao.IDriverLicenseDAO;
import com.solvd.carRental.models.DriverLicense;

public class DriverLicenseDAO implements IDriverLicenseDAO{
	private final static Logger LOGGER = LogManager.getLogger(DriverLicenseDAO.class);
	private final static String GET_BY_ID = "select * from Drivers_Licenses where id = ?";
	private final static String GET_ALL = "select * from Drivers_Licenses";
	private final static String INSERT = "insert into Drivers_Licenses (number, valid_from, expiration) values(?, ?, ?)";
	private final static String UPDATE = "update Drivers_Licenses set number = ?, valid_from = ?, expiration = ?  where id = ?";
	private final static String DELETE = "delete from Drivers_Licenses where id = ?";
	private final static String GET_BY_CUSTOMER_ID = "select * from Drivers_Licenses where customer_id = ?";
	
	@Override
	public DriverLicense getEntityById(Long id) {	
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
	public List<DriverLicense> getAll() {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = cp.getConnection();
			ps = c.prepareStatement(GET_ALL);
			rs = ps.executeQuery();
			List <DriverLicense> driverLicenses = new ArrayList<DriverLicense>();
			while (rs.next()) {
				driverLicenses.add(this.buildEntity(rs));
			}
			return driverLicenses;
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
	public void updateEntity(DriverLicense driverLicense) {	
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = cp.getConnection();
			ps = c.prepareStatement(UPDATE);
			ps.setLong(1, driverLicense.getNumber());
			ps.setDate(2, Date.valueOf(driverLicense.getValidFrom()));
			ps.setDate(3, Date.valueOf(driverLicense.getExpiration()));
			ps.setLong(4, driverLicense.getId());
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
	public void saveEntity(DriverLicense driverLicense) {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet generatedKeys = null;
		try {
			c = cp.getConnection();
			ps = c.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, driverLicense.getNumber());
			ps.setDate(2, Date.valueOf(driverLicense.getValidFrom()));
			ps.setDate(3, Date.valueOf(driverLicense.getExpiration()));
			ps.executeUpdate();
			generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				driverLicense.setId(generatedKeys.getLong(1));
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
	public DriverLicense getByCustomerId(Long id) {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = cp.getConnection();
			ps = c.prepareStatement(GET_BY_CUSTOMER_ID);
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
	public DriverLicense buildEntity(ResultSet rs) throws SQLException {
		DriverLicense driverLicense = new DriverLicense (
				rs.getLong("id"),
				rs.getLong("number"),
				rs.getDate("valid_from").toLocalDate(),
				rs.getDate("expiration").toLocalDate()
				);
		return driverLicense;
	}


}
