package com.qa.api.mocking.tests;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.APIMock;
import com.qa.api.mocking.WiremockSetup;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static  org.hamcrest.Matchers.equalTo;

import java.util.Map;

public class MockGetUserAPITest extends BaseTest{
	
	@Test
	public void getDummyUserTest()
	{
		APIMock.getDummyUser();
		Response response=restClient.get(BASE_URL_LOCAL_HOST_PORT, "/api/users", null, null, AuthType.NO_AUTH, ContentType.ANY);
		response.then()
		.assertThat()
		.statusCode(200)
		.body("name", equalTo("prafull"));
	}
	@Test
	public void getDummyUserWithQueryParamTest()
	{
		APIMock.getDummyUserWIthQueryParams();
		Map<String,String>queryMap=Map.of("name","John Doe");
		Response response=restClient.get(BASE_URL_LOCAL_HOST_PORT, "/api/users", queryMap, null, AuthType.NO_AUTH, ContentType.ANY);
		response.then()
		.assertThat()
		.statusCode(200)
		.body("name", equalTo("John Doe"));
	}
	
	

	@Test
	public void getDummyUserWithJSONFileTest()
	{
		APIMock.getDummyUserWIthJsonFile();;
		Response response=restClient.get(BASE_URL_LOCAL_HOST_PORT, "/api/users", null, null, AuthType.NO_AUTH, ContentType.ANY);
		response.then()
		.assertThat()
		.statusCode(200)
		.body("name", equalTo("John Doe"));
	}
	
	
	

}
