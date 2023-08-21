package stepdefinitions;

import endpoints.Book_endpoints;

public class basestep {

	
	String baseUrl ="https://simple-books-api.glitch.me";


	Book_endpoints bookendpoint;


	public basestep() {
		 bookendpoint = new Book_endpoints (baseUrl);
		 
		 
		 
		 
		 
		 
	}
	
	
	
	
	
	
	
	
}
