

package stepdefinitions;





import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;

import com.aventstack.extentreports.Status;



import DtaProviders.ConfigReader;
import DtaProviders.ExcelReader;
import endpoints.Book_endpoints;

import io.cucumber.java.en.Given;

import io.cucumber.java.en.Then;

import io.cucumber.java.en.When;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import io.restassured.path.json.JsonPath;

import io.restassured.response.Response;

import io.restassured.specification.RequestSpecification;
import requests.AddPostRegisterClient;
import requests.PostOrderRequest;
import requests.updateRequest;
import response.Book;

import response.Getstatus;
import response.getallorders;
import response.postorder;
import response.postregister;
import Utilities.DynamicValues;
import Utilities.LoggerLoad;






public class Book_SD  extends basestep {

	static final String baseUrl = ConfigReader.getProperty("base.url");

	RequestSpecification request;

	Response response;

	static String status1;

	static int id;
	
	Map<String, String> excelDataMap;
	PostOrderRequest order;
	 AddPostRegisterClient register;
	 updateRequest update;
	 
	 static String accessToken;
	 static int bookid;
	 static String orderid;
	 static int falsebookid;
	public void SetupPreRequisites() {

		response =  bookendpoint.Getstatus();

		Getstatus status = response.getBody().as(Getstatus.class);

		status1 =status.status;

		System.out.println(status1);

		response.then().statusCode(200);

		assertEquals(ConfigReader.getProperty("books.okstatus"), status1);

		}


	@Given("User creates GET Request for the  API endpoint with {string}")
	public void user_creates_get_request_for_the_api_endpoint_with(String dataKey) {
	   
		RestAssured.baseURI = baseUrl;

		request = RestAssured.given();
		if (!dataKey.equals("Invalid")) 
		{
			SetupPreRequisites();
		}

		
		
		
	}

	@When("User sends HTTPS get Request with {string}")
	public void user_sends_https_get_request_with(String dataKey) {
	    
		response =  bookendpoint.GetAllBooks(dataKey);
		
		
	}

	@Then("User receives Status Code  with response body for endpoint {string}")
	public void user_receives_status_code_with_response_body_for_endpoint(String dataKey) throws IOException {
	    
		response.then().log().all();

		if(dataKey.equals("Valid")) {

		response.then().statusCode(200);

		JsonPath jsonPathEvaluator = response.jsonPath();
		
		List<Book> bookList = jsonPathEvaluator.getList("", Book.class);

		bookid=(bookList.get(0).id);

		ConfigReader.setProperty("book.id", Integer.toString(bookid));

		response.then().assertThat()

		.body(JsonSchemaValidator.matchesJsonSchema(getClass().getClassLoader().getResourceAsStream("getalllistofbooks.json")));

		}else if(dataKey.equals("Invalid")) {

		if(response.statusCode() == 404 ) {

		System.out.println(response.statusCode()+"error");

		response.then().statusCode(404);

		}

		} else {

		assertTrue(false);

		}


		
		
		
		
	}

	@When("User sends HTTPS get Request with for type query parameter {string}")
	public void user_sends_https_get_request_with_for_type_query_parameter(String dataKey) {
		

		if(dataKey.equals(" fiction ")) {
		response =  bookendpoint.GetAllBookswithtypefiction(dataKey);
		}
		else {
			
			response =  bookendpoint. GetAllBookswithtypenonfiction(dataKey);
			
		}
	
	}
	

	@Then("User receives Status Code  with response body for type {string}")
	public void user_receives_status_code_with_response_body_for_type(String dataKey) {
	   
		response.then().log().all();

		response.then().statusCode(200);

		response.then().assertThat()

		.body(JsonSchemaValidator.matchesJsonSchema(getClass().getClassLoader().getResourceAsStream("getalllistofbooks.json")));

		JsonPath jsonPathEvaluator = response.jsonPath();

		List<Book> bookList = jsonPathEvaluator.getList("", Book.class);

		for (Iterator iterator = bookList.iterator(); iterator.hasNext();) {

		Book book = (Book) iterator.next();

		assertEquals(dataKey,book.type);

		}

		}
		
	

	@When("User sends HTTPS get Request with for available query parameter {string}")
	public void user_sends_https_get_request_with_for_available_query_parameter(String dataKey) {
	   

		if(dataKey.equals("True")) {
		response =  bookendpoint.GetAllBookswithAvailableTrue(dataKey);
		}
		else {
			
			response =  bookendpoint. GetAllBookswithAvailableFalse(dataKey);
			
		}
		
		
		
		
	}

	@Then("User receives Status Code  with response body for available {string}")
	public void user_receives_status_code_with_response_body_for_available(String dataKey) {
	   
		
		response.then().log().all();

		response.then().statusCode(200);

		response.then().assertThat()

		.body(JsonSchemaValidator.matchesJsonSchema(getClass().getClassLoader().getResourceAsStream("getalllistofbooks.json")));

		JsonPath jsonPathEvaluator = response.jsonPath();

		List<Book> bookList = jsonPathEvaluator.getList("", Book.class);

		for (Iterator iterator = bookList.iterator(); iterator.hasNext();) {

		Book book = (Book) iterator.next();

		assertEquals(dataKey,book.available);
		
			if(book.available.equals(dataKey)) {
				
				falsebookid=book.id;
				LoggerLoad.logDebug("book id - " + falsebookid);
			}
			}
		
			
			
		}
{
			
			
		}
		
		
		
		
		
	
	
	
	@Given("User creates Post Request for the  API endpoint with fields from {string} with {string}")
	public void user_creates_post_request_for_the_api_endpoint_with_fields_from_with(String sheetName, String dataKey) {
	

		try {
			
			RestAssured.baseURI = baseUrl;
		     request =RestAssured.given();
			request.header("Content-Type", "application/json");
			
			excelDataMap = null;
			excelDataMap = ExcelReader.getData(dataKey, sheetName);
			
		
			String clientName=null,clientEmail=null;
					String	dynamicclientname =null;
					String	dynamicclientemail =null;
					
			if(null != excelDataMap && excelDataMap.size() > 0 ) { 	
				excelDataMap = ExcelReader.getData(dataKey, sheetName);
				
				if(dataKey.equals("InValid_Existing")){
					dynamicclientname=ConfigReader.getProperty("existing.clientname");
				 LoggerLoad.logDebug("clientName: "+dynamicclientname);
				 dynamicclientemail=ConfigReader.getProperty("existing.clientemail");
				 LoggerLoad.logDebug("clientEmail: "+dynamicclientemail);
				
			}
				else {
					if(!(dataKey.equals("Post_Missing_Clientname")) && (!(excelDataMap.get("clientName").isBlank()))) {

						clientName=excelDataMap.get("clientName");

						dynamicclientname = clientName + DynamicValues.SerialNumber();

						LoggerLoad.logDebug("clientName: "+dynamicclientname);

						}
				}
					
					if(!dataKey.equals("Post_Missing_clientEmail")&&(!(excelDataMap.get("clientEmail").isBlank()))) {
						clientEmail=excelDataMap.get("clientEmail");

						dynamicclientemail = DynamicValues.SerialNumber() + clientEmail;

						LoggerLoad.logDebug("clientEmail: "+dynamicclientemail);
					}
				
				
				 
				 register = new  AddPostRegisterClient(dynamicclientname,dynamicclientemail);	
				 LoggerLoad.logInfo(" POST request  API client created");
				
				
			}	
			
			
		}
		 catch (Exception e) {
			 LoggerLoad.logInfo(e.getMessage());
			e.printStackTrace();
		}
		
		
		
	}
	@When("User sends HTTPS Post  Request with requestbody with {string}")
	public void user_sends_https_post_request_with_requestbody_with(String datakey) {

		try 
		{
			response = bookendpoint.PostRegisterApiClient(register,datakey);
			LoggerLoad.logInfo("client POST request sent");
			
		
		} 
		catch (Exception ex) 
		{
			LoggerLoad.logInfo(ex.getMessage());
			ex.printStackTrace();
		}
	
		}
		
	

	@Then("User receives Status Code  with response body for endpoint with {string} and  {string}")
	public void user_receives_status_code_with_response_body_for_endpoint_with_and(String dataKey, String sheetName)throws IOException {
		try 
		{
			
			switch(dataKey)
			{
				case "Valid" : 

					response.then().log().all();
					response.then().assertThat()
						// Validate response status
						.statusCode(HttpStatus.SC_CREATED)
						// Validate content type
						.contentType(ContentType.JSON)
						// Validate json schema
						.body(JsonSchemaValidator.matchesJsonSchema(
							getClass().getClassLoader().getResourceAsStream("postregisterclient.json")));

			postregister postregister = response.getBody().as(postregister.class);

			accessToken=postregister.accessToken;

			ConfigReader.setProperty("access.token", accessToken);

			LoggerLoad.logDebug("accessToken:" + accessToken );
			
			
			break;
			
				case "InValid_Existing" : 
					response.then().log().all();
					response.then().statusCode(409);
					response.then().assertThat()

					// Validate response status

				
					// Validate content type

					.contentType(ContentType.JSON)

					// Validate json schema

					.body(JsonSchemaValidator.matchesJsonSchema(

					getClass().getClassLoader().getResourceAsStream("errorpostregister.json")));

					break;
	         case "InValid_Endpoint" : 
	    	   response.then().log().all();
	    	  response.then().statusCode(404);
	    	 
	    

			
	    	break;
	         case "Post_Missing_Clientname" :
	         case "Post_Missing_clientEmail": 
		    	   response.then().log().all();
		    	  response.then().statusCode(400);
		    	  response.then().assertThat()
		    	.contentType(ContentType.JSON)
		    	
		    	  .body(JsonSchemaValidator.matchesJsonSchema(

							getClass().getClassLoader().getResourceAsStream("errorpostregister.json")));

				
		    	break;		default :

					assertTrue(false);

					break;

					}
			

			// Validate values in response} 
			
			
		
			
		
			
		}
		catch (Exception ex) 
		{
			LoggerLoad.logInfo(ex.getMessage());
			ex.printStackTrace();
		}

	}
	
	

	
	
	@Given("User creates Post Request for book order  with fields from {string} with {string}")
	public void user_creates_post_request_for_book_order_with_fields_from_with(String sheetName, String dataKey) {
	try {
			
			RestAssured.baseURI = baseUrl;
		     request =RestAssured.given();
			
			
			
			 
			
			excelDataMap = null;
			excelDataMap = ExcelReader.getData(dataKey, sheetName);
			
		
			String customername=null;
			Integer Bookid = null;
			if(null != excelDataMap && excelDataMap.size() > 0 ) { 	
				excelDataMap = ExcelReader.getData(dataKey, sheetName);
		
				if(!(excelDataMap.get("customername").isBlank())){
					customername =excelDataMap.get("customername");
					LoggerLoad.logDebug("customername - " + customername);
				}
				
				if(dataKey.equals("PostValid")&&(!dataKey.equals("Post_Missing_BookId"))&&!dataKey.equals("PostInValidBookId")){
				Bookid= bookid;
				LoggerLoad.logDebug("book id - " + Bookid);
				}
				
				
				if(dataKey.equals("PostInValidBookId")){
					Bookid= bookid +44;
					LoggerLoad.logDebug("book id - " + Bookid);
					}	
				
				
					}
			
			order = new PostOrderRequest(Bookid,customername);
				
	
	
					
	}	 catch (Exception e) {
		 LoggerLoad.logInfo(e.getMessage());
		e.printStackTrace();
	}
		
	}	

	@When("User sends HTTPS  post Request for post order with {string}")
	public void user_sends_https_post_request_for_post_order_with(String dataKey) {

		try 
		{
			response = bookendpoint.PostBookOrder(order,dataKey,accessToken);
			LoggerLoad.logInfo("client POST request sent");
			
		
		} 
		catch (Exception ex) 
		{
			LoggerLoad.logInfo(ex.getMessage());
			ex.printStackTrace();
		}
	
		}
		
	
		
		
	

	@Then("User receives Status Code  with response body for post order with {string} and  {string}")
	public void user_receives_status_code_with_response_body_for_post_order_with_and(String dataKey, String sheetName) throws IOException {
		
			
			switch(dataKey)
			{
				case "PostValid" : 

					response.then().log().all();
					postorder postorder = response.getBody().as(postorder.class);

					orderid=postorder.orderId;

					ConfigReader.setProperty("order.id", orderid);

					LoggerLoad.logDebug("accessToken:" + accessToken );
					
					response.then().assertThat()
						// Validate response status
						.statusCode(HttpStatus.SC_CREATED)
						// Validate content type
						.contentType(ContentType.JSON)
						// Validate json schema
						.body(JsonSchemaValidator.matchesJsonSchema(
							getClass().getClassLoader().getResourceAsStream("postsuccessorder.json")));

		break;
		
				case "PostInValidEndpoint" :
					
					 response.then().log().all();
			    	  response.then().statusCode(404);
			    	  
			    	  
			    		break;  
			    		
	
				case "Post_Missing_BookId" : 		
				case "PostInValidBookId" : 
					
					
					 response.then().log().all();
			    	  response.then().statusCode(400);
			    	  response.then().assertThat()
			    	.contentType(ContentType.JSON)
			    	
			    	  .body(JsonSchemaValidator.matchesJsonSchema(

								getClass().getClassLoader().getResourceAsStream("errorpostregister.json")));
			    	  break; 
				
				case "PostBookId_noStock" : 
					
					
					 response.then().log().all();
			    	  response.then().statusCode(404);
			    	  response.then().assertThat()
			    	.contentType(ContentType.JSON)
			    	
			    	  .body(JsonSchemaValidator.matchesJsonSchema(

								getClass().getClassLoader().getResourceAsStream("errorpostregister.json")));
			    	  break; 
	}
		}
	
	
	@Given("Check if user able to retrieve  list of orders with endpoint {string}")
	public void check_if_user_able_to_retrieve_list_of_orders_with_endpoint(String dataKey) {

		RestAssured.baseURI = baseUrl;
	     request =RestAssured.given();
		
		
		
		
	}

	@When("User creates GET Request for the  get all orders with {string}")
	public void user_creates_get_request_for_the_get_all_orders_with(String dataKey) {
		response =  bookendpoint.GetAllOrdes(dataKey,accessToken);
		
	}

	@Then("User receives Status Code  with response body for  get all orders {string}")
	public void user_receives_status_code_with_response_body_for_get_all_orders(String dataKey) throws IOException {
	  
		if(dataKey.equals("Valid")) {
			response.then().log().all();
			
			response.then().statusCode(200);
			
			  response.then().assertThat()
			.contentType(ContentType.JSON)
			// Validate json schema
			.body(JsonSchemaValidator.matchesJsonSchema(
				getClass().getClassLoader().getResourceAsStream("getallordersschema.json")));

		
		
	}
		else if(dataKey.equals("Invalid"))
			
		{
			response.then().log().all();
			response.then().statusCode(404);
			
			
		
		}
	}

	@Given("Check if user able to  get single order with orderid {string}")
	public void check_if_user_able_to_get_single_order_with_orderid(String string) {
	    
		RestAssured.baseURI = baseUrl;
		request = RestAssured.given();

		
		
	}

	@When("User creates GET Request for the  get single order with orderid {string}")
	public void user_creates_get_request_for_the_get_single_order_with_orderid(String dataKey) {
	    
		response =  bookendpoint.GetSingleOrder(dataKey,accessToken,orderid);
	}

	@Then("User receives Status Code  with response body for  get single order with orderid {string}")
	public void user_receives_status_code_with_response_body_for_get_single_order_with_orderid(String dataKey) {
		if(dataKey.equals("Valid")) {
			response.then().log().all();
			
			response.then().statusCode(200);
			
			
			  response.then().assertThat()
			.contentType(ContentType.JSON)
			// Validate json schema
			.body(JsonSchemaValidator.matchesJsonSchema(
				getClass().getClassLoader().getResourceAsStream("getsingleorder.json")));

		
		
	}
		else if(dataKey.equals("Invalid"))
			
		{
			response.then().log().all();
			response.then().statusCode(404);
			 response.then().assertThat()
			.body(JsonSchemaValidator.matchesJsonSchema(
					getClass().getClassLoader().getResourceAsStream("errorpostregister.json")));

			
			
		
		}
	}
		
	
	
	@Given("User creates GET Request with the single book id and  API {string}")
	public void user_creates_get_request_with_the_single_book_id_and_api(String string) {
	   
		
		RestAssured.baseURI = baseUrl;
		request = RestAssured.given();

	}

	@When("User sends HTTPS  get Request for single book with bookid {string}")
	public void user_sends_https_get_request_for_single_book_with_bookid(String dataKey) {
	
		response =  bookendpoint.GetSingleBookwithBookId(dataKey, bookid);
	}

	@Then("User receives Status Code  with response body for single book with  bookid {string}")
	public void user_receives_status_code_with_response_body_for_single_book_with_bookid(String dataKey) {
	    

		if(dataKey.equals("Valid")) {
			response.then().log().all();
			response.then().statusCode(200);
			
			
			  response.then().assertThat()
			.contentType(ContentType.JSON)
			// Validate json schema
			.body(JsonSchemaValidator.matchesJsonSchema(
				getClass().getClassLoader().getResourceAsStream("getsinglebook.json")));

		
		
	}
		else if(dataKey.equals("Invalid"))
			
		{
			response.then().log().all();
			response.then().statusCode(404);
			
			
		
		}
		
	}

	@Given("User creates Put Request for book order  with fields from {string} with {string}")
	public void user_creates_put_request_for_book_order_with_fields_from_with(String sheetName, String dataKey) {

		
		try {
			
			RestAssured.baseURI = baseUrl;
		     request =RestAssured.given();
			
			
			
			 
			
			excelDataMap = null;
			excelDataMap = ExcelReader.getData(dataKey, sheetName);
			
		
			String customername=null;
			
			if(null != excelDataMap && excelDataMap.size() > 0 ) { 	
				excelDataMap = ExcelReader.getData(dataKey, sheetName);
		
				if(!(excelDataMap.get("customername").isBlank())){
					customername =excelDataMap.get("customername");
					LoggerLoad.logDebug("customername - " + customername);
				}
				update = new updateRequest(customername);
		
	}
		}	 catch (Exception e) {
			 LoggerLoad.logInfo(e.getMessage());
			e.printStackTrace();
		}
			
		}	
	@When("User sends HTTPS  put Request for book order with {string}")
	public void user_sends_https_put_request_for_book_order_with(String dataKey) {
	    
		response =  bookendpoint.updateOrder(update,dataKey,accessToken,orderid);
	}

	@Then("User receives Status Code  with response body for put order with {string} and  {string}")
	public void user_receives_status_code_with_response_body_for_put_order_with_and(String dataKey, String sheetName) {
	    
		
		if(dataKey.equals("ValidPut_Customername")) {
			response.then().log().all();
			response.then().statusCode(204);
			
			
			
		
		
	}
		else if(dataKey.equals("InvalidPut_Customername"))
			
		{
			response.then().log().all();
			response.then().statusCode(404);
			
			
		
		}
		
	}

	@Given("User creates delete Request for book order  with fields with {string}")
	public void user_creates_delete_request_for_book_order_with_fields_with(String string) {
	    
		RestAssured.baseURI = baseUrl;
		request = RestAssured.given();

		
	}

	@When("User sends HTTPS  delete Request for book order with {string}")
	public void user_sends_https_delete_request_for_book_order_with(String dataKey) {
	   
		response =  bookendpoint.deleteOrder(dataKey,accessToken,orderid);
		
	}

	@Then("User receives Status Code  with response body for put order with {string}")
	public void user_receives_status_code_with_response_body_for_put_order_with(String dataKey) {
	  
		
		
		if(dataKey.equals("Valid")) {
			response.then().log().all();
			response.then().statusCode(204);
			
			
			
		
		
	}
		else if(dataKey.equals("Invalid"))
			
		{
			response.then().log().all();
			response.then().statusCode(404);
			
			
		
		}
		
	}
		
	



}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	
	
	

