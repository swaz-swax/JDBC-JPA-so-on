package com.capgemini.mps.presentation;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.capgemini.mps.dto.Mobile;
import com.capgemini.mps.exception.MobilePurchaseException;
import com.capgemini.mps.service.IMobileService;
import com.capgemini.mps.service.MobileServiceImpl;


public class MPSTester {
	private static Scanner scanner=new Scanner(System.in);
	private static IMobileService mobileService=new MobileServiceImpl();
	//private static IPurchaseService purchaseService=new PurchaseServiceImpl();
	
	public static void main(String[] args) {		
		while (true) {
			// show menu
			System.out.println();
			System.out.println();
			System.out.println("   Mobile Purchase System ");
			System.out.println("_______________________________\n");

			System.out.println("1.Add Mobile ");
			System.out.println("2.Search Mobile Based On Price Range");
			System.out.println("3.Retrive All Mobiles");
			System.out.println("4.Delete Mobile");
			System.out.println("5.Purchase Mobile");
			System.out.println("6.Update Mobile Price");
			System.out.println("7.Get Mobile Details");
			System.out.println("8.Get Mobile Price");
			System.out.println("9.Exit");
			System.out.println("________________________________");
			System.out.println("Select an option:");
			// accept option
			try 
			{
				int n;
				int option = scanner.nextInt();
				switch (option) 
				{
				case 1:
					   Mobile mobile=new Mobile();
					   getMobileDetails(mobile);
					try 
					{
						n=addNewMobile(mobile);
						System.out.println(n);
						
					} 
					catch (MobilePurchaseException e) 
					{
						//Log to file
						e.printStackTrace();
					}
						
					  break;
				/*case 2: 
					  System.out.println("Enter lower-limit and upper-limit price range");
					  double lowPrice=scanner.nextDouble();
					  double highPrice=scanner.nextDouble();
					  List<Mobile> mobileList1=getMobilesPriceRange(lowPrice,highPrice);
					  showMobiles(mobileList1);
					  break;*/
				case 3:
					  List<Mobile> mobileList2= getAllMobileDetails();
					  showMobiles(mobileList2);
					  break;
				case 4:
					  System.out.println("Enter mobileId: ");
					  Long mobileId=scanner.nextLong();
					  n=deleteMobile(mobileId);
					  System.out.println(n);
					  break;
				/*case 5: 		  
					 
					  System.out.println("Enter 10-digit phone number");
					  Long phoneNumber=scanner.nextLong();
					  scanner.nextLine();//clears KBD buffer
					  System.out.println("Enter customer name(begin with uppercase and name cannot exceed 20 characters): ");
					  String name=scanner.nextLine();	
					  scanner.nextLine();//clears KBD buffer
					  System.out.println("Enter EmailId: ");
					  String emailId= scanner.nextLine();					  
					  
					  CustomerValidator validator=new CustomerValidator();
					  if(validator.isValidCustomerName(name)==true) {
						  if(validator.isValidEmail(emailId)==true) {
							  if(validator.isValidPhoneNumber(phoneNumber)==true) {
								  PurchaseDetails purchaseDetails=
										  	new PurchaseDetails();
								  purchaseDetails.setCustomerName(name);
								  purchaseDetails.setEmailId(emailId);
								  purchaseDetails.setPhoneNumber(phoneNumber);
								  System.out.println("Enter mobile Id: ");
								  Integer mid=scanner.nextInt();
								  try {
									if(mobileService.isValidMobileId(mid)==true) {
										  purchaseDetails.setMobileId(mid);
										  Integer purchaseId=addPurchaseDetails(name,emailId,phoneNumber,mid);										  
									}else {
										System.out.println("Enter valid mobileId");  
									}
								} catch (MobilePurchaseException e) {									
									  System.out.println(e.getMessage());
								}
							  }else {
								  System.out.println("Enter valid phone number");
							  }
						  }else {
							  System.out.println("Enter valid emailId");
						  }
					  }else {
						  System.out.println("Enter valid customer name");
					  }
					  break;
*/
				case 6:
					  System.out.println("Enter mobileId: ");
					  mobileId=scanner.nextLong();
					  System.out.println("Enter ne price: ");
					  Double newPrice=scanner.nextDouble();
					  n=updateMobilePrice(mobileId,newPrice);
					  System.out.println(n);
					break;
					
				case 7:
					System.out.println("Enter Mobile Id: ");
					mobileId=scanner.nextLong();
					mobile=getMobDetails(mobileId);
					System.out.println(mobile);
					
					break;
					
				case 8:
					System.out.println("Enter Mobile Id: ");
					mobileId=scanner.nextLong();
					Double price=getMobilePrice(mobileId);
					System.out.println("Price= "+price);
					
					break;
				case 9:
					System.out.print("Exit Mobile Purchase System");
					System.exit(0);					
				default:
					System.out.println("Enter a valid option[1-6]");
				}// end of switch
			}catch (InputMismatchException e) {
				scanner.nextLine();
				System.err.println("Please enter a numeric value, try again");
			}

		}// end of while
	}

	
	
	
	private static Double getMobilePrice(Long mobileId) 
	{
		try 
		{
			return mobileService.getMobilePrice(mobileId);
		} 
		catch (MobilePurchaseException e) 
		{
			e.getMessage();
		}
		return null;
	}




	private static Mobile getMobDetails(Long mobileId) 
	{
		try
		{
		return mobileService.getMobileDetails(mobileId);
		}
		catch(MobilePurchaseException e)
		{
			e.getMessage();
		}
		return null;
	}

	/*private static Integer addPurchaseDetails(String name, String emailId, Long phoneNumber, int mobileId) {
				Integer purchaseId=null;
				try {
					purchaseId= 
							purchaseService.addPurchaseDetails(name, emailId, phoneNumber, mobileId);
					System.out.println("Presentation Layer: "+purchaseId);
					return purchaseId;
				}catch(MobilePurchaseException e) {
					System.out.println(e.getMessage());
				}
				return purchaseId;
	}*/

	private static int updateMobilePrice(Long mobileId, Double newPrice) {
		int status=0;
		try 
		{
			status=mobileService.updateMobilePrice(mobileId, newPrice);
			return status;
		} 
		catch (MobilePurchaseException e) 
		{
			 //Log to file
			 System.out.println(e.getMessage());
		}
		return status;
	}

	private static int addNewMobile(Mobile mobile) throws MobilePurchaseException 
	{
		return mobileService.addNewMobile(mobile);
	}

	private static void getMobileDetails(Mobile mobile) 
	{
		
		
		System.out.println("Enter mobile price: ");
		mobile.setPrice(scanner.nextDouble());
		scanner.nextLine(); //clears the keyboard buffer
		System.out.println("Enter mobile quantity: ");
		mobile.setQuantity(scanner.nextInt());	
		scanner.nextLine(); //clears the keyboard buffer
		System.out.println("Enter mobile brand name: ");
		mobile.setName(scanner.nextLine());
	}

	private static Integer deleteMobile(Long mobileId) 
	{
		try 
		{
			int status=mobileService.deleteMobile(mobileId);
			return status;
		} 
		catch (MobilePurchaseException e) 
		{
			 //Log to file
			 System.out.println(e.getMessage());
		}
		return null;
	}

/*	private static List<Mobile> getMobilesPriceRange(double lowPrice, double highPrice) {
		try {
			return mobileService.getMobilesPriceRange(lowPrice, highPrice);
		} catch (MobilePurchaseException e) {	
			//Log to file
			System.out.println(e.getMessage());
		}
		return null;
	}
*/
	private static void showMobiles(List<Mobile> mobileList) {
		Iterator<Mobile> iterator=mobileList.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
	}

	private static List<Mobile> getAllMobileDetails() {
		List<Mobile> mobileList;
		try {
			mobileList = mobileService.getAllMobileDetails();
			return mobileList; 
		} catch (MobilePurchaseException e) {	
			//log to file
			System.out.println(e.getMessage());
		}
		return null;
	}

}


