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
import com.solvd.carRental.dao.IAddressDAO;
import com.solvd.carRental.models.Address;

public class AddressDAO implements IAddressDAO {
	private final static Logger LOGGER = LogManager.getLogger(AddressDAO.class);
	private final static String GET_BY_ID = "select * from Addresses where id = ?";
	private final static String GET_ALL = "select * from Addresses";
	private final static String INSERT = "insert into Addresses (address_line1, address_line2, city, zip_code) values(?, ?, ?, ?)";
	private final static String UPDATE = "update Addresses set address_line1 = ?, address_line2 = ?, city = ?, zip_code = ? where id = ?";
	private final static String DELETE = "delete from Addresses where id = ?";
	private final static String GET_ALL_BY_BE_ID = "select * from Addresses where business_entity_id = ?";

	@Override
	public Address getEntityById(Long id) {	
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
	public List<Address> getAll() {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = cp.getConnection();
			ps = c.prepareStatement(GET_ALL);
			rs = ps.executeQuery();
			List <Address> addresses = new ArrayList<Address>();
			while (rs.next()) {
				addresses.add(this.buildEntity(rs));
			}
			return addresses;
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
	public void updateEntity(Address address) {	
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = cp.getConnection();
			ps = c.prepareStatement(UPDATE);
			ps.setString(1, address.getAddressFirstLine());
			ps.setString(2, address.getAddressSecondLine());
			ps.setString(3, address.getCity());
			ps.setString(4, address.getZipCode());
			ps.setLong(5,address.getId());
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
	public void saveEntity(Address address) {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet generatedKeys = null;
		try {
			c = cp.getConnection();
			ps = c.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,address.getAddressFirstLine());
			ps.setString(2, address.getAddressSecondLine());
			ps.setString(3, address.getCity());
			//ps.SetLong(5, address.getRegion().getId());
			ps.setString(4, address.getZipCode());
			ps.executeUpdate();
			generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				address.setId(generatedKeys.getLong(1));
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
	public List<Address> getAllByBusinessEntityId(Long id) {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = cp.getConnection();
			ps = c.prepareStatement(GET_ALL_BY_BE_ID);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			List <Address> addresses = new ArrayList<Address>();
			while (rs.next()) {
				addresses.add(this.buildEntity(rs));
			}
			return addresses;
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
	public Address buildEntity(ResultSet resultSet) throws SQLException  {
		Address address = new Address (
				resultSet.getLong("id"),
				resultSet.getString("address_line1"),
				resultSet.getString("address_line2"),
				resultSet.getString("city"),
				resultSet.getString("zip_code")
				);
		return address;
	}
}
