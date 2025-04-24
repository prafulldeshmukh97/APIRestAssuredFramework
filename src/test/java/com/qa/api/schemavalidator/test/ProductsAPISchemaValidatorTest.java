package com.qa.api.schemavalidator.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.SchemaValidator;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductsAPISchemaValidatorTest extends BaseTest {

	@Test
	public void productsAPischemaValidatorTest() {
//		RestAssured.given()
//		.baseUri("https://fakestoreapi.com")
//		.when()
//		.get("/products")
//		.then()
//		.statusCode(200)
//		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/productsAPISchema.json"));
		Response response = restClient.get(BASE_URL_PRODUCTS, "/products", null, null, AuthType.NO_AUTH,
				ContentType.ANY);
		Assert.assertEquals(SchemaValidator.validateSchema(response, "schema/productsAPISchema.json"), true);
	}

}
