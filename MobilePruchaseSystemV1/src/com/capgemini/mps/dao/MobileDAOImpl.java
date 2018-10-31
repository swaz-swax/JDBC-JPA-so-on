package com.capgemini.mps.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.mps.dto.Mobile;
import com.capgemini.mps.exception.MobilePurchaseException;
//import com.capgemini.mps.utility.DBConnection;
import com.capgemini.mps.utility.MySQLConnection;


public class MobileDAOImpl implements IMobileDAO {

	@Override
	public Integer addNewMobile(Mobile mobile) throws MobilePurchaseException 
	{
		int n;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		try
		{
			connection=MySQLConnection.getConnection();
			preparedStatement=connection.prepareStatement(QueryMapper.INSERT_MOBILE);
			//preparedStatement.setLong(1, mobile.getMobileId());
			preparedStatement.setString(1, mobile.getName());
			preparedStatement.setDouble(2, mobile.getPrice());
			preparedStatement.setInt(3, mobile.getQuantity());
			n=preparedStatement.executeUpdate();
			return n;
		}
		catch(SQLException e)
		{
			//e.printStackTrace();
			//TODO : Log to file
			throw new MobilePurchaseException("Unale to add new mobile" +e.getMessage());
		}
		catch(Exception e)
		{
			throw new MobilePurchaseException(e.getMessage());
		}
		finally
		{
			try 
			{
				connection.close();
			} 
			catch (SQLException e) 
			{
				
				e.printStackTrace();
			}
		}
		
		
	}

	@Override
	public Integer deleteMobile(Long mobileId) throws MobilePurchaseException {
		try(
				Connection connection=MySQLConnection.getConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(QueryMapper.DELETE_MOBILE);
				)
		{
			preparedStatement.setLong(1, mobileId);
			int n=preparedStatement.executeUpdate();
			return n;
		}
		catch(SQLException e)
		{
			throw new MobilePurchaseException("Unable to delete mobile details.\n"+e.getMessage());
		}
		catch(IOException e)
		{
			throw new MobilePurchaseException(e.getMessage());
		}

	}

	@Override
	public Mobile getMobileDetails(Long mobileId) throws MobilePurchaseException 
	{
		try
		(
				Connection connection=MySQLConnection.getConnection();
				CallableStatement callableStatement=connection.prepareCall(QueryMapper.GET_MOBILE_DETAILS);
				)
				{
					callableStatement.setLong(1, mobileId);
					//have to register the OUT parameter mode from procedures or functions
					callableStatement.registerOutParameter(2, Types.VARCHAR);
					callableStatement.registerOutParameter(3, Types.DOUBLE);
					callableStatement.registerOutParameter(4, Types.INTEGER);
					callableStatement.executeQuery();
					Mobile mobile=new Mobile();
					mobile.setMobileId(mobileId);
					populateMobile(mobile, callableStatement);
					return mobile;
					
				}
		catch(SQLException e)
		{
			throw new MobilePurchaseException("Unable to get mobile details.\n"+e.getMessage());
		}
		catch(IOException e)
		{
			throw new MobilePurchaseException(e.getMessage());
		}
				
	}

	private void populateMobile(Mobile mobile,
			CallableStatement callableStatement) throws SQLException {
		mobile.setName(callableStatement.getString(2));
		mobile.setPrice(callableStatement.getDouble(3));
		mobile.setQuantity(callableStatement.getInt(4));
		
	}

	@Override
	public List<Mobile> getAllMobileDetails() throws MobilePurchaseException {
		try
		(
				Connection connection=MySQLConnection.getConnection();
				Statement statement= connection.createStatement();
		)
		{
			ResultSet resultSet=statement.executeQuery(QueryMapper.SELECT_ALL_MOBILES);
			List<Mobile> mobileList=new ArrayList<Mobile>();
			
			while(resultSet.next())
			{
				Mobile mobile=new Mobile();
				populateMobile(resultSet, mobile);
				
				mobileList.add(mobile);
				
			}
			return mobileList;
		}
		catch(SQLException e)
		{
			//Log to file
			throw new MobilePurchaseException(e.getMessage());
		}
		catch(IOException e)
		{
			//Log to file
			throw new MobilePurchaseException(e.getMessage());
		}
		
	}

	private void populateMobile(ResultSet resultSet, Mobile mobile)
			throws SQLException {
		mobile.setMobileId(resultSet.getLong("mobile_id"));
		mobile.setName(resultSet.getString("name"));
		mobile.setPrice(resultSet.getDouble("price"));
		mobile.setQuantity(resultSet.getInt("quantity"));
	}

	@Override
	public Integer updateMobilePrice(Long mobileId, Double newPrice)
			throws MobilePurchaseException {
		int n;
		try
		(
				Connection connection=MySQLConnection.getConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(QueryMapper.UPDATE_MOBILE_PRICE);
		)
		{
			preparedStatement.setDouble(1, newPrice);
			preparedStatement.setLong(2, mobileId);
			n=preparedStatement.executeUpdate();
			return n;
			
		}catch(SQLException e)
		{
			//Log to file
			throw new MobilePurchaseException("Unale to update new price" + e.getMessage());
		}
		catch(IOException e)
		{
			//Log to file
			throw new MobilePurchaseException(e.getMessage());
		}
	
	}

	@Override
	public Double getMobilePrice(Long mobileId) throws MobilePurchaseException {
		try
		(
				Connection connection=MySQLConnection.getConnection();
				CallableStatement callableStatement=connection.prepareCall(QueryMapper.GET_MOBILE_PRICE);
				)
				{
					
					//have to register the OUT parameter mode from procedures or functions
					callableStatement.registerOutParameter(1, Types.DOUBLE);
					
					callableStatement.setLong(2, mobileId);
					
					callableStatement.execute();
					Double price=callableStatement.getDouble(1);
					return price;
					
				}
		catch(SQLException e)
		{
			throw new MobilePurchaseException("Unable to get mobile price.\n"+e.getMessage());
		}
		catch(IOException e)
		{
			throw new MobilePurchaseException(e.getMessage());
		}
		
	}

}
