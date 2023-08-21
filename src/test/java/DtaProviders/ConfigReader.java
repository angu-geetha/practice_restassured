package DtaProviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import Utilities.LoggerLoad;


public class ConfigReader {

	private static Properties properties;
	private static final String propertyFilePath = "src/test/resources/configs/Configuration.properties";

	public static void loadProperty() 
	{
		BufferedReader reader;
		try 
		{
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try 
			{
				properties.load(reader);
				reader.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}
	}
	
	public static String getProperty( String key) {
		return properties.getProperty(key);
	}
	
	public static void setProperty(String key, String value) throws IOException {
		
		FileOutputStream out;
		try {
			out = new FileOutputStream(propertyFilePath);
			properties.setProperty(key, value);;
			properties.store(out, null);
		} catch (FileNotFoundException e) {
			LoggerLoad.logInfo(e.getMessage());
			e.printStackTrace();
		}	 
	}
	
	
	public static String getBooksGetAllUrl()
	{
		String val = properties.getProperty("books.getallurl");
		if (val != null)
			return val;
		else
			throw new RuntimeException("books.getallurl not specified in the Configuration.properties file.");
	}
	
	public static String getstatus()
	{
		String val = properties.getProperty("books.getstatus");
		if (val != null)
			return val;
		else
			throw new RuntimeException("books.getallurl not specified in the Configuration.properties file.");
	}
	public static String postRegister()
	{
		String val = properties.getProperty("post.register");
		if (val != null)
			return val;
		else
			throw new RuntimeException("post.register not specified in the Configuration.properties file.");
	}

	public static String postOrder()
	{
		String val = properties.getProperty("post.order");
		if (val != null)
			return val;
		else
			throw new RuntimeException("post.register not specified in the Configuration.properties file.");
	}
	

	public static String getAllOrders()
	{
		String val = properties.getProperty("get.allorders");
		if (val != null)
			return val;
		else
			throw new RuntimeException("books.getallurl not specified in the Configuration.properties file.");
	}
	public static String getSingleBookUrl()
	{
		String val = properties.getProperty("get.singlebook");
		if (val != null)
			return val;
		else
			throw new RuntimeException("books.getallurl not specified in the Configuration.properties file.");
	}
	public static String getSingleOrder()
	{
		String val = properties.getProperty("get.singleorder");
		if (val != null)
			return val;
		else
			throw new RuntimeException("books.getallurl not specified in the Configuration.properties file.");
	}
	public static String updateOrder()
	{
		String val = properties.getProperty("update.order");
		if (val != null)
			return val;
		else
			throw new RuntimeException("books.getallurl not specified in the Configuration.properties file.");
	}
	public static String deleteOrder()
	{
		String val = properties.getProperty("delete.order");
		if (val != null)
			return val;
		else
			throw new RuntimeException("books.getallurl not specified in the Configuration.properties file.");
	}
}
