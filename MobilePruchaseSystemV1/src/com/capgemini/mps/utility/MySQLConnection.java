package com.capgemini.mps.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.capgemini.mps.exception.MobilePurchaseException;

public class MySQLConnection 
{
	private static Properties properties = null;
	private static Connection connection = null;
	
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
	
	public static Connection getConnection() throws MobilePurchaseException, IOException
	{
		properties=new MySQLConnection().loadProperties();
		/*if(connection==null)
		{*/
			String url=properties.getProperty("url");
			String username=properties.getProperty("username");
			String password=properties.getProperty("password");
			try {
				connection=DriverManager.getConnection(url,username,password);
				return connection;
			} 
			catch (SQLException e) 
			{
				
				e.printStackTrace();
			}
			
		//}
		return null;
		
	}
}
