package com.qa.api.mocking.tests;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.APIMock;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MockCreateUserAPITests extends BaseTest {

	@Test
	public void createDummyUserTest()
	{
		String Dummybody="{\"name\":\"Prafull_Automaiton\"}";
		APIMock.createDummyUser();
		Response response=restClient.post(BASE_URL_LOCAL_HOST_PORT, "api/users", Dummybody, null, null, AuthType.NO_AUTH,ContentType.JSON);
		response.then()
		.assertThat()
		.statusCode(201)
		.statusLine(equalTo("HTTP/1.1 201 User is created.."));
	}
	@Test
	public void createDummyUserWithJSONFileTest()
	{
		String Dummybody="{\"name\":\"John Doe\"}"; //just for formality we are passing this line
		APIMock.createDummyUserWithJSONFile();
		Response response=restClient.post(BASE_URL_LOCAL_HOST_PORT, "api/users", Dummybody, null, null, AuthType.NO_AUTH,ContentType.JSON);
		response.then()
		.assertThat()
		.statusCode(201)
		.statusLine(equalTo("HTTP/1.1 201 User is created.."))
		.body("id",equalTo(101));
	}
}
