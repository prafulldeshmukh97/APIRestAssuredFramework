package com.qa.api.mocking.tests;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.APIMock;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MockDeleteUserAPiTests extends BaseTest {
	
	@Test
	public void deleteDummyUserTest()
	{
		APIMock.deleteDummyUser();
		Response respons =restClient.delete(BASE_URL_LOCAL_HOST_PORT, "/api/users/1", null, null,AuthType.NO_AUTH, ContentType.JSON);
		respons.then()
		.assertThat()
		.statusCode(200);
	
	}

}
