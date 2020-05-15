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
import com.solvd.carRental.dao.IAddressDAO;
import com.solvd.carRental.models.Address;

public class AddressDAO implements IAddressDAO {
	private final static Logger LOGGER = LogManager.getLogger(AddressDAO.class);

	@Override
	public Address getEntityById(Long id) {	
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement("select * from Addresses where id = ?");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			rs.next();
			Address address = new Address (
					rs.getLong("id"),
					rs.getString("address_line1"),
					rs.getString("address_line2"),
					rs.getString("city"),
					rs.getString("zip_code")
					);
			return address;
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
	public List<Address> getAll() {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement("select * from Addresses where id = ?");
			rs = ps.executeQuery();
			List <Address> addresses = new ArrayList<Address>();
			while (rs.next()) {
				Address address = new Address (
						rs.getLong("id"),
						rs.getString("address_line1"),
						rs.getString("address_line2"),
						rs.getString("city"),
						rs.getString("zip_code")
						);
				addresses.add(address);
			}
			return addresses;
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
	public void updateEntityById(Address address) {	
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement("update Addresses set address_line1 = ?, address_line2 = ?, city = ?, zip_code = ? where id = ?");
			ps.setString(1, address.getAddressFirstLine());
			ps.setString(2, address.getAddressSecondLine());
			ps.setString(3, address.getCity());
			ps.setString(4, address.getZipCode());
			ps.setLong(5,address.getId());
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
	public void saveEntity(Address address) {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement("insert into Addresses (id, address_line1, address_line2, city, zip_code) values(?, ?, ?, ?, ?)");
			ps.setLong(1, address.getId());
			ps.setString(2,address.getAddressFirstLine());
			ps.setString(3, address.getAddressSecondLine());
			ps.setString(4, address.getCity());
			//ps.SetLong(5, address.getRegion().getId());
			ps.setString(5, address.getZipCode());
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
			ps = c.prepareStatement("delete from Addresses where id = ?");
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
