package com.capgemini.mps.service;

import java.util.List;

import com.capgemini.mps.dao.IMobileDAO;
import com.capgemini.mps.dao.MobileDAOImpl;
import com.capgemini.mps.dto.Mobile;
import com.capgemini.mps.exception.MobilePurchaseException;

public class MobileServiceImpl implements IMobileService 
{
	private IMobileDAO mobileDAO=new MobileDAOImpl();
	
	@Override
	public Integer addNewMobile(Mobile mobile) throws MobilePurchaseException 
	{
		//TODO: validate mobile details
		return mobileDAO.addNewMobile(mobile);
	}

	@Override
	public Integer deleteMobile(Long mobileId) throws MobilePurchaseException {
		// TODO Auto-generated method stub
		return mobileDAO.deleteMobile(mobileId);
	}

	@Override
	public Mobile getMobileDetails(Long mobileId)
			throws MobilePurchaseException {
		// TODO Auto-generated method stub
		return mobileDAO.getMobileDetails(mobileId);
	}

	@Override
	public List<Mobile> getAllMobileDetails() throws MobilePurchaseException {
		// TODO Auto-generated method stub
		return mobileDAO.getAllMobileDetails();
	}

	@Override
	public Integer updateMobilePrice(Long mobileId, Double newPrice)
			throws MobilePurchaseException {
		// TODO Auto-generated method stub
		return mobileDAO.updateMobilePrice(mobileId, newPrice);
	}

	@Override
	public Double getMobilePrice(Long mobileId) throws MobilePurchaseException {
		
		return mobileDAO.getMobilePrice(mobileId);
	}

}
