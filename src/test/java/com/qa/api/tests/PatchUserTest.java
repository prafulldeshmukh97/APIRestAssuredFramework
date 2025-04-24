package com.qa.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.UserPojo;
import com.qa.api.utils.StringUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PatchUserTest extends BaseTest {
	@Test
	public void patchUserWithBuilderTest()
	{
		//1.POST
		UserPojo user=UserPojo.builder().name("prafull")
		.email(StringUtility.getRandomeEmailId()).gender("male").status("active")
		.build();
		Response response=restClient.post(BASE_URL_GOREST,"/public/v2/users", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		String userID=response.jsonPath().getString("id");
		Assert.assertEquals(response.statusCode(),201);
		
		//2. GET To verify user is created or not
		Response responseGet=restClient.get(BASE_URL_GOREST,"/public/v2/users/"+userID, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responseGet.statusCode(),200);
		Assert.assertEquals(responseGet.jsonPath().getString("id"),userID);
		
		//PUT Call
		//update the body
		user.setEmail(StringUtility.getRandomeEmailId());
		Response responsePatch=restClient.patch(BASE_URL_GOREST,"/public/v2/users/"+userID, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responsePatch.statusCode(), 200);
		Assert.assertEquals(responsePatch.jsonPath().getString("email"),user.getEmail());
		
		
	}


}
