package com.demo.JobPortal;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.demo.baseutil.BaseTest;
import com.demo.dataproviders.DataProviderFactory;
import com.demo.dataproviders.DataProviderFileRowFilter;
import com.demo.util.WebActionUtil;

import io.restassured.response.Response;

/**
 * Description:Script to create Project in Job Portal
 * 
 *
 */
public class TC3_CreateProject extends BaseTest{

	@DataProviderFileRowFilter(file = "./src/main/resources/data/Data.xlsx", sql = "Select * from Data where SlNo ='3'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Creating the Project in Job Portalusing POST Request")
	public void createProjectTest(String SlNo,String methodName,String endPointUrl,String fileName,String header,String methodAction,String queryParam,String pathParam,String authorization) throws InterruptedException
	{
//		/*Sending the POST Request*/
//		Response response = WebActionUtil.executeApi(methodName,endPointUrl,fileName,header,methodAction,queryParam,pathParam);
//		
//		WebActionUtil.pass("Project created successfully");
//		
//		/*Logging to the Logger File*/
//		WebActionUtil.info(response.getBody().asPrettyString());
//		
//		/*Validating the Status Code*/
//		int statusCode = response.getStatusCode();
//		Assert.assertEquals(statusCode, 201);
//		
//		String [] actual= {Integer.toString(statusCode)};
//		String [] expected= {Integer.toString(201)};
//		WebActionUtil.createCustomizeTable(expected, actual);
//		
//		Thread.sleep(5000);
	}

}
