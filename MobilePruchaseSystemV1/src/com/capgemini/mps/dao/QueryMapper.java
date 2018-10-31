package com.capgemini.mps.dao;

public interface QueryMapper 
{
	public static final String INSERT_MOBILE="insert into mobiles_cg(name,price,quantity) values(?,?,?)";
	public static final String DELETE_MOBILE="Delete from mobiles_cg where mobile_id=?";
	public static final String SELECT_MOBILE="Select * from mobiles_cg where mobile_id=?";
	public static final String UPDATE_MOBILE_PRICE="UPDATE mobiles_cg SET price = ? WHERE mobile_id=?";
	public static final String SELECT_ALL_MOBILES="Select * from mobiles_cg";
	public static final String GET_MOBILE_DETAILS="{call get_mobile_details(?,?,?,?)}";
	public static final String GET_MOBILE_PRICE="{?= call get_mobile_price(?)}";
	
}
