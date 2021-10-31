package com.demo.Trello;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.demo.baseutil.BaseTest;
import com.demo.dataproviders.DataProviderFactory;
import com.demo.dataproviders.DataProviderFileRowFilter;
import com.demo.util.WebActionUtil;
import com.demo.util.WebActionUtil_Test;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * Description:Script to Verify that whether user is able to get a card
 * 
 *
 */
public class TC4_GetCard extends BaseTest {

	@DataProviderFileRowFilter(file = "./src/main/resources/data/Data.xlsx", sql = "Select * from TrelloData where SlNo ='4'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify that whether user is able to get a card")
	public void getCard(String SlNo,String methodName,String endPointUrl,String fileName,String header,String methodAction,String queryParam,String pathParam,String boardName , String listName , String cardName) throws InterruptedException
	{
		 
		 WebActionUtil_Test.getCard(endPointUrl, fileName, header, methodAction, queryParam, pathParam, boardName, listName, cardName);
		 WebActionUtil_Test.logMessage("Card Fetched Succesfully");

	  
	}
}
