package com.demo.util;



import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.Reporter;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.demo.baseutil.BaseTest;
import com.demo.reports.ExtentManager;
import com.demo.table.Helper;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

/**
 * Description: All the action utilities added in this class e.g
 * 
 * 
 * @author : Sajal, Vikas
 */

public class WebActionUtil_Test extends BaseTest {

	public long ETO;


	public WebActionUtil_Test(long ETO) {
		this.ETO = ETO;

	}

	/**
	 * 
	 * Description Method for the info updation in extent Report Report
	 * @param message
	 * @author Sajal, Vikas
	 */

	public static void pass(String message) {
		ExtentManager.getTestReport().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
	}

	/**
	 * Description Method to provide info in the log,extent reports,testNg reports
	 * 
	 * @author Sajal, Vikas
	 * @param message
	 */
	public static void info(String message) {
		BaseTest.logger.info(message);

	}

	/**
	 * Description Method to provide info in the extent report
	 * 
	 * @author Sajal, Vikas
	 * @param message
	 */
	public static void extentinfo(String message) {
		ExtentManager.getTestReport().info(message);

	}

	/**
	 * 
	 * Description Method for the Warning updation in extent Report,Log file,TestNG
	 * Report
	 * 
	 * @author Sajal, Vikas
	 * @param message
	 */

	public static void warn(String message) {

		BaseTest.logger.warn(message);
		Reporter.log(message);
	}

	/**
	 * 
	 * Description Method for the error/errorure updation in extent Report
	 * 
	 * @author Sajal, Vikas
	 * @param message
	 */

	public static void fail(String message) {
		Reporter.log(message);
		ExtentManager.getTestReport().fail(MarkupHelper.createLabel(message, ExtentColor.RED));

	}

	/**
	 * 
	 * Description Method for the error updation in extent Report
	 * 
	 * @author Sajal, Vikas
	 * @param message
	 */
	public static void error(String message) {

		BaseTest.logger.error(message);
		Reporter.log(message);

	}

	/**
	 * 
	 * Description Method for the error/errorure updation in extent Report
	 * 
	 * @author Sajal, Vikas
	 * @param message,color
	 */

	public static void validationinfo(String message, String color) {
		if (color.equalsIgnoreCase("blue"))

			ExtentManager.getTestReport().info(MarkupHelper.createLabel(message, ExtentColor.BLUE));
		else if (color.equalsIgnoreCase("green"))
			ExtentManager.getTestReport().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
		else if (color.equalsIgnoreCase("red"))
			ExtentManager.getTestReport().fail(MarkupHelper.createLabel(message, ExtentColor.RED));
	}

	
	
	
	
	
	/**
	 * 
	 * Description Method for fetching of get Current Date Time
	 * 
	 * @author Sajal, Vikas
	 * 
	 */

	public static String getCurrentDateTime() {
		DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate = new Date();
		return customFormat.format(currentDate);
	}

	
	/**
	 * 
	 * Description Method to delete the directory
	 * 
	 * @author Sajal, Vikas
	 * @param pathToDeleteFolder
	 */
	public static void deleteDir(String pathToDeleteFolder) {
		File extefolder = new File(pathToDeleteFolder);
		if ((extefolder.exists())) {
			deleteFolderDir(extefolder);
		}
	}

	/**
	 * 
	 * Description Method to delete folder directory
	 * 
	 * @author Sajal, Vikas
	 * @param folderToDelete
	 */
	public static void deleteFolderDir(File folderToDelete) {
		File[] folderContents = folderToDelete.listFiles();
		if (folderContents != null) {
			for (File folderfile : folderContents) {
				if (!Files.isSymbolicLink(folderfile.toPath())) {
					deleteFolderDir(folderfile);
				}
			}

		}
		folderToDelete.delete();
	}

	/**
	 * Description To create the duration of the Test Run
	 * 
	 * @author Sajal, Vikas
	 * @param millis
	 */
	public static String formatDuration(final long millis) {
		long seconds = (millis / 1000) % 60;
		long minutes = (millis / (1000 * 60)) % 60;
		long hours = millis / (1000 * 60 * 60);

		StringBuilder b = new StringBuilder();
		b.append(hours == 0 ? "00" : hours < 10 ? String.valueOf("0" + hours) : String.valueOf(hours));
		b.append(":");
		b.append(minutes == 0 ? "00" : minutes < 10 ? String.valueOf("0" + minutes) : String.valueOf(minutes));
		b.append(":");
		b.append(seconds == 0 ? "00" : seconds < 10 ? String.valueOf("0" + seconds) : String.valueOf(seconds));
		return b.toString();
	}

	/**
	 * Description Method to delete excel file from downloads
	 * 
	 * @author Sajal, Vikas
	 * @param downloadspath
	 */
	public synchronized void deleteXlFilesFromDownloads(String downloadspath) {
		// Delete all files from this directory
		String targetDirectory = downloadspath;
		File dir = new File(targetDirectory);

		// Filter out all log files
		String[] xlFiles = dir.list((d, s) -> {
			boolean fileName = s.startsWith("Claim") && s.endsWith(".xlsx") ? true : false;
			return fileName;
		});
		if (xlFiles.length >= 50) {
			for (String xlFile : xlFiles) {
				String tempXLFile = new StringBuffer(targetDirectory).append(File.separator).append(xlFile).toString();
				File fileDelete = new File(tempXLFile);
				boolean isdeleted = fileDelete.delete();
				
			}
		}
	}
	
	
	
	/**
	* Description Method to get JSON Object
	*
	* @author Ashish
	* @param path
	*/
	public synchronized static JSONObject getJsonObject() {
	// JSON parser object to parse read file
	JSONParser jsonParser = new JSONParser();
	JSONObject id = null;
	try {
	FileReader reader = new FileReader(System.getProperty("user.dir") + prop.getProperty("iD"));
	// Read JSON file
	Object obj = jsonParser.parse(reader);
	JSONObject jsonObject = (JSONObject) obj;
	id =  jsonObject;
	if (id.equals("")) {
	id = null;
	
	}
	} catch (Exception e) {
	e.printStackTrace();
	}
	return id;

	}
	
	
	
	

	
	/**
	* Description Method to get the value from JSON file.
	*
	* @author Ashish Piplodiya
	* @param key
	*/
	public synchronized static String getValue(String key ) {
	
	JSONParser jsonParser = new JSONParser();
	String value= null;
	try {
	FileReader reader = new FileReader(System.getProperty("user.dir") + prop.getProperty("iD"));
	
	Object obj = jsonParser.parse(reader);
	JSONObject jsonObject = (JSONObject) obj;
	value = (String) jsonObject.get(key);
	if (key.equals("")) {
		key = null;
	
	}
	info("Value fetched from JSON file: "+key+"    "+value);
	}
	catch (Exception e) {
	e.printStackTrace();
	info("Unable to get the value from JSON File");
	}
	return value;

	}
	
	/**
	* Description Method to Update the value into JSON file
	*
	* @author Ashish Piplodiya
	* @param id,path
	*/
	public synchronized static void setValue(String key , String id) {
	// Creating a JSONObject object
	
		JSONObject jsonObject = getJsonObject();
		
	
	   // Inserting key-value pairs into the json object
		jsonObject.put(key, id);
	
	try {
	FileWriter file = new FileWriter(System.getProperty("user.dir") + prop.getProperty("iD"));
	file.write(jsonObject.toJSONString().toString());
	file.close();
		
	info(id + " written in .json file");
	} catch (Exception e) {
	e.printStackTrace();
	error("Unable to write " + id + " in json file");
	      }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	* Description Method to set Token value
	*
	* @author Sajal, Vikas
	* @param tokenValue
	*/
	public synchronized static void setTokenValue(String tokenValue) {
	// Creating a JSONObject object
	JSONObject jsonObject = new JSONObject();
	// Inserting key-value pairs into the json object
	jsonObject.put("token", tokenValue);
	try {
	FileWriter file = new FileWriter(System.getProperty("user.dir") + "./data/token.json");
	file.write(jsonObject.toJSONString());
	file.close();
	info(tokenValue + " written in token.json file");
	} catch (Exception e) {
	e.printStackTrace();
	error("Unable to write " + tokenValue + " in token.json file");
	}
	}
	
	/**
	* Description Method to set Token value
	*
	* @author Sajal, Vikas
	* @param id,path
	*/
	public synchronized static void setId(String id , String path) {
	// Creating a JSONObject object
	JSONObject jsonObject = new JSONObject();
	
	// Inserting key-value pairs into the json object
	jsonObject.put("id", id);
	try {
	FileWriter file = new FileWriter(System.getProperty("user.dir") + path);
	file.write(jsonObject.toJSONString());
	file.close();
		
	info(id + " written in .json file");
	} catch (Exception e) {
	e.printStackTrace();
	error("Unable to write " + id + " in json file");
	}
	}
	
	
	
//	public void getValues()
//	{
//		String contents =
//		JSONObject jsonFile = new JSONObject();
//	    JSONObject variableList = jsonFile.getJSONObject("Variables"); // <-- use getJSONObject
//	    JSONArray keys = variableList.names ();
//	    for (int i = 0; i < keys.length (); ++i) {
//	        String key = keys.getString(i);
//	        String value = variableList.getString(key);
//	        System.out.println("key: " + key + " value: " + value);
//	    }
//	}
	
	
	
	/**
	* Description Method to get Token value
	*
	* @author Sajal, Vikas
	* @param path
	*/
	public synchronized static String getId(String path) {
	// JSON parser object to parse read file
	JSONParser jsonParser = new JSONParser();
	String id = null;
	try {
	FileReader reader = new FileReader(System.getProperty("user.dir") + path);
	// Read JSON file
	Object obj = jsonParser.parse(reader);
	JSONObject jsonObject = (JSONObject) obj;
	id = (String) jsonObject.get("id");
	if (id.equals("")) {
	id = null;
	info("Token value not available in token.json file");
	}
	} catch (Exception e) {
	e.printStackTrace();
	}
	return id;

	}

	/**
	* Description Method to get Token value
	*
	* @author Sajal, Vikas
	*/
	public synchronized static String getTokenValue() {
	// JSON parser object to parse read file
	JSONParser jsonParser = new JSONParser();
	String token = null;
	try {
	FileReader reader = new FileReader(System.getProperty("user.dir") + "./data/token.json");
	// Read JSON file
	Object obj = jsonParser.parse(reader);
	JSONObject jsonObject = (JSONObject) obj;
	token = (String) jsonObject.get("token");
	if (token.equals("")) {
	token = null;
	info("Token value not available in token.json file");
	}
	} catch (Exception e) {
	e.printStackTrace();
	}
	return token;

	}
	
	public void addPreRequest(String headers , String queryParam , String pathParam)
	{
		if(!(headers==""))
		{
			try
			{
				addHeaders(headers);
				
			} catch (Exception e) 
			{
				
			} 
		}
		if(!(queryParam==""))
		{
			
			try
			{
				addQueryParam(queryParam);
				
			} catch (Exception e) 
			{
				
			} 
		}
		if(!(pathParam==""))
		{
			try
			{
		
			addPathParam(pathParam);
			
		} catch (Exception e) 
		{
			
		} 
		}
		
		
	}
	
	
	
	
	
	
	/**
	 * 
	 * Description Method to Create customizable table.
	 * 
	 * @author Ashish
	 * @param actual,expected
	 */
		public static void createCustomizeTable(String[] actual , String[] expected)
		{
				ExtentManager.getTestReport().info(Helper.createTable(actual,expected ));
		}
		
		
//		/**
//		 * 
//		 * Description Method to Execute the API Request
//		 * 
//		 * @author Ashish
//		 * @param methodName,endPointUrl,file,header,methodAction,queryParam,pathParam
//		 */
//			public synchronized static Response executeApi(String methodName,String endPointUrl,String file,String header,String methodAction,String queryParam,String pathParam, String boardName, String listName , String CardName)
//			{
//				
//				if(methodName.equalsIgnoreCase("get"))
//				{
//					return get(endPointUrl, file, header, methodAction,queryParam,pathParam,boardName,listName,CardName);
//					
//				}
//				if(methodName.equalsIgnoreCase("create"))
//				{
//					//return post(endPointUrl, file, header, methodAction,queryParam,pathParam,boardName,listName,CardName);
//					
//				}
//				if(methodName.equalsIgnoreCase("update"))
//				{
//					return update(endPointUrl, file, header, methodAction,queryParam,pathParam,boardName,listName,CardName);
//					
//				}
//				if(methodName.equalsIgnoreCase("delete"))
//				{
//					return delete(endPointUrl, file, header, methodAction,queryParam,pathParam,boardName,listName,CardName);
//					
//				}
//				
//				return null;
//				
//			}
//	
		
		
		
		public void validatetext(String expected)
		{try
		{
			String actualvalue = "";
			logMessage(expected+" matched successfully with"+actualvalue);
		}
			catch (Exception e) {
				// TODO: handle exception
			}
			
			
			
		}
		
		
		/**
		 * 
		 * Description Method to Add Headers
		 * 
		 * @author Ashish
		 *
		 */
		  @Step("{0}")
		  public synchronized static String logMessage(String message)
		  {
			  
			return message;
			  
		  }
		
		
			/**
			 * 
			 * Description Method to Add Headers
			 * 
			 * @author Ashish
			 * @param header
			 */
				
			public static void addHeaders(String header) throws FileNotFoundException, IOException, ParseException
			{
				try {
					
					JSONParser jsonParser = new JSONParser();
					JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(prop.getProperty("header")));
				    String[] a = header.split(",");
				    
			    for (int i = 0; i < a.length; i++)
				{
			    	
					requestSpecBuilder.addHeader(a[i], (String) jsonObject.get(a[i]));
					
				}
                  info("Header added to the request");
				
				} catch (Exception e) {
							error("Unable to add Headers");
					
							fail("Unable to add Headers");
					
							Assert.fail();
				}
	
			}
			
			/**
			 * 
			 * Description Method to Add Query Parameter
			 * 
			 * @author Ashish
			 * @param queryParam
			 */public static void addQueryParam(String queryParam) throws FileNotFoundException, IOException, ParseException
			{
				try {
					
					JSONParser jsonParser = new JSONParser();
					JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(prop.getProperty("queryParam")));
				    String[] a = queryParam.split(",");
				    
				  for (int i = 0; i < a.length; i++)
				{
			    	if(a[i].equals("id"))
			    	{
			    	
			    		requestSpecBuilder.addQueryParam(a[i],  jsonObject.get(a[i]));
			    		
			    	}
			    	
			    	else {
			    	   requestSpecBuilder.addQueryParam(a[i], (String) jsonObject.get(a[i]));
			    	   
			    	  }
				}

				 info("Query Parameter added to the Request");
				
				} catch (Exception e) {
							error("Unable to add Query Parameter");
					
							fail("Unable to add Query Parameter");
					
							Assert.fail();
				}
	
			}
			
			 /**
				 * 
				 * Description Method to Add Path Parameter
				 * 
				 * @author Ashish
				 * @param pathParam
				 */
			public static void addPathParam(String pathParam) throws FileNotFoundException, IOException, ParseException
			{
				try {
					
					JSONParser jsonParser = new JSONParser();
					JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(prop.getProperty("queryParam")));
				    String[] a = pathParam.split(",");
				    
				    
			    for (int i = 0; i < a.length; i++)
				{
			    	if(a[i].equals("id"))
			    	{
			    	
			    		requestSpecBuilder.addPathParam(a[i],  jsonObject.get(a[i]));
			    	}
			    	
			    	else {
			    	requestSpecBuilder.addPathParam(a[i], (String) jsonObject.get(a[i]));
			    	}
				}
			    info("Path Parameter added to the Request");
				
				} catch (Exception e) {
					
					
					error("Unable to add Path Parameter");
					
					fail("Unable to add Path Parameter");
					
					Assert.fail();
				}
	
			}
			
			
			public static synchronized Response buildPostRequest(String endPointUrl , RequestSpecBuilder requestSpecBuilder)
			{
				try {
				Response response;
				requestSpecification = requestSpecBuilder.build();
				
				response = given().spec(requestSpecification).post(endPointUrl).then().log().all().extract().response();
				 
				 
				 return response;
				}
				
				catch (Exception e) {
					
				}
				return null;
			}
			
			
			
			public static synchronized Response buildGetRequest(String endPointUrl , RequestSpecBuilder requestSpecBuilder)
			{
				try {
					Response response;
					requestSpecification = requestSpecBuilder.build();
					
					 response = given().spec(requestSpecification).get(endPointUrl+""+getValue("cardId")).then().log().all().extract().response();
					 
					 return response;
					}
					
					catch (Exception e) {
						
					}
					return null;
			}
			
			
			
			public static synchronized Response buildUpdateRequest(String endPointUrl , RequestSpecBuilder requestSpecBuilder)
			{
				try {
					Response response;
					requestSpecification = requestSpecBuilder.build();
					
					 response = given().spec(requestSpecification).put(endPointUrl+""+getValue("cardId")).then().log().all().extract().response();
					 
					 return response;
					}
					
					catch (Exception e) {
						
					}
					return null;
			}
			
			
			
			public static synchronized Response buildDeleteRequest(String endPointUrl , RequestSpecBuilder requestSpecBuilder )
			{
				try {
					Response response;
					requestSpecification = requestSpecBuilder.build();
					
					
					 response = given().spec(requestSpecification).delete(endPointUrl+""+getValue("boardId")).then().log().all().extract().response();
					
					
					 
					return response;
					}
					
					catch (Exception e) {
						
					}
					return null;
			}
			
			
			
			
			
			
			
			
			
			public static synchronized Response createBoard(String endPointUrl, String file, String header,
					String methodAction, String queryParam, String pathParam, String boardName, String listName,
					String cardName)
			{
				
				  requestSpecBuilder = apiRequest(endPointUrl, file, header, methodAction, queryParam, pathParam, boardName, listName, cardName);
				
				  requestSpecBuilder.addQueryParam("name",boardName);
				  
				  Response response = buildPostRequest(endPointUrl, requestSpecBuilder);
				  
//				  String id=response.path("id").toString();
//				  
//				  setValue("boardId",id);
					return response;
			}
			
			public static synchronized void deleteBoard(String endPointUrl, String file, String header,
					String methodAction, String queryParam, String pathParam, String boardName, String listName,
					String cardName)
			{
				
				  requestSpecBuilder = apiRequest(endPointUrl, file, header, methodAction, queryParam, pathParam, boardName, listName, cardName);
				
				 // requestSpecBuilder.addPathParam("idBoard", getValue("boardId"));
				  
				  Response response = buildDeleteRequest(endPointUrl, requestSpecBuilder);
				  
				  
					
			}
			
			
			
			
			
			
			
			
			
			
			public static synchronized Response createList(String endPointUrl, String file, String header,
					String methodAction, String queryParam, String pathParam, String boardName, String listName,
					String cardName)
			{
				
				  requestSpecBuilder = apiRequest(endPointUrl, file, header, methodAction, queryParam, pathParam, boardName, listName, cardName);
				
				  requestSpecBuilder.addQueryParam("idBoard", getValue("boardId"));
				  requestSpecBuilder.addQueryParam("name",listName);
				  
				 Response response = buildPostRequest(endPointUrl, requestSpecBuilder);
				 
//				 String id=response.path("id").toString();
//				 setValue("listId",id);
				    
				 return response;
			}
			
			
			public static synchronized void createCard(String endPointUrl, String file, String header,
					String methodAction, String queryParam, String pathParam, String boardName, String listName,
					String cardName)
			{
				
				  requestSpecBuilder = apiRequest(endPointUrl, file, header, methodAction, queryParam, pathParam, boardName, listName, cardName);
				  
				  requestSpecBuilder.addQueryParam("idList", getValue("listId"));
				  
				  requestSpecBuilder.addQueryParam("name", cardName);
				  
				 Response response = buildPostRequest(endPointUrl, requestSpecBuilder);
				  
				    String id=response.path("id").toString();
				    setValue("cardId",id);
			}
			
			
			public static synchronized void updateCard(String endPointUrl, String file, String header,
					String methodAction, String queryParam, String pathParam, String boardName, String listName,
					String cardName)
			{
				
				  requestSpecBuilder = apiRequest(endPointUrl, file, header, methodAction, queryParam, pathParam, boardName, listName, cardName);
				
				  requestSpecBuilder.addQueryParam("name", listName);
				  
				  buildUpdateRequest(endPointUrl, requestSpecBuilder);
			}
			
			
			
			public static synchronized void deleteCard(String endPointUrl, String file, String header,
					String methodAction, String queryParam, String pathParam, String boardName, String listName,
					String cardName)
			{
				  requestSpecBuilder.addQueryParam("idBoard", getValue("boardId"));
				
				  requestSpecBuilder = apiRequest(endPointUrl, file, header, methodAction, queryParam, pathParam, boardName, listName, cardName);
				
				 
				  
				Response response = buildDeleteRequest(endPointUrl, requestSpecBuilder);
				 
				
				 
				
				
			}
			
			
			
			public static synchronized void getCard(String endPointUrl, String file, String header,
					String methodAction, String queryParam, String pathParam, String boardName, String listName,
					String cardName)
			{
				
				  requestSpecBuilder = apiRequest(endPointUrl, file, header, methodAction, queryParam, pathParam, boardName, listName, cardName);
				
			 
				  
				  Response response = buildGetRequest(endPointUrl, requestSpecBuilder);
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			/**
			 * 
			 * Description Method to Post The Request
			 * 
			 * @author Manikandan A
			 * 
			 * @param endPointUrl,file,header,methodAction,queryParam,pathParam
			 */
			private synchronized static RequestSpecBuilder apiRequest(String endPointUrl, String file, String header,
					String methodAction, String queryParam, String pathParam, String boardName, String listName,
					String cardName) {
				Response response;
				try {
					
					addHeaders(header);
			
					addQueryParam(queryParam);

					return requestSpecBuilder;

				}

				catch (Exception e) {

					error("Unable to send the Api Request");

					fail("Unable to send the Api Request");

					Assert.fail();

				}
				return null;

			}
			

			
}
