package com.solvd.carRental.dao.mysqlimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.carRental.connectionpool.ConnectionPool;
import com.solvd.carRental.dao.IEntityDAO;
import com.solvd.carRental.models.Reservation;

public class ReservationDAO implements IEntityDAO<Reservation> {
	private final static Logger LOGGER = LogManager.getLogger(CarModelDAO.class);
	private final static String GET_BY_ID = "select * from Reservations where id = ?";
	private final static String GET_ALL = "select * from Reservations";
	private final static String INSERT = "insert into Reservations (pickupDateTime , returnDateTime, cost, confirmation_number, creation_date) values(?, ?, ?, ?, ?)";
	private final static String UPDATE = "update Reservations set pickupDateTime = ?, returnDateTime = ?, cost = ?, confirmation_number = ?, creation_date = ?  where id = ?";
	private final static String DELETE = "delete from Reservations where id = ?";
	
	@Override
	public Reservation getEntityById(Long id) {	
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
			Timestamp pickupTs = rs.getTimestamp ("pickup_date_time");
			LocalDateTime pickupDateTime = pickupTs.toLocalDateTime();
			Timestamp returnTs = rs.getTimestamp ("return_date_time");
			LocalDateTime returnDateTime = returnTs.toLocalDateTime();
			Reservation reservation = new Reservation (
					rs.getLong("id"),
					pickupDateTime,
					returnDateTime,
					rs.getDouble("cost"),
					rs.getLong("confirmation_number"),
					rs.getDate("creation_date").toLocalDate()
					);
			return reservation;
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
	public List<Reservation> getAll() {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement(GET_ALL);
			rs = ps.executeQuery();
			List <Reservation> reservations = new ArrayList<Reservation>();
			while (rs.next()) {
				Timestamp pickupTs = rs.getTimestamp ("pickup_date_time");
				LocalDateTime pickupDateTime = pickupTs.toLocalDateTime();
				Timestamp returnTs = rs.getTimestamp ("return_date_time");
				LocalDateTime returnDateTime = returnTs.toLocalDateTime();
				Reservation reservation = new Reservation (
						rs.getLong("id"),
						pickupDateTime,
						returnDateTime,
						rs.getDouble("cost"),
						rs.getLong("confirmation_number"),
						rs.getDate("creation_date").toLocalDate()
						);
				reservations.add(reservation);
			}
			return reservations;
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
	public void updateEntity(Reservation reservation) {	
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement(UPDATE);
			Timestamp pickupTs = Timestamp.valueOf(reservation.getPickupDateTime());
			Timestamp returnTs = Timestamp.valueOf(reservation.getReturnDateTime());
			ps.setTimestamp(1, pickupTs);
			ps.setTimestamp(2, returnTs);
			ps.setDouble(3, reservation.getCost());
			ps.setLong (4, reservation.getConfirmationNumber());
			ps.setLong(5, reservation.getId());
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
	public void saveEntity(Reservation reservation) {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet generatedKeys = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			Timestamp pickupTs = Timestamp.valueOf(reservation.getPickupDateTime());
			Timestamp returnTs = Timestamp.valueOf(reservation.getReturnDateTime());
			ps.setTimestamp(1, pickupTs);
			ps.setTimestamp(2, returnTs);
			ps.setDouble(3, reservation.getCost());
			ps.setLong (4, reservation.getConfirmationNumber());
			ps.executeUpdate();
			generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				reservation.setId(generatedKeys.getLong(1));
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
