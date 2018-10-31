package com.capgemini.mps.service;

import java.util.List;

import com.capgemini.mps.dto.Mobile;
import com.capgemini.mps.exception.MobilePurchaseException;

public interface IMobileService 
{
	  public Integer addNewMobile(Mobile mobile) throws MobilePurchaseException ;
	  public Integer deleteMobile(Long mobileId) throws MobilePurchaseException ;
	  public Mobile getMobileDetails(Long mobileId) throws MobilePurchaseException ;
	  public Integer updateMobilePrice(Long mobileId, Double newPrice) throws MobilePurchaseException ;
	  public List<Mobile> getAllMobileDetails() throws MobilePurchaseException ;
	  public Double getMobilePrice(Long mobileId) throws MobilePurchaseException ;
	  
}
