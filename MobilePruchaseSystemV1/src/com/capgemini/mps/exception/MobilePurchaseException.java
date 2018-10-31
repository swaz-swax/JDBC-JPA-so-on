package com.capgemini.mps.exception;

public class MobilePurchaseException extends Exception{
private String status;
public MobilePurchaseException(){
	status="Mobile Purchase exception";
}
/**
 * @param status
 */
public MobilePurchaseException(String status) {
	super();
	this.status = status;
}
public String getStatus() {
	return status;
}



}
