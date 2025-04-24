package com.qa.contacts.api;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.Credentials_ContactsAPI_Pojo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ContactsAPITest extends BaseTest {
	private String tokenID;
	@BeforeMethod
	public void getContactsAPI_Token()
	{
		Credentials_ContactsAPI_Pojo cred = Credentials_ContactsAPI_Pojo.builder()
				.email("prafull.deshmukh979797@gmail.com")
				.password("Test@123")
				.build();
		
		Response response=restClient.post(BASE_URL_CONTACTS,"/users/login", cred, null, null, AuthType.NO_AUTH, ContentType.JSON);
		tokenID=response.jsonPath().getString("token");
		System.out.println("Token id ===>"+tokenID);
		ConfigManager.set("ContactsAPi_bearerToken", tokenID);
	}
	
	@Test
	public void getContactsTest()
	{
		
		Response response =restClient.get(BASE_URL_CONTACTS,"/contacts/", null, null, AuthType.CONTACTS_API_BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
	}

}
