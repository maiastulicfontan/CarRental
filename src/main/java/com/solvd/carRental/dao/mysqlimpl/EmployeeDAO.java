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
import com.solvd.carRental.dao.IEmployeeDAO;
import com.solvd.carRental.models.Employee;

public class EmployeeDAO implements IEmployeeDAO{
	private final static Logger LOGGER = LogManager.getLogger(EmployeeDAO.class);
	private final static String GET_BY_ID = "select * from Employees e inner join People p on e.id = p.id inner join Business_Entities be on p.id = be.id where e.id = ?";
	private final static String GET_ALL = "select * from Employees e inner join People p on e.id = p.id inner join Business_Entities be on p.id = be.id";
	private final static String INSERT_EMP = "insert into Employees (id, hire_date) values (?, ?)";
	private final static String INSERT_PERSON = "insert into People ";
	private final static String UPDATE = "update Employees set hire_date = ?  where id = ?";
	private final static String DELETE = "delete from Employees where id = ?";
	private final static String GET_BY_LOC_ID = "select * from Employees where location_id = ?";

	@Override
	public Employee getEntityById(Long id) {	
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
	public List<Employee> getAll() {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = cp.getConnection();
			ps = c.prepareStatement(GET_ALL);
			rs = ps.executeQuery();
			List <Employee> employees = new ArrayList<Employee>();
			while (rs.next()) {
				employees.add(this.buildEntity(rs));
			}
			return employees;
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
	public void updateEntity(Employee employee) {	
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = cp.getConnection();
			ps = c.prepareStatement(UPDATE);
			ps.setDate(1, Date.valueOf(employee.getHireDate()));
			ps.setLong(2, employee.getId());
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
	public void saveEntity(Employee employee) { //applying transactions
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		PreparedStatement psPerson = null;
		ResultSet generatedKeys = null;
		try {
			c = cp.getConnection();
			c.setAutoCommit(false);
			psPerson = c.prepareStatement (INSERT_PERSON, Statement.RETURN_GENERATED_KEYS);
			ps = c.prepareStatement(INSERT_EMP);
			psPerson.executeUpdate();
			generatedKeys = psPerson.getGeneratedKeys();
			ps.setDate(1, Date.valueOf(employee.getHireDate()));
			if (generatedKeys.next()) {
				employee.setId(generatedKeys.getLong(1));
			} else {
				throw new SQLException("Could not get id, fail in creating record");
			}
			c.commit();
		} catch (InterruptedException e) {
			LOGGER.error(e);
		} catch (SQLException e) {
			LOGGER.error(e);
			//c.rollback();
		} finally {
			try {
				generatedKeys.close();
			} catch (SQLException e) {
				LOGGER.error(e);
			} finally {
				try { 
					ps.close();
					psPerson.close();
				} catch (SQLException e) {
					LOGGER.error(e);
				} finally {
					try {
						cp.releaseConnection(c);
						c.setAutoCommit(true);
					} catch (InterruptedException e) {
						LOGGER.error(e);
					} catch (SQLException e) {
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
	
	public List<Employee> getAllByLocationId(Long locationId) {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = cp.getConnection();
			ps = c.prepareStatement(GET_BY_LOC_ID);
			ps.setLong(1, locationId);
			rs = ps.executeQuery();
			List <Employee> employees = new ArrayList<Employee>();
			while (rs.next()) {
				employees.add(this.buildEntity(rs));
			}
			return employees;
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
	public Employee buildEntity(ResultSet rs) throws SQLException {
		Employee employee = new Employee (
				rs.getLong("id"),
				rs.getString("first_name"),
				rs.getString("last_name"),
				rs.getDate("birth_date").toLocalDate(),
				rs.getString("national_gvt_id"),
				rs.getDate("hire_date").toLocalDate()
				);
		return employee;
	}

}
