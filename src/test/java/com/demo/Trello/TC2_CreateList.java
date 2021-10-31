package com.demo.Trello;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.demo.baseutil.BaseTest;
import com.demo.dataproviders.DataProviderFactory;
import com.demo.dataproviders.DataProviderFileRowFilter;
import com.demo.util.WebActionUtil;
import com.demo.util.WebActionUtil_Test;

import io.restassured.response.Response;

/**
 * Description:Script to Verify that whether user is able to create a list.
 * 
 *
 */
public class TC2_CreateList extends BaseTest{
	

		@DataProviderFileRowFilter(file = "./src/main/resources/data/Data.xlsx", sql = "Select * from TrelloData where SlNo ='2'")
		@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify that whether user is able to create a list")
		public void createList(String SlNo,String methodName,String endPointUrl,String fileName,String header,String methodAction,String queryParam,String pathParam,String boardName , String listName , String cardName) throws InterruptedException
		{
			//System.out.print(listName);
			
			Response response = WebActionUtil_Test.createList(endPointUrl, fileName, header, methodAction, queryParam, pathParam, boardName, listName, cardName);
			 WebActionUtil_Test.logMessage("List Created Succesfully");
			 String id=response.path("id").toString();
			 WebActionUtil_Test.setValue("listId", id);
			 
//			/*Validating the Status Code*/
//			int statusCode = response.getStatusCode();
//			Assert.assertEquals(statusCode, 200);
//			
//			/*Logging to the Extent Report*/
//            WebActionUtil.pass("List Created Successfully");
//            
//            /*Logging to the Extent Report*/
//    		WebActionUtil.pass(response.getBody().asPrettyString());
//			
//			/*Logging to the Logger File*/
//			WebActionUtil.info(response.getBody().asPrettyString());
//			
//			WebActionUtil.logMessage("List Created Successfully");
//			
//			String [] actual= {Integer.toString(statusCode)};
//			String [] expected= {Integer.toString(200)};
//			WebActionUtil.createCustomizeTable(expected, actual);
			
		}

}