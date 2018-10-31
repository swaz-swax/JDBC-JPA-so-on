package com.capgemini.mps.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.capgemini.mps.exception.MobilePurchaseException;

import oracle.jdbc.pool.OracleDataSource;

public class DBConnection {
	private static Connection connection = null;
	private static DBConnection instance = null;
	private static Properties properties = null;
	private static OracleDataSource dataSource = null;
	
	private DBConnection() throws MobilePurchaseException {
		try {
			properties = loadProperties();
			dataSource = prepareDataSource();
		} catch (IOException e) {
			throw new MobilePurchaseException(" Could not read the database details from properties file");
		} catch (SQLException e) {
			throw new MobilePurchaseException(e.getMessage());
		}

	}
	
	/**
	 * 
	 * @return returns Properties object
	 * @throws IOException
	 */
	private Properties loadProperties() throws IOException {
		if (properties == null) {
			Properties newProps = new Properties();
			String fileName = "resources/jdbc.properties";

			InputStream inputStream = new FileInputStream(fileName);
			newProps.load(inputStream);
			inputStream.close();
			return newProps;
		} else {
			return properties;
		}
	}
	
	private OracleDataSource prepareDataSource() throws SQLException {

		if (dataSource == null) {
			if (properties != null) {
				String connectionURL = properties.getProperty("url");
				String username = properties.getProperty("username");
				String password = properties.getProperty("password");
				dataSource = new OracleDataSource();
				dataSource.setURL(connectionURL);
				dataSource.setUser(username);
				dataSource.setPassword(password);
			}
		}
		return dataSource;
	}
	
	public static Connection getConnection() throws MobilePurchaseException, SQLException {
		if(connection == null) {
			if(instance==null) {
				instance=new DBConnection();
			}
			return dataSource.getConnection();
		}
		return connection;
	}
	
}
