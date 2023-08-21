package Routes;

import DtaProviders.ConfigReader;
import Utilities.LoggerLoad;

public class Bookroutes {

	
	public static String getAllBooks(String dataKey)
	{
		String endpoint = null;
		if("Invalid".equals(dataKey))
			endpoint = ConfigReader.getBooksGetAllUrl() + "ss";
		else 
			endpoint = ConfigReader.getBooksGetAllUrl();	

		
		System.out.println("endpoint in "+endpoint);
		return endpoint;
		
	}
	public static String getstatus()
	{
		String endpoint = null;
	try {
			endpoint = ConfigReader.getstatus();	
	
		
		System.out.println("endpoint in "+endpoint);
		
	}
	catch (Exception e){
	
		LoggerLoad.logInfo(e.getMessage());
		e.printStackTrace();
	
	}
	return endpoint;
	}
	
	public static String getSingleBook(String dataKey,Integer bookid)
	{
		String endpoint = null;
		if("Invalid".equals(dataKey))
			endpoint = ConfigReader.getSingleBookUrl() + "ss";
		else 
			endpoint = ConfigReader.getSingleBookUrl()	+ bookid;

		
		System.out.println("endpoint in "+endpoint);
		return endpoint;
		
	}
	
	public static String registerApiClientroutes(String dataKey)
	{  
		
		String endpoint = null;
	if("InValid_Endpoint".equals(dataKey))
		endpoint = ConfigReader. postRegister() + "ss";
	else 
		endpoint = ConfigReader. postRegister();	

	
	System.out.println("endpoint in "+endpoint);
	return endpoint;
	
	}
	public static String postOrderRoute(String dataKey)
	{  
		
		String endpoint = null;
	if("PostInValidEndpoint".equals(dataKey))
		endpoint = ConfigReader. postOrder() + "ss";
	else 
		endpoint = ConfigReader. postOrder();	

	
	System.out.println("endpoint in "+endpoint);
	return endpoint;
	
	}
	public static String getAllOrders(String dataKey)
	{
		String endpoint = null;
		if("Invalid".equals(dataKey))
			endpoint = ConfigReader.getAllOrders() + "ss";
		else 
			endpoint = ConfigReader.getAllOrders();	

		
		System.out.println("endpoint in "+endpoint);
		return endpoint;
		
	}
	public static String getSingleOrder(String dataKey,String orderid )
	{
		String endpoint = null;
		if("Invalid".equals(dataKey))
			endpoint = ConfigReader.getSingleOrder() + orderid + "ss";
		else 
			endpoint = ConfigReader.getSingleOrder()+orderid;

		
		System.out.println("endpoint in "+endpoint);
		return endpoint;
		
}
	public static String updateOrder(String dataKey,String orderid )
	{
		String endpoint = null;
		if("InvalidPut_Customername".equals(dataKey))
			endpoint = ConfigReader.updateOrder() + orderid+ "ss";
		else 
			endpoint = ConfigReader.updateOrder()+orderid;

		
		System.out.println("endpoint in "+endpoint);
		return endpoint;
		
}
	
	public static String deleteOrder(String dataKey,String orderid )
	{
		String endpoint = null;
		if("Invalid".equals(dataKey))
			endpoint = ConfigReader.deleteOrder() + orderid+ "ss";
		else 
			endpoint = ConfigReader.deleteOrder()+orderid;

		
		System.out.println("endpoint in "+endpoint);
		return endpoint;
		
}
}
