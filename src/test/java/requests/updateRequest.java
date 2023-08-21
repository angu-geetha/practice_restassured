package requests;

import Utilities.LoggerLoad;

public class updateRequest {

	public String customerName;
	
public updateRequest( String customerName) {
		
		
		this.customerName = customerName;
		 LoggerLoad.logInfo("customerName: "+customerName);
	}
	
}
