package com.demo.JobPortal;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.demo.baseutil.BaseTest;
import com.demo.dataproviders.DataProviderFactory;
import com.demo.dataproviders.DataProviderFileRowFilter;
import com.demo.util.WebActionUtil;

import io.restassured.response.Response;

/**
 * Description:Script to Update the Project using PUT Request
 * 
 *
 */
public class TC6_UpdateProject extends BaseTest {

	@DataProviderFileRowFilter(file = "./src/main/resources/data/Data.xlsx", sql = "Select * from Data where SlNo ='6'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Updating the Project in Job Portal using PUT Request")
	public void updateProjectbyPut(String SlNo,String methodName,String endPointUrl,String fileName,String header,String methodAction,String queryParam,String pathParam,String authorization)
	{
//		/*Sending the PUT Request*/
//		Response response = WebActionUtil.executeApi(methodName,endPointUrl,fileName,header,methodAction,queryParam,pathParam);
//		
//		
//		/*Logging to the Logger File*/
//		WebActionUtil.info(response.getBody().asPrettyString());
//		
//		/*Validating the Status Code*/
//		int statusCode = response.getStatusCode();
//		Assert.assertEquals(statusCode, 200);
//		
//		WebActionUtil.pass("Project Updated successfully");
//		
//		String [] actual= {Integer.toString(statusCode)};
//		String [] expected= {Integer.toString(200)};
//		WebActionUtil.createCustomizeTable(expected, actual);
		
	}

}
