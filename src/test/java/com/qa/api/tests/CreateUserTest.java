package com.qa.api.tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.UserPojo;
import com.qa.api.utils.StringUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTest extends BaseTest {
	
	@Test(enabled = false)
	public void createUserTest()
	{
		UserPojo user = new UserPojo(null,"prafull1234","male",StringUtility.getRandomeEmailId(),"inactive");
	Response response=restClient.post( BASE_URL_GOREST,"/public/v2/users",user, null, null, AuthType.BEARER_TOKEN,ContentType.JSON);
	Assert.assertEquals(response.statusCode(),201);
	}

	@Test
	public void createUserWithBuilderTest()
	{
		//POST
		UserPojo user=UserPojo.builder().name("prafull")
		.email(StringUtility.getRandomeEmailId()).gender("male").status("active")
		.build();
		Response response=restClient.post(BASE_URL_GOREST,"/public/v2/users", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		String userID=response.jsonPath().getString("id");
		Assert.assertEquals(response.statusCode(),201);
		//GET To verify user is created or not
		Response responseGet=restClient.get(BASE_URL_GOREST,"/public/v2/users/"+userID, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responseGet.jsonPath().getString("name"), user.getName());
		Assert.assertEquals(responseGet.jsonPath().getString("email"), user.getEmail());
		Assert.assertEquals(responseGet.jsonPath().getString("gender"), user.getGender());
		Assert.assertEquals(responseGet.jsonPath().getString("status"), user.getStatus());
		
		
	}
	
	@Test(enabled = false)
	public void createUserUsingJSONFileTest()
	{
		File userJsonFile = new File("./src\\test\\resource\\jsons\\userJsonFile.json");
		
		Response response=restClient.post(BASE_URL_GOREST,"/public/v2/users", userJsonFile, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.statusCode(),201);
	}
	
	
}
