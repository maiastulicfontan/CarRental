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
import com.solvd.carRental.dao.IPhoneNumberDAO;
import com.solvd.carRental.models.PhoneNumber;

public class PhoneNumberDAO implements IPhoneNumberDAO {
	private final static Logger LOGGER = LogManager.getLogger(PhoneNumberDAO.class);
	private final static String GET_BY_ID = "select * from Phone_Numbers where id = ?";
	private final static String GET_ALL = "select * from Phone_Numbers";
	private final static String INSERT = "insert into Phone_Numbers (phone_number) values(?)";
	private final static String UPDATE = "update Phone_Numbers set phone_number = ?  where id = ?";
	private final static String DELETE = "delete from Phone_Numbers where id = ?";
	private final static String GET_ALL_BY_BE_ID = "select * from Phone_Numbers where business_entity_id = ?";
	
	@Override
	public PhoneNumber getEntityById(Long id) {	
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
			PhoneNumber phoneNumber = new PhoneNumber (
					rs.getLong("id"),
					rs.getString("number")
					);
			return phoneNumber;
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
	public List<PhoneNumber> getAll() {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement(GET_ALL);
			rs = ps.executeQuery();
			List <PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
			while (rs.next()) {
				PhoneNumber phoneNumber = new PhoneNumber (
						rs.getLong("id"),
						rs.getString("number")
						);
				phoneNumbers.add(phoneNumber);
			}
			return phoneNumbers;
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
	public void updateEntity(PhoneNumber phoneNumbers) {	
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement(UPDATE);
			ps.setString(1, phoneNumbers.getNumber());
			ps.setLong(2, phoneNumbers.getId());
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
	public void saveEntity(PhoneNumber phoneNumber) {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet generatedKeys = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, phoneNumber.getNumber());
			ps.executeUpdate();
			generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				phoneNumber.setId(generatedKeys.getLong(1));
			} else {
				throw new SQLException("Could not get id, fail in creating record");
			}
		} catch (ClassNotFoundException e) {
			LOGGER.error(e);
		} catch (InterruptedException e) {
			LOGGER.error(e);
		} catch (SQLException e) {
			LOGGER.error(e);
		} finally {
			try {
				ps.close();
				generatedKeys.close();
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
	
	@Override
	public List<PhoneNumber> getAllByBusinessEntityId(Long id) {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement(GET_ALL_BY_BE_ID);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			List <PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
			while (rs.next()) {
				PhoneNumber phoneNumber = new PhoneNumber (
						rs.getLong("id"),
						rs.getString("phone_number")
						);
				phoneNumbers.add(phoneNumber);
			}
			return phoneNumbers;
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

}
