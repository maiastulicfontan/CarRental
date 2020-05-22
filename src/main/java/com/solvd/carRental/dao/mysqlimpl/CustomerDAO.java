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
import com.solvd.carRental.models.Customer;

public class CustomerDAO implements IEntityDAO<Customer>{
	private final static Logger LOGGER = LogManager.getLogger(CustomerDAO.class);
	private final static String GET_BY_ID = "select * from Customers c inner join People p on c.id = p.id inner join Business_Entities be on p.id = be.id where c.id = ?";
	private final static String GET_ALL = "select * from Customers c inner join People p on c.id = p.id inner join Business_Entities be on p.id = be.id";
	private final static String INSERT = "insert into Customers (email_address) values(?)";
	private final static String UPDATE = "update Customers set email_address = ? where id = ?";
	private final static String DELETE = "delete from Customers where id = ?"; 
	
	
	@Override
	public Customer getEntityById(Long id) {	
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
			Customer customer = new Customer (
					rs.getLong("id"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getDate("birth_date").toLocalDate(),
					rs.getString("national_gvt_id"),
					rs.getString("email_address")
					);
			return customer;
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
	public List<Customer> getAll() {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement(GET_ALL);
			rs = ps.executeQuery();
			List <Customer> customers = new ArrayList<Customer>();
			while (rs.next()) {
				Customer customer = new Customer (
						rs.getLong("id"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getDate("birth_date").toLocalDate(),
						rs.getString("national_gvt_id"),
						rs.getString("email_address")
						);
				customers.add(customer);
			}
			return customers;
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
	public void updateEntity(Customer customer) {	
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement(UPDATE);
			ps.setString(1, customer.getEmailAddress());
			ps.setLong(2, customer.getId());
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
	public void saveEntity(Customer customer) {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet generatedKeys = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, customer.getEmailAddress());
			ps.executeUpdate();
			generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				customer.setId(generatedKeys.getLong(1));
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

}
