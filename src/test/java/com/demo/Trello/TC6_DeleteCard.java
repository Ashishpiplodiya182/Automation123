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
 * Description:Script to Script to Verify that whether user is able to delete a card
 * 
 *
 */
public class TC6_DeleteCard extends BaseTest {

	@DataProviderFileRowFilter(file = "./src/main/resources/data/Data.xlsx", sql = "Select * from TrelloData where SlNo ='6'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Script to Verify that whether user is able to delete a card")
	public void deleteCard(String SlNo,String methodName,String endPointUrl,String fileName,String header,String methodAction,String queryParam,String pathParam,String boardName , String listName , String cardName) throws InterruptedException
	{

		
		 
		 WebActionUtil_Test.deleteCard(endPointUrl, fileName, header, methodAction, queryParam, pathParam, boardName, listName, cardName);
		 WebActionUtil_Test.logMessage("Card Deleted Succesfully");
		
//		/*Logging to the Logger File*/
//		WebActionUtil.info(response.getBody().asPrettyString());
//		
//	
//		/*Validating the Status Code*/
//		int statusCode = response.getStatusCode();
//		Assert.assertEquals(statusCode, 200);
//		
//		/*Logging to the Extent Report*/
//		WebActionUtil.pass("Card Deleted Successfully");
//		
//		/*Logging to the Extent Report*/
//		WebActionUtil.pass(response.getBody().asPrettyString());
//		
//		/*Logging to the Allure Report*/
//		WebActionUtil.logMessage("Card Deleted Successfully");
//		
//		String [] actual= {Integer.toString(statusCode)};
//		String [] expected= {Integer.toString(200)};
//		WebActionUtil.createCustomizeTable(expected, actual);
		
		
	}

}
