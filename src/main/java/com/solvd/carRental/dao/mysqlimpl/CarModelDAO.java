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
import com.solvd.carRental.dao.IEntityDAO;
import com.solvd.carRental.models.CarModel;

public class CarModelDAO implements IEntityDAO<CarModel> {
	private final static Logger LOGGER = LogManager.getLogger(CarModelDAO.class);
	private final static String GET_BY_ID = "select * from Car_Models where id = ?";
	private final static String GET_ALL = "select * from Car_Models";
	private final static String INSERT = "insert into Car_Models (id, name, description, transmission, number_of_seats, airbag_info, luggage_space, fuel_consumption, cost_per_day) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final static String UPDATE = "update Car_Models set name = ?, description = ?, transmission = ?, number_of_seats = ?, airbag_info = ?, luggage_space = ?, fuel_consumption = ?, cost_per_day = ?  where id = ?";
	private final static String DELETE = "delete from Car_Models where id = ?";
	
	@Override
	public CarModel getEntityById(Long id) {	
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
			CarModel carModel = new CarModel (
					rs.getLong("id"),
					rs.getString("name"),
					rs.getString("description"),
					rs.getString("transmission"),
					rs.getInt("number_of_seats"),
					rs.getString("airbag_info"),
					rs.getString("luggage_space"),
					rs.getString("fuel_consumption"),
					rs.getDouble("cost_per_day")
					);
			return carModel;
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
	public List<CarModel> getAll() {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement(GET_ALL);
			rs = ps.executeQuery();
			List <CarModel> carModels = new ArrayList<CarModel>();
			while (rs.next()) {
				CarModel carModel = new CarModel (
						rs.getLong("id"),
						rs.getString("name"),
						rs.getString("description"),
						rs.getString("transmission"),
						rs.getInt("number_of_seats"),
						rs.getString("airbag_info"),
						rs.getString("luggage_space"),
						rs.getString("fuel_consumption"),
						rs.getDouble("cost_per_day")
						);
				carModels.add(carModel);
			}
			return carModels;
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
	public void updateEntity(CarModel carModel) {	
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement(UPDATE);
			ps.setString(1, carModel.getName());
			ps.setString(2, carModel.getDescription());
			ps.setString(3, carModel.getTransmission());
			ps.setInt (4, carModel.getNumberOfSeats());
			ps.setString(5, carModel.getAirbagInfo());
			ps.setString(6, carModel.getLuggageSpace());
			ps.setString(7, carModel.getFuelConsumption());
			ps.setDouble(8, carModel.getCostPerDay());
			ps.setLong(9, carModel.getId());
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
	public void saveEntity(CarModel carModel) {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement(INSERT);
			ps.setLong(1, carModel.getId());
			ps.setString(2, carModel.getName());
			ps.setString(3, carModel.getDescription());
			ps.setString(4, carModel.getTransmission());
			ps.setInt (5, carModel.getNumberOfSeats());
			ps.setString(6, carModel.getAirbagInfo());
			ps.setString(7, carModel.getLuggageSpace());
			ps.setString(8, carModel.getFuelConsumption());
			ps.setDouble(9, carModel.getCostPerDay());
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