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
import com.solvd.carRental.models.EmployeePosition;

public class EmployeePositionDAO implements IEntityDAO<EmployeePosition>{
	private final static Logger LOGGER = LogManager.getLogger(EmployeePositionDAO.class);
	private final static String GET_BY_ID = "select * from Employee_Positions where id = ?";
	private final static String GET_ALL = "select * from Employee_Positions";
	private final static String INSERT = "insert into Employee_Positions (name, description, salary) values(?, ?, ?)";
	private final static String UPDATE = "update Employee_Positions set name = ?, description = ?, salary = ?  where id = ?";
	private final static String DELETE = "delete from Employee_Positions where id = ?";

	@Override
	public EmployeePosition getEntityById(Long id) {	
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
			EmployeePosition employeePosition = new EmployeePosition (
					rs.getLong("id"),
					rs.getString("name"),
					rs.getString("description"),
					rs.getDouble("salary")
					);
			return employeePosition;
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
	public List<EmployeePosition> getAll() {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement(GET_ALL);
			rs = ps.executeQuery();
			List <EmployeePosition> employeePositions = new ArrayList<EmployeePosition>();
			while (rs.next()) {
				EmployeePosition employeePosition = new EmployeePosition (
						rs.getLong("id"),
						rs.getString("name"),
						rs.getString("description"),
						rs.getDouble("salary")
						);
				employeePositions.add(employeePosition);
			}
			return employeePositions;
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
	public void updateEntity(EmployeePosition employeePosition) {	
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement(UPDATE);
			ps.setString (1, employeePosition.getName());
			ps.setString (2, employeePosition.getDescription());
			ps.setDouble (3, employeePosition.getSalary());
			ps.setLong(4, employeePosition.getId());
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
	public void saveEntity(EmployeePosition employeePosition) {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet generatedKeys = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, employeePosition.getName());
			ps.setString(2, employeePosition.getDescription());
			ps.setDouble(3, employeePosition.getSalary());
			ps.executeUpdate();
			generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				employeePosition.setId(generatedKeys.getLong(1));
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
