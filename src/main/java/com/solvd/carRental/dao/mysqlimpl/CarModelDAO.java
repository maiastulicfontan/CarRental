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
import com.solvd.carRental.models.CarModel;

public class CarModelDAO implements IEntityDAO<CarModel> {
	private final static Logger LOGGER = LogManager.getLogger(CarModelDAO.class);
	private final static String GET_BY_ID = "select * from Car_Models where id = ?";
	private final static String GET_ALL = "select * from Car_Models";
	private final static String INSERT = "insert into Car_Models (name, description, transmission, number_of_seats, airbag_info, luggage_space, fuel_consumption, cost_per_day) values(?, ?, ?, ?, ?, ?, ?, ?)";
	private final static String UPDATE = "update Car_Models set name = ?, description = ?, transmission = ?, number_of_seats = ?, airbag_info = ?, luggage_space = ?, fuel_consumption = ?, cost_per_day = ?  where id = ?";
	private final static String DELETE = "delete from Car_Models where id = ?";
	
	@Override
	public CarModel getEntityById(Long id) {	
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
	public List<CarModel> getAll() {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = cp.getConnection();
			ps = c.prepareStatement(GET_ALL);
			rs = ps.executeQuery();
			List <CarModel> carModels = new ArrayList<CarModel>();
			while (rs.next()) {
				carModels.add(this.buildEntity(rs));
			}
			return carModels;
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
	public void updateEntity(CarModel carModel) {	
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		try {
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
	public void saveEntity(CarModel carModel) {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet generatedKeys = null;
		try {
			c = cp.getConnection();
			ps = c.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, carModel.getName());
			ps.setString(2, carModel.getDescription());
			ps.setString(3, carModel.getTransmission());
			ps.setInt (4, carModel.getNumberOfSeats());
			ps.setString(5, carModel.getAirbagInfo());
			ps.setString(6, carModel.getLuggageSpace());
			ps.setString(7, carModel.getFuelConsumption());
			ps.setDouble(8, carModel.getCostPerDay());
			ps.executeUpdate();
			generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				carModel.setId(generatedKeys.getLong(1));
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
	public CarModel buildEntity(ResultSet rs) throws SQLException {
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
	}
	
}
