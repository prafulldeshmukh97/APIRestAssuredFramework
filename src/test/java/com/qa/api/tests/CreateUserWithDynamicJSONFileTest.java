package com.qa.api.tests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.StringUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserWithDynamicJSONFileTest extends BaseTest {

	@Test
	public void createUserWithDynamicEmailIDtoJSONFIleTest() {
		String FilePath = "src/test/resource/jsons/userJsonFile.json";
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode userNode = mapper.readTree(Files.readAllBytes(Paths.get(FilePath)));
			// create new emailid
			String uniqueEmail = StringUtility.getRandomeEmailId();

			// update the exisitng email to unique
			ObjectNode obj = (ObjectNode) userNode;
			obj.put("email", uniqueEmail);

			// convert jsonnode to json string
			String updateJsonString = mapper.writeValueAsString(userNode);
			System.out.println("Updated JSON string =" + updateJsonString);

			Response response = restClient.post(BASE_URL_GOREST, "/public/v2/users", updateJsonString, null, null,
					AuthType.BEARER_TOKEN, ContentType.JSON);
			Assert.assertEquals(response.statusCode(), 201);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
