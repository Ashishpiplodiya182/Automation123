package com.demo.Trello;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.demo.baseutil.BaseTest;
import com.demo.dataproviders.DataProviderFactory;
import com.demo.dataproviders.DataProviderFileRowFilter;
import com.demo.util.WebActionUtil;
import com.demo.util.WebActionUtil_Test;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * Description:Script to Verify that whether user is able to create a board.
 * 
 *
 */
public class TC1_CreateBoard extends BaseTest  {

	@DataProviderFileRowFilter(file = "./src/main/resources/data/Data.xlsx", sql = "Select * from TrelloData where SlNo ='1'")
	
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify that whether user is able to create a board")
	public void createBoard(String SlNo, String methodName, String endPointUrl, String fileName, String header,
			String methodAction, String queryParam, String pathParam ,String boardName , String listName , String cardName) throws FileNotFoundException, IOException, ParseException, InterruptedException {

		
		

		/*Sending the POST Request*/
		//Response response = WebActionUtil.executeApi(methodName,endPointUrl,fileName,header,methodAction,queryParam,pathParam,boardName,listName,cardName );
		
	// WebActionUtil_Test(SlNo, methodName, endPointUrl, fileName, header, methodAction, queryParam, pathParam, boardName, listName, cardName);
		
	Response response = WebActionUtil_Test.createBoard(endPointUrl, fileName, header, methodAction, queryParam, pathParam, boardName, listName, cardName);
	
	   String id=response.path("id").toString();
//	  
	  WebActionUtil_Test.setValue("boardId",id);
//     WebActionUtil_Test.logMessage("Board Created Succesfully");	
//    
//   
//	 
	
//	 
		
//	    given().baseUri(prop.getProperty("baseUri"))
//	    
//	    
//	    .contentType(ContentType.JSON)
//	    .accept(ContentType.JSON)
//	    
//	    .when()
//	    .delete(endPointUrl)
//	    .then()
//	    .statusCode(200).log().all();





//	 
//	 
	 //		/*Validating the Status Code*/
//		int statusCode = response.getStatusCode();
//		Assert.assertEquals(statusCode, 200);
//		
//		/*Logging to the Extent Report*/
//		WebActionUtil.pass("Board Created Successfully");
//		
//		/*Logging to the Logger File*/
//		WebActionUtil.info(response.getBody().asPrettyString());
//		
//		/*Logging to the Extent Report*/
//		WebActionUtil.pass(response.getBody().asPrettyString());
//		
//		/*Logging to the Report*/
//		WebActionUtil.logMessage("Board Created Successfully");
//		
//		String [] actual= {Integer.toString(statusCode)};
//		String [] expected= {Integer.toString(200)};
//		WebActionUtil.createCustomizeTable(expected, actual);
//		 
//		
		

	}

}
