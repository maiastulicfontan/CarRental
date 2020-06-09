package com.solvd.carRental.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MyBatisConnectionFactory {
	private static Logger LOGGER = LogManager.getLogger(MyBatisConnectionFactory.class);
	 private static SqlSessionFactory factory;

	    static {
	        Reader reader = null;
	        try {
	            reader = Resources.getResourceAsReader("mybatis-config.xml");
	        } catch (IOException e) {
	            LOGGER.error(e);
	        }
	        factory = new SqlSessionFactoryBuilder().build(reader);
	    }

	    public static SqlSessionFactory getSqlSessionFactory() {
	        return factory;
	    }

}
