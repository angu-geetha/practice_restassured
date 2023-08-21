package requests;

import Utilities.LoggerLoad;

public class AddPostRegisterClient {

	
	public String clientName;
	public String clientEmail;
	
	
	
	public AddPostRegisterClient(String clientName, String clientEmail) {
		
		this.clientName = clientName;
		this.clientEmail = clientEmail;
		 LoggerLoad.logInfo("clientName: "+clientName +"clientEmail: "+clientEmail);
	}
	
	
	
}
