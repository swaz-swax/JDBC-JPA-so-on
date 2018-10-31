package com.capgemini.mps.utility;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import com.capgemini.mps.exception.MobilePurchaseException;

public class DBConnectionTest {

	@Test
	public void testGetConnection() throws MobilePurchaseException, SQLException {
		assertNotNull(DBConnection.getConnection());
	}

}
