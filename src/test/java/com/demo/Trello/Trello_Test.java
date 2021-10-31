package com.demo.Trello;

import org.testng.annotations.Test;

import com.demo.baseutil.BaseTest;
import com.demo.util.WebActionUtil;

import io.restassured.response.Response;

public class Trello_Test extends BaseTest
{
	@Test
	public void trelloTest()
	{
	
		String queryparam = "key,token";
		
		String header ="Content-Type,Accept";
		
//		WebActionUtil.executeApi("create", "/1/boards/", "", header, "createboard", queryparam, "");
//		
//		WebActionUtil.executeApi("create", "/1/lists", "", header, "createlist", queryparam, "");
//		
//		WebActionUtil.executeApi("create", "/1/cards", "", header, "createcard", queryparam, "");
//		
//		WebActionUtil.executeApi("get", "/1/cards/", "", header, "getcard", queryparam, "");
//		
//		WebActionUtil.executeApi("update", "/1/cards/", "", header, "updatecard", queryparam, "");
//		
		// Response res = WebActionUtil.executeApi("delete", "/1/cards/", "", header, "deletecard", queryparam, "");
		
		 
		// WebActionUtil.pass(res.getBody().asPrettyString());

		
	}

}
