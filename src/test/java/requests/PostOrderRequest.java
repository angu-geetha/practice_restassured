package requests;

import Utilities.LoggerLoad;

public class PostOrderRequest {


	public Integer bookId;
	public String customerName;
	
public PostOrderRequest(Integer bookId, String customerName) {
		
		this.bookId = bookId;
		this.customerName = customerName;
		 LoggerLoad.logInfo("bookId: "+ bookId +"customerName: "+customerName);
	}
	
	
}
