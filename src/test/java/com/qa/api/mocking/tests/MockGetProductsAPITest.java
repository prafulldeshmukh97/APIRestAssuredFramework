package com.qa.api.mocking.tests;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.APIMock;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MockGetProductsAPITest extends BaseTest{
	@Test
	public void getDummyProductsWithJSONFileTest()
	{
		APIMock.getDummyProductsWIthJsonFile();;
		Response response=restClient.get(BASE_URL_LOCAL_HOST_PORT, "/api/products", null, null, AuthType.NO_AUTH, ContentType.ANY);
		response.then()
		.assertThat()
		.statusCode(200);
		
	}

}
