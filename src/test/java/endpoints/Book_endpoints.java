package endpoints;



import Routes.Bookroutes;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import requests.AddPostRegisterClient;
import requests.PostOrderRequest;
import requests.updateRequest;

public class Book_endpoints {

	String baseUrl;
	
	
	public Book_endpoints(String baseUrl) {
		this.baseUrl = baseUrl;
	}



	public Response GetAllBooks(String dataKey)
	{
		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		Response response = request.get(Bookroutes .getAllBooks(dataKey));
		return response;
	}

	public Response Getstatus()
	{
		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		Response response = request.get(Bookroutes .getstatus());
		return response;
	}
	public Response GetAllBookswithtypefiction(String dataKey)
	{
		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		Response response = request.queryParam("type","fiction").get(Bookroutes .getAllBooks(dataKey));
		return response;
	}
	public Response GetAllBookswithtypenonfiction(String dataKey)
	{
		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		Response response = request.queryParam("type","non-fiction").get(Bookroutes .getAllBooks(dataKey));
		return response;
	}
	
	public Response GetAllBookswithAvailableTrue(String dataKey)
	{
		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		Response response = request.queryParam("available","true").get(Bookroutes .getAllBooks(dataKey));
		return response;
	}
	
	public Response GetAllBookswithAvailableFalse(String dataKey)
	{
		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		Response response = request.queryParam("available","false").get(Bookroutes .getAllBooks(dataKey));
		return response;
	}
	public Response GetSingleBookwithBookId(String dataKey,Integer bookid)
	{
		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		Response response = request.get(Bookroutes . getSingleBook(dataKey,bookid));
		return response;
	}
	
	public Response PostRegisterApiClient( AddPostRegisterClient register,String dataKey)
	{
		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");

		Response response = request.body(register).post(Bookroutes.registerApiClientroutes(dataKey));
		
		return response;
	}
	
	public Response PostBookOrder( PostOrderRequest order,String dataKey,String accessToken)
	{
		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer " + accessToken);
		request.header("Content-Type", "application/json");
		
		
		Response response = request.body(order).post(Bookroutes.postOrderRoute(dataKey));
		
		return response;
	}
	public Response GetAllOrdes(String dataKey,String accessToken)
	{
		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.header("Authorization", "Bearer " + accessToken);
		Response response = request.get(Bookroutes .getAllOrders(dataKey));
		return response;
	}
	
	public Response GetSingleOrder(String dataKey,String accessToken,String orderid)
	{
		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.header("Authorization", "Bearer " + accessToken);
		Response response = request.get(Bookroutes .getSingleOrder(dataKey,orderid));
		return response;
	}
	
	public Response updateOrder(updateRequest update,String dataKey,String accessToken,String orderid)
	{
		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.header("Authorization", "Bearer " + accessToken);
		Response response = request.body(update).patch(Bookroutes .updateOrder(dataKey,orderid));
		return response;
	}

	public Response deleteOrder(String dataKey,String accessToken,String orderid)
	{
		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.header("Authorization", "Bearer " + accessToken);
		Response response = request.delete(Bookroutes .deleteOrder(dataKey,orderid));
		return response;
	}
}



