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

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Description: All the action utilities added in this class e.g
 * 
 * 
 * @author : Sajal, Vikas
 */

public class WebActionUtil extends BaseTest {

	public long ETO;


	public WebActionUtil(long ETO) {
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
	 * @author Ashish piplodiya
	 * @param message
	 */
	
	public static String logMessage(String message)
	{
		return message;
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
	
	/**
	* Description Method to set Token value
	*
	* @author Sajal, Vikas
	* @param id,path
	*/
	public synchronized static void setGenericId(String key , String id) {
	// Creating a JSONObject object
	
		JSONObject jsonObject = getId();//{"id":"123456"}
		
	
	   // Inserting key-value pairs into the json object
		jsonObject.put(key, id);//{"id":"123456","key":"12345"}
	
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
	* Description Method to get Token value
	*
	* @author Ashish
	* @param path
	*/
	public synchronized static JSONObject getId() {
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
	* Description Method to get Token value
	*
	* @author Sajal, Vikas
	* @param path
	*/
	public synchronized static String getGenericId(String key ) {
	// JSON parser object to parse read file
	JSONParser jsonParser = new JSONParser();
	
	try {
	FileReader reader = new FileReader(System.getProperty("user.dir") + prop.getProperty("iD"));
	// Read JSON file
	Object obj = jsonParser.parse(reader);
	JSONObject jsonObject = (JSONObject) obj;
	key = (String) jsonObject.get(key);
	if (key.equals("")) {
		key = null;
	
	}
	} catch (Exception e) {
	e.printStackTrace();
	}
	return key;

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
	
	public void addPreRequest(String headers , String query , String pathS)
	{
		if(!(headers==""))
		{
			
		}
		if(!(headers==""))
		{
			
		}
		if(!(headers==""))
		{
			
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
		
		
		/**
		 * 
		 * Description Method to Execute the API Request
		 * 
		 * @author Ashish
		 * @param methodName,endPointUrl,file,header,methodAction,queryParam,pathParam
		 */
			public synchronized static Response executeApi(String methodName,String endPointUrl,String file,String header,String methodAction,String queryParam,String pathParam, String boardName, String listName , String CardName)
			{
				
				if(methodName.equalsIgnoreCase("get"))
				{
					return get(endPointUrl, file, header, methodAction,queryParam,pathParam,boardName,listName,CardName);
					
				}
				if(methodName.equalsIgnoreCase("create"))
				{
					return post(endPointUrl, file, header, methodAction,queryParam,pathParam,boardName,listName,CardName);
					
				}
				if(methodName.equalsIgnoreCase("update"))
				{
					return update(endPointUrl, file, header, methodAction,queryParam,pathParam,boardName,listName,CardName);
					
				}
				if(methodName.equalsIgnoreCase("delete"))
				{
					return delete(endPointUrl, file, header, methodAction,queryParam,pathParam,boardName,listName,CardName);
					
				}
				
				return null;
				
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
			
			/**
			 * 
			 * Description Method to Post The Request
			 * 
			 * @author Manikandan A
			 * @param endPointUrl,file,header,methodAction,queryParam,pathParam
			 */
			private synchronized static Response post(String endPointUrl,String file,String header,String methodAction, String queryParam , String pathParam ,String boardName , String listName, String cardName )
			{
				Response response;
				try {

							if (methodAction.equalsIgnoreCase("createboard")) {
								
								try {
								addHeaders(header);
								addQueryParam(queryParam);
								
								System.out.print(boardName);
								
								requestSpecBuilder.addQueryParam("name",boardName);
								requestSpecification = requestSpecBuilder.build();
								
								 response = given().spec(requestSpecification).post(endPointUrl).then().log().all().extract().response();
								
								 String id=response.path("id").toString();
								 
								 setId(id,prop.getProperty("boardId"));
								
								 
								return response;
								
								
								}catch(Exception e)
								{
									error("Unable to Create board");
									
									fail("Unable to Create board");
									
									Assert.fail();
									
									
								}
								
							} else if(methodAction.equalsIgnoreCase("createlist")){
								try {
									
									
									addHeaders(header);
									//requestSpecBuilder.addQueryParam("idBoard", getId(prop.getProperty("boardId")));
									addQueryParam(queryParam);
									
									requestSpecBuilder.addQueryParam("name",listName);
									requestSpecification = requestSpecBuilder.build();
								
									
								
									 response = given().spec(requestSpecification).post(endPointUrl).then().log().all().extract().response();
									
								    String id=response.path("id").toString();
								   setId(id , prop.getProperty("listId"));

									
									
									 
									
									return response;
								
								}
								catch(Exception e)
								{
									
									error("Unable to Create list");
									
									fail("Unable to Create list");
									
									Assert.fail();
								}
								
							}else if(methodAction.equalsIgnoreCase("createcard")) {
								
									try {
										
									     
										addHeaders(header);
										//requestSpecBuilder.addQueryParam("idList", getId(prop.getProperty("listId")));
										requestSpecBuilder.addQueryParam("name",cardName);
										System.out.print("=============================================");
										System.out.print(cardName);
										addQueryParam(queryParam);
										
										
										requestSpecification = requestSpecBuilder.build();

										
										
										response=given().spec(requestSpecification).post(endPointUrl).then().log().all().extract().response();
										
										String id=response.path("id").toString();
										setId(id , prop.getProperty("cardId"));

										
										return response;
									} catch (Exception e) {
										
										error("Unable to Create Card");
										fail("Unable to Create Card");
										Assert.fail();
										
									}
							}
				} 
				catch (Exception e) {
					
					error("Unable to send the POST Request");
					
					fail("Unable to send the POST Request");
					
					Assert.fail();
					
				}
				return null;
		
					
				}
			

			/**
			 * 
			 * Description Method to send the Get Request
			 * 
			 * @author Manikandan A
			 * @param endPointUrl,file,header,methodAction,queryParam,pathParam
			 */
			private synchronized static Response get(String endPointUrl,String file,String header,String methodAction, String queryParam , String pathParam ,String boardName , String listName , String cardName)
			{
				Response response;
				try {
					if(methodAction.equalsIgnoreCase("getcard")) {

							
								try {
									
									
									addHeaders(header);
									
									addQueryParam(queryParam);
									
									requestSpecification = requestSpecBuilder.build();
															
									// response = given().spec(requestSpecification).get(endPointUrl+""+getId(prop.getProperty("cardId"))).then().log().all().extract().response();
									
									 
									//return response;
								} catch (Exception e) {

									error("Unable to fetch the Card");
									fail("Unable to fetch the Card");
									Assert.fail();
								}
					}
				}
					
				catch (Exception e) {
					
					error("Unable to Send the GET Request");
					
					fail("Unable to Send the GET Request");
					
					Assert.fail();
					
				}
				return null;
		
					
				}
			
			
			
			/**
			 * 
			 * Description Method to send the update.
			 * 
			 * @author Manikandan A
			 * @param endPointUrl,file,header,methodAction,queryParam,pathParam
			 */
			private synchronized static Response update(String endPointUrl,String file,String header,String methodAction, String queryParam , String pathParam ,String boardName, String listName , String cardName)
			{
				Response response;
				try {
							if (methodAction.equalsIgnoreCase("updatecard")) {
								
								try {
									
									
									addHeaders(header);
								
								    addQueryParam(queryParam);
								   
									requestSpecBuilder.addQueryParam("name",listName);
								
									
									requestSpecification = requestSpecBuilder.build();
									
									
									
									System.out.print("i am here inside update");
									// response = given().spec(requestSpecification).put(endPointUrl+""+getId(prop.getProperty("cardId"))).then().log().all().extract().response();
									

										
									//return response;
								} catch (Exception e) {
									
									error("Unable to update the Card using PUT Request");
									
									fail("Unable to update the Card using PUT Request");
									
									Assert.fail();
								}
								
							} else if(methodAction.equalsIgnoreCase("patch")){
								
								try {
									requestSpecBuilder.addHeader("Authorization","Bearer "+WebActionUtil.getTokenValue());
									
										addHeaders(header);
										addPathParam(pathParam);
										addQueryParam(queryParam);
										
										requestSpecification = requestSpecBuilder.build();
									
										 response = given().spec(requestSpecification).patch(endPointUrl).then().log().all().extract().response();
										
									
										
										
										return response;
								} catch (Exception e) {
									
									error("Unable to update the Card using PATCH Request");
									
									fail("Unable to update the Card using PATCH Request");
									
									Assert.fail();
								}
							}
				} 
				catch (Exception e) {
					error("Unable to Send the Update Request");
					
					fail("Unable to Send the Update Request");
					
					Assert.fail();
					
				}
				return null;
		
					
				}
			
			/**
			 * 
			 * Description Method to send the Delete Request
			 * 
			 * @author Manikandan A
			 * @param endPointUrl,file,header,methodAction,queryParam,pathParam
			 */
			private synchronized static Response delete(String endPointUrl,String file,String header,String methodAction, String queryParam , String pathParam ,String boardName , String cardName , String listName)
			{
				Response response;
				try {
								try {
									
									
									addHeaders(header);
									
									addQueryParam(queryParam);
									
									
									
									requestSpecification = requestSpecBuilder.build();
															
									// response = given().spec(requestSpecification).when().delete(endPointUrl+""+getId(prop.getProperty("cardId"))).then().log().all().extract().response();
									

									//return response;
								} catch (Exception e) {
									
										error("Unable to Delete the Card");
									
										fail("Unable to Delete the Card");
									
										Assert.fail();
								}
								
				} 
				catch (Exception e) {
					
					error("Unable to Send the DELETE Request");
					
					fail("Unable to Send the DELETE Request");
					
					Assert.fail();
					
				}
				return null;
		
					
				}
			

}
