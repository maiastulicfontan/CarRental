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
import com.solvd.carRental.dao.ICarDAO;
import com.solvd.carRental.models.Car;

public class CarDAO implements ICarDAO{
	private final static Logger LOGGER = LogManager.getLogger(CarDAO.class);

	@Override
	public Car getEntityById(Long id) {	
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement("select * from Cars where id = ?");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			rs.next();
			Car car = new Car (
					rs.getLong("id"),
					rs.getString("license_plate"),
					rs.getInt("model_year"),
					rs.getString("color")
					);
			return car;
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
	public List<Car> getAll() {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement("select * from Cars");
			rs = ps.executeQuery();
			List <Car> cars = new ArrayList<Car>();
			while (rs.next()) {
				Car car = new Car (
						rs.getLong("id"),
						rs.getString("license_plate"),
						rs.getInt("model_year"),
						rs.getString("color")
						);
				cars.add(car);
			}
			return cars;
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
	public void updateEntityById(Car car) {	
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement("update Cars set license_plate = ?, model_year = ?, color = ?  where id = ?");
			ps.setString(1, car.getLicensePlate());
			ps.setInt(2, car.getModelYear());
			ps.setString(3, car.getColor());
			ps.setLong(4, car.getId());
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
	public void saveEntity(Car car) {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement("insert into Cars (id, license_plate, model_year, color) values(?, ?, ?, ?)");
			ps.setLong(1, car.getId());
			ps.setString(2, car.getLicensePlate());
			ps.setInt(3, car.getModelYear());
			ps.setString(4, car.getColor());
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
			ps = c.prepareStatement("delete from Cars where id = ?");
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
	
	public List<Car> getAllByLocationId(Long locationId) {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement("select * from Cars where location_id = ?");
			ps.setLong(1, locationId);
			rs = ps.executeQuery();
			List <Car> cars = new ArrayList<Car>();
			while (rs.next()) {
				Car car = new Car (
						rs.getLong("id"),
						rs.getString("license_plate"),
						rs.getInt("model_year"),
						rs.getString("color")
						);
				cars.add(car);
			}
			return cars;
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
