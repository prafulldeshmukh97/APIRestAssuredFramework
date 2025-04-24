package com.qa.api.schemavalidator.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.UserPojo;
import com.qa.api.utils.SchemaValidator;
import com.qa.api.utils.StringUtility;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class UserSchemaValidatorTest extends BaseTest {

	@Test
	public void getUserSchemaValidatorTest() {
//		RestAssured.given()
//		.baseUri("https://gorest.co.in")
//		.when()
//		.get("/public/v2/users/7848277")
//		.then()
//		.statusCode(200)
//		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/user-schema.json"));

		// POST - create fresh user to use userid
		UserPojo user = UserPojo.builder().name("prafull").email(StringUtility.getRandomeEmailId()).gender("male")
				.status("active").build();
		Response response = restClient.post(BASE_URL_GOREST, "/public/v2/users", user, null, null,
				AuthType.BEARER_TOKEN, ContentType.JSON);
		String userID = response.jsonPath().getString("id");
		Assert.assertEquals(response.statusCode(), 201);
//GET call to featch user for schema validation
		Response responseGet = restClient.get(BASE_URL_GOREST, "/public/v2/users/" + userID, null, null,
				AuthType.BEARER_TOKEN, ContentType.ANY);
		Assert.assertEquals(SchemaValidator.validateSchema(responseGet, "schema/user-schema.json"), true);

	}
}
