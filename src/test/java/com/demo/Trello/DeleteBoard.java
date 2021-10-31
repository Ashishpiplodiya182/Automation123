package com.demo.Trello;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.demo.baseutil.BaseTest;
import com.demo.dataproviders.DataProviderFactory;
import com.demo.dataproviders.DataProviderFileRowFilter;
import com.demo.util.WebActionUtil_Test;

import io.restassured.response.Response;

public class DeleteBoard extends BaseTest
{
	/**
	 * Description:Script to Verify that whether user is able to create a board.
	 * 
	 *
	 */
	

		@DataProviderFileRowFilter(file = "./src/main/resources/data/Data.xlsx", sql = "Select * from TrelloData where SlNo ='2'")
		
		@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify that whether user is able to create a board")
		public void createBoard(String SlNo, String methodName, String endPointUrl, String fileName, String header,
				String methodAction, String queryParam, String pathParam ,String boardName , String listName , String cardName) throws FileNotFoundException, IOException, ParseException, InterruptedException {

			

			 WebActionUtil_Test.deleteBoard("/boards/", fileName, header, methodAction, queryParam, pathParam, boardName, listName, cardName);
				
//			Response response = WebActionUtil_Test.createList(endPointUrl, fileName, header, methodAction, queryParam, pathParam, boardName, listName, cardName);
//			
//		   String id=response.path("id").toString();
//			 WebActionUtil_Test.setValue("listId2",id);
		
		}
			
			
}
