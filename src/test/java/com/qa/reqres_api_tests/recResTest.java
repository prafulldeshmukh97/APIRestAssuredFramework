package com.qa.reqres_api_tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class recResTest extends BaseTest {
	
	@Test
	public void getUserTest()
	{
		Response response =restClient.get(BASE_URL_REQ_RES,"/api/users?page=2", null, null, AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
