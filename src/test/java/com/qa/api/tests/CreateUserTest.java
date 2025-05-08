package com.qa.api.tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.UserPojo;
import com.qa.api.utils.StringUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTest extends BaseTest {

	@DataProvider
	public Object[][] getUserData() {
		return new Object[][] { { "prafull", "active", "male" }, 
			{ "deshmukh", "inactive", "female" },
				{ "riya", "active", "female" } };
	}

	@Test (dataProvider = "getUserData")
	public void createUserTest(String name,String status,String gender) {
		UserPojo user = new UserPojo(null,name,StringUtility.getRandomeEmailId(),gender,status);
		Response response = restClient.post(BASE_URL_GOREST, "/public/v2/users", user, null, null,
				AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 201);
	}

	@Test(dataProvider = "getUserData")
	public void createUserWithBuilderTest(String name,String status,String gender) {
		// POST
		UserPojo user = UserPojo.builder().name(name).email(StringUtility.getRandomeEmailId()).gender(gender)
				.status(status).build();
		Response response = restClient.post(BASE_URL_GOREST, "/public/v2/users", user, null, null,
				AuthType.BEARER_TOKEN, ContentType.JSON);
		String userID = response.jsonPath().getString("id");
		Assert.assertEquals(response.statusCode(), 201);
		// GET To verify user is created or not
		Response responseGet = restClient.get(BASE_URL_GOREST, "/public/v2/users/" + userID, null, null,
				AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responseGet.jsonPath().getString("name"), user.getName());
		Assert.assertEquals(responseGet.jsonPath().getString("email"), user.getEmail());
		Assert.assertEquals(responseGet.jsonPath().getString("gender"), user.getGender());
		Assert.assertEquals(responseGet.jsonPath().getString("status"), user.getStatus());

	}

	@Test(enabled = false)
	public void createUserUsingJSONFileTest() {
		File userJsonFile = new File("./src\\test\\resource\\jsons\\userJsonFile.json");

		Response response = restClient.post(BASE_URL_GOREST, "/public/v2/users", userJsonFile, null, null,
				AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 201);
	}

}
