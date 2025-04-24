package com.qa.api.utils;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class SchemaValidator {

	@Test
	public static boolean validateSchema(Response response, String FilePath) {
		try {

			response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(FilePath));
			System.out.println("Schema validation passed");
			return true;

		} catch (Exception e) {
			System.out.println("Schema validation failed .."+e.getMessage());
			return false;
		}

	}

}
