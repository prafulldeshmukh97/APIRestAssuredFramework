package com.qa.api.mocking;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;import org.apache.commons.io.output.ProxyOutputStream;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class APIMock {
	//******************create stub/mock for the GET Calls ****************************
	
	public static void getDummyUser()
	{
		stubFor(get(urlEqualTo("/api/users"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type","application/json")
						.withBody("{\r\n"
								+ "    \"name\":\"prafull\"\r\n"
								+ "}"))
				);	
	}

public static void getDummyUserWIthJsonFile()
{
	stubFor(get(urlEqualTo("/api/users"))
			.willReturn(aResponse()
					.withStatus(200)
					.withHeader("Content-Type","application/json")
					.withBodyFile("userJsonFile.json"))
			);	
}

public static void getDummyUserWIthQueryParams()
{
	stubFor(get(urlPathEqualTo("/api/users"))
			//.withQueryParam("name",equalTo("John Doe"))
			.willReturn(aResponse()
					.withStatus(200)
					.withHeader("Content-Type","application/json")
					.withBodyFile("userJsonFile.json"))
			);	
}

public static void getDummyProductsWIthJsonFile()
{
	stubFor(get(urlEqualTo("/api/products"))
			.willReturn(aResponse()
					.withStatus(200)
					.withHeader("Content-Type","application/json")
					.withBodyFile("products.json"))
			);	
}

//******************create stub/mock for the POST Calls ****************************

public static void createDummyUser()
{
	stubFor(post(urlEqualTo("/api/users"))
			.willReturn(aResponse()
					.withStatus(201)
					.withHeader("Content-Type","application/json")
					.withStatusMessage("User is created..")
					.withBody("{\"id\":1,\"name\":\"Prafull_Automaiton\"}")));
}
public static void createDummyUserWithJSONFile()
{
	stubFor(post(urlEqualTo("/api/users"))
			.willReturn(aResponse()
					.withStatus(201)
					.withHeader("Content-Type","application/json")
					.withStatusMessage("User is created..")
					.withBodyFile("userJsonFile.json")));
}

//******************create stub/mock for the DELETE Calls ****************************
public static void deleteDummyUser()
{
	stubFor(delete(urlEqualTo("/api/users/1"))
			.willReturn(aResponse()
					.withStatus(200)
					.withBody("Resource not found.."))
			
			);
	
}
}

