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
import com.solvd.carRental.dao.ICreditCardDAO;
import com.solvd.carRental.models.CreditCard;

public class CreditCardDAO implements ICreditCardDAO {
	private final static Logger LOGGER = LogManager.getLogger(CreditCardDAO.class);
	
	@Override
	public CreditCard getEntityById(Long id) {	
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement("select * from Credit_Cards where id = ?");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			rs.next();
			CreditCard creditCard = new CreditCard (
					rs.getLong("id"),
					rs.getInt("number"),
					rs.getString("valid_from"),
					rs.getString("expiration")
					);
			return creditCard;
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
	public List<CreditCard> getAll() {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement("select * from Credit_Cards");
			rs = ps.executeQuery();
			List <CreditCard> creditCards = new ArrayList<CreditCard>();
			while (rs.next()) {
				CreditCard creditCard = new CreditCard (
						rs.getLong("id"),
						rs.getInt("number"),
						rs.getString("valid_from"),
						rs.getString("expiration")
						);
				creditCards.add(creditCard);
			}
			return creditCards;
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
	public void updateEntityById(CreditCard creditCard) {	
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement("update Credit_Cards set number = ?, valid_from = ?, expiration = ?  where id = ?");
			ps.setInt(1, creditCard.getNumber());
			ps.setString(2, creditCard.getValidFrom());
			ps.setString(3, creditCard.getExpiration());
			ps.setLong(4, creditCard.getId());
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
	public void saveEntity(CreditCard creditCard) {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement("insert into Credit_Cards (id, number, valid_from, expiration) values(?, ?, ?, ?)");
			ps.setLong(1, creditCard.getId());
			ps.setInt(2, creditCard.getNumber());
			ps.setString(3, creditCard.getValidFrom());
			ps.setString(4, creditCard.getExpiration());
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
			ps = c.prepareStatement("delete from Credit_Cards where id = ?");
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
	
	public List<CreditCard> getAllByCustomerId(Long customerId) {	
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = cp.getConnection();
			ps = c.prepareStatement("select * from Credit_Cards where customer_id = ?");
			ps.setLong(1, customerId);
			rs = ps.executeQuery();
			rs.next();
			List <CreditCard> creditCards = new ArrayList<CreditCard>();
			while (rs.next()) {
				CreditCard creditCard = new CreditCard (
						rs.getLong("id"),
						rs.getInt("number"),
						rs.getString("valid_from"),
						rs.getString("expiration")
						);
				creditCards.add(creditCard);
			}
			return creditCards;
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
