package com.capgemini.mps.utility;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import com.capgemini.mps.exception.MobilePurchaseException;

public class MySQLConnectionTest {

	@Test
	public void testGetConnection() throws MobilePurchaseException, SQLException {
		
			try {
				assertNotNull(MySQLConnection.getConnection());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
	}
}
