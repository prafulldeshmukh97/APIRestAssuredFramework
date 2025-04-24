package com.qa.api.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserTest extends BaseTest {
	
	@Test
	public void getUserTest()
	{
		Map<String, String> queryParamMap= new HashMap<String, String>();
		queryParamMap.put("name","naveen");
		queryParamMap.put("status","active");
		
		Response response=restClient.get(BASE_URL_GOREST,"/public/v2/users/", queryParamMap, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
	}
	@Test(enabled = false)
	public void getSingleUserTest()
	{
		Response response=restClient.get(BASE_URL_GOREST,"/public/v2/users/7831975", null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	

	

}
