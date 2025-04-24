package com.qa.producs.api.test;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductsAPITest extends BaseTest {
	//No toekn required in this api
	@Test
	public void getProductsTest()
	{
		Response response=restClient.get(BASE_URL_PRODUCTS,"/users", null, null, AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test
	public void getProductsAPILimitTest()
	{
	
		Map<String,String> limitMap = new HashMap<String,String>();
		limitMap.put("limit","5");
		Response response=restClient.get(BASE_URL_PRODUCTS,"/users", limitMap, null, AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
	}
	

}
