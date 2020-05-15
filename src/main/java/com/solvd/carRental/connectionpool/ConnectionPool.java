package com.solvd.carRental.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ConnectionPool {
	private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
	private BlockingQueue <Connection> connectionPoolQueue;
	private static ConnectionPool cp;
	private AtomicInteger currentConnections = new AtomicInteger();
	private static final int MAX_SIZE = 5;
	private static ReentrantLock lock = new ReentrantLock();
	
	private ConnectionPool() {
		connectionPoolQueue = new LinkedBlockingQueue<>(MAX_SIZE);
	}
	
	public void init() throws InterruptedException{
		try {
			connectionPoolQueue.put(DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/car_rental", "root", "root"));
		} catch (InterruptedException | SQLException e) {
			LOGGER.error(e);
		}
	}
	
	/* with synchronized
	 * public String getConnection() throws InterruptedException {
		if (connectionPoolQueue.peek() == null && currentConnections.get() < MAX_SIZE) {
				synchronized (ConnectionPool.class) {
					if (connectionPoolQueue.peek() == null && currentConnections.get() < MAX_SIZE) {
						cp.init();
						currentConnections.getAndIncrement();
					}
				}		
		}
		return connectionPoolQueue.poll(3, TimeUnit.SECONDS); //a thread will wait 3 seconds for a string (connection) to become available 
	}*/
	
	  //not sure if this is the right way to use locks in this case but wanted to give it a try
	  public Connection getConnection() throws InterruptedException {
		lock.lock();
		try{
			if (connectionPoolQueue.peek() == null && currentConnections.get() < MAX_SIZE) {
				cp.init();
				currentConnections.getAndIncrement();
			}
		} catch (InterruptedException e) {
			LOGGER.error(e);
		} finally {
			lock.unlock();
		}
		return connectionPoolQueue.poll(3, TimeUnit.SECONDS); 
	  }
	
	public void releaseConnection(Connection con) throws InterruptedException{
		if (con != null) {
			connectionPoolQueue.offer(con, 3, TimeUnit.SECONDS); //a string will wait 3 seconds for a space in the blocking queue to become available
			LOGGER.info(con+" has been released");
		}
	}
	
	/* with synchronized
	 * public static ConnectionPool getInstance() {
	if (cp == null){
		synchronized(ConnectionPool.class) { 
			if (cp == null) {
				cp = new ConnectionPool();
			}
		}
	}
	return cp;
	}*/
	
	
	public static ConnectionPool getInstance() {
		lock.lock();
		try {
			if (cp == null) {
				cp = new ConnectionPool();
			}
		} finally {
			lock.unlock();
		}
		return cp;
	}
	
	
	@Override
	public String toString() {
		return connectionPoolQueue.toString();
	}
}
