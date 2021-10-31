package com.demo.JobPortal;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.demo.baseutil.BaseTest;
import com.demo.dataproviders.DataProviderFactory;
import com.demo.dataproviders.DataProviderFileRowFilter;
import com.demo.util.WebActionUtil;

import io.restassured.response.Response;

/**
 * Description:Script to Generate Token from Job Portal 
 * 
 *
 */
public class TC2_GetToken extends BaseTest{
	

		@DataProviderFileRowFilter(file = "./src/main/resources/data/Data.xlsx", sql = "Select * from Data where SlNo ='2'")
		@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Generating the Token from Job Portal using POst Request")
		public void generateToken(String SlNo,String methodName,String endPointUrl,String fileName,String header,String methodAction,String queryParam,String pathParam,String authorization) throws InterruptedException
		{
//			/*Sending the POST Request*/
//			Response response = WebActionUtil.executeApi(methodName,endPointUrl,fileName,header,methodAction,queryParam,pathParam );
//			
//			WebActionUtil.pass("Token Generated Successfully");
//			
//			/*Logging to the Logger File*/
//			WebActionUtil.info(response.getBody().asPrettyString());
//			
//			/*Validating the Status Code*/
//			int statusCode = response.getStatusCode();
//			Assert.assertEquals(statusCode, 200);
//			
//			String [] actual= {Integer.toString(statusCode)};
//			String [] expected= {Integer.toString(200)};
//			WebActionUtil.createCustomizeTable(expected, actual);
//			
//			Thread.sleep(5000);
		}

}