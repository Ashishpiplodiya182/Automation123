package com.demo.JobPortal;

import org.testng.annotations.Test;

import com.demo.baseutil.BaseTest;
import com.demo.dataproviders.DataProviderFactory;
import com.demo.dataproviders.DataProviderFileRowFilter;


import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class Testing extends BaseTest {
	
	@DataProviderFileRowFilter(file = "./src/main/resources/data/Data.xlsx", sql = "Select * from Data where SlNo ='1'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: SignUp by the User in Job Portal using POST Request")
	public void signUpTest()
	{
		String header="Content-Type,Accept";
//		 Response res = WebActionUtil.executeApi("create", "/users/authenticate","userData", header, "token");
//		
//		 res.then().statusCode(200);
//		System.out.println(res.getBody().asPrettyString());
	String queryParam="id,jobTitle,jobDescription";
		
//	String pathParam="id";
//		WebActionUtil.executeApi("create", "/users/sign-up","userData", header, "createUser","","");
//		
//		WebActionUtil.executeApi("create", "/users/authenticate","userData", header, "token","","");
//		
//		WebActionUtil.executeApi("create", "/auth/webapi/add","projectData", header, "createProject","","");
//		
//		WebActionUtil.executeApi("get", "/auth/webapi/all","", header, "getAll","","");
//		
//		WebActionUtil.executeApi("get", "/auth/webapi/find","", header, "find",queryParam,"");
//		
//		WebActionUtil.executeApi("update", "/auth/webapi/update","updateData", header, "put","","");
//		
//		WebActionUtil.executeApi("update", "/auth/webapi/update/details"," ", header, "patch",queryParam,"");
//		
//		WebActionUtil.executeApi("delete", "/auth/webapi/remove/{id}","", header, "delete","",pathParam);
		
	}

}
