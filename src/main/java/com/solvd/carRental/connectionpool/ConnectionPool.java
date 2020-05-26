package com.solvd.carRental.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
//import java.util.concurrent.locks.ReentrantLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ConnectionPool {
	private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
	private BlockingQueue <Connection> connectionPoolQueue;
	private static ConnectionPool cp;
	private AtomicInteger currentConnections = new AtomicInteger();
	private static final int MAX_SIZE = 5;
	//private static ReentrantLock lock = new ReentrantLock();
	
	private ConnectionPool() {
		connectionPoolQueue = new LinkedBlockingQueue<>(MAX_SIZE);
	}
	
	public void init() {
		try {
			connectionPoolQueue.put(DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/car_rental?serverTimezone=UTC", "root", "root"));
			LOGGER.info("Connection" + connectionPoolQueue.peek()+ "  has been initialized");
			currentConnections.getAndIncrement();
		} catch (InterruptedException | SQLException e) {
			LOGGER.error(e);
		}
	}
	
	// with synchronized
	public Connection getConnection() throws InterruptedException {
		if (connectionPoolQueue.peek() == null && currentConnections.get() < MAX_SIZE) {
				synchronized (ConnectionPool.class) {
					if (connectionPoolQueue.peek() == null && currentConnections.get() < MAX_SIZE) {
						cp.init();
					}
				}		
		}
		LOGGER.info("Connection "+connectionPoolQueue.peek() + " has been taken");
		return connectionPoolQueue.take();
	}
	
	  //not sure if this is the right way to use locks in this case but wanted to give it a try
	  /*public Connection getConnection() throws InterruptedException {
		lock.lock();
		try{
			if (connectionPoolQueue.peek() == null && currentConnections.get() < MAX_SIZE) {
				cp.init();
				return connectionPoolQueue.poll(3, TimeUnit.SECONDS);
			}
		} catch (InterruptedException e) {
			LOGGER.error(e);
		} finally {
			lock.unlock();
		}
		return connectionPoolQueue.poll(3, TimeUnit.SECONDS); 
	  }*/
	
	public void releaseConnection(Connection con) throws InterruptedException{
		if (con != null) {
			connectionPoolQueue.put(con); 
			LOGGER.info(con+" has been released");
		}
	}
	
	// with synchronized
	public static ConnectionPool getInstance() {
	if (cp == null){
		synchronized(ConnectionPool.class) { 
			if (cp == null) {
				cp = new ConnectionPool();
			}
		}
	}
	return cp;
	}
	
	
	/*public static ConnectionPool getInstance() {
		lock.lock();
		try {
			if (cp == null) {
				cp = new ConnectionPool();
				LOGGER.info("New instance of cp");
			}
		} finally {
			lock.unlock();
		}
		return cp;
	}*/
	
	
	@Override
	public String toString() {
		return connectionPoolQueue.toString();
	}
}
