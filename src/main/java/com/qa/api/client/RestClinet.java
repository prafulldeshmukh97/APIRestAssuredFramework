package com.qa.api.client;

import static io.restassured.RestAssured.expect;

import java.io.File;
import java.util.Base64;
import java.util.Map;

import com.qa.api.constants.AuthType;
import com.qa.api.exceptions.FrameworkException;
import com.qa.api.manager.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;

public class RestClinet {
	
	private static final String RequestSpecification = null;
	ResponseSpecification responseSpec200 = expect().statusCode(200);
	ResponseSpecification responseSpec200or201 = expect() .statusCode(anyOf(equalTo(200), equalTo(201)));
	ResponseSpecification responseSpec200or404 = expect() .statusCode(anyOf(equalTo(200), equalTo(404)));
	ResponseSpecification responseSpec201 = expect().statusCode(201);
	ResponseSpecification responseSpec204 = expect().statusCode(204);
	ResponseSpecification responseSpec400 = expect().statusCode(400);
	ResponseSpecification responseSpec401 = expect().statusCode(401);
	ResponseSpecification responseSpec422 = expect().statusCode(422);
	ResponseSpecification responseSpec500 = expect().statusCode(500);
	


	private RequestSpecification setUp(String baseUrl,AuthType auth, ContentType contenType) {
		RequestSpecification request = RestAssured.given()
				.baseUri(baseUrl)
				.contentType(contenType)
				.accept(contenType);

		switch (auth) {
		case BEARER_TOKEN:
			request.header("Authorization", "Bearer " + ConfigManager.get("bearerToken"));

			break;
		case CONTACTS_API_BEARER_TOKEN:
			request.header("Authorization", "Bearer " + ConfigManager.get("ContactsAPi_bearerToken"));

			break;
		case OAUTH2:
			request.header("Authorization", "Bearer " + generateOAuth2Token());

			break;
		case BASIC_AUTH:
			request.header("Authorization", "Basic "+generateBasicAuthToken());

			break;
		case API_KEY:
			request.header("x-api-key", ConfigManager.get("apiKey"));

			break;

		case NO_AUTH:
			System.out.println("Authorization is not required");
			break;

		default:
			System.out.println("Auth type is not supported please pass right Auth type");
			throw new FrameworkException("NO AUTH SUPPORTED");
			 	
		}
		return request;
	}

	private String generateOAuth2Token() {
		return RestAssured.given()
				.formParam("grant_type", ConfigManager.get("granType"))
				.formParam("client_secret", ConfigManager.get("clientSecret"))
				.formParam("client_id", ConfigManager.get("clientId"))
				.post(ConfigManager.get("tokenUrl")).then()
				.extract().path("access_token");
	}
	private String  generateBasicAuthToken()
	{
		String credentials =ConfigManager.get("basicUserName")+":"+ConfigManager.get("basicPassword");
		return Base64.getEncoder().encodeToString(credentials.getBytes());
		
	}
	
	//****************CRUD methods ********************************************
	
	/**
	 * This mrthod is used to call GET APIs
	 * @param endPoint
	 * @param queryParam
	 * @param pathParam
	 * @param auth
	 * @param contenttype
	 * @return It return get API response
	 */
	public Response get(String baseUrl,String endPoint,Map<String, String>queryParam,Map<String, String> pathParam,AuthType auth,ContentType contenttype)
	{
	
		RequestSpecification request=setUp( baseUrl,auth, contenttype);
		//RequestSpecification request=setUpAuthAndContentType(auth, contenttype);
		if(queryParam!=null)
		{
			request.queryParams(queryParam);
		}
		if(pathParam!=null)
		{
			request.queryParams(pathParam);
		}
		Response response= request.get(endPoint).then().spec(responseSpec200or404).extract().response();
		response.prettyPrint();
		return response;
	}
	/**
	 * This method is used to call POST APIs
	 * @param <T>
	 * @param body
	 * @param endPoint
	 * @param queryParam
	 * @param pathParam
	 * @param auth
	 * @param contenttype
	 * returns API x`reponse
	 * @return 
	 */
	public<T> Response post(String baseUrl,String endPoint,T body,Map<String, String>queryParam,Map<String, String> pathParam,AuthType auth,ContentType contenttype)
	{
		RequestSpecification request=setUpAuthAndContentType(baseUrl,auth, contenttype);
		applyParams(request, queryParam, pathParam);
	Response response=	request.body(body).log().all()
		.post(endPoint)
		.then().log().all()
		.spec(responseSpec200or201)
		.extract()
		.response();
	response .prettyPrint();
	return response;
	}
	/**
	 * This method is used to pass file as a body to API POST method
	 * @param endPoint
	 * @param file
	 * @param queryParam
	 * @param pathParam
	 * @param auth
	 * @param contenttype
	 * @return
	 */
	//same method overload woith file type instead of TBody
	public Response post(String baseUrl,String endPoint,File file,Map<String, String>queryParam,Map<String, String> pathParam,AuthType auth,ContentType contenttype)
	{
		RequestSpecification request=setUpAuthAndContentType(baseUrl,auth, contenttype);
		applyParams(request, queryParam, pathParam);
	Response response=	request.body(file).log().all()
		.post(endPoint)
		.then().log().all()
		.spec(responseSpec201)
		.extract()
		.response();
	response .prettyPrint();
	return response;
	}
	/**
	 * 
	 * @param <T>
	 * @param endPoint
	 * @param body
	 * @param queryParam
	 * @param pathParam
	 * @param auth
	 * @param contenttype
	 * @return
	 */
	public<T> Response put(String baseUrl,String endPoint,T body,Map<String, String>queryParam,Map<String, String> pathParam,AuthType auth,ContentType contenttype)
	{
		RequestSpecification request=setUpAuthAndContentType(baseUrl,auth, contenttype);
		applyParams(request, queryParam, pathParam);
	Response response=	request.body(body).log().all()
		.put(endPoint)
		.then().log().all()
		.spec(responseSpec200)
		.extract()
		.response();
	response .prettyPrint();
	return response;
	}
	/**
	 * 
	 * @param <T>
	 * @param endPoint
	 * @param body
	 * @param queryParam
	 * @param pathParam
	 * @param auth
	 * @param contenttype
	 * @return
	 */
	public<T> Response patch(String baseUrl,String endPoint,T body,Map<String, String>queryParam,Map<String, String> pathParam,AuthType auth,ContentType contenttype)
	{
		RequestSpecification request=setUpAuthAndContentType(baseUrl,auth, contenttype);
		applyParams(request, queryParam, pathParam);
	Response response=	request.body(body).log().all()
		.patch(endPoint)
		.then().log().all()
		.spec(responseSpec200)
		.extract()
		.response();
	response .prettyPrint();
	return response;
	}
	
	public Response delete(String baseUrl,String endPoint,Map<String, String>queryParam,Map<String, String> pathParam,AuthType auth,ContentType contenttype)
	{
		RequestSpecification request=setUpAuthAndContentType(baseUrl,auth, contenttype);
		applyParams(request, queryParam, pathParam);
		Response response=request.delete(endPoint)
		.then().log().all()
		.spec(responseSpec204)
		.extract()
		.response();
	response .prettyPrint();
	return response;
	}
	
	//****************utility to resue methods------
	
	private RequestSpecification setUpAuthAndContentType(String baseUrl,AuthType auth,ContentType contenttype)
	{
		 return setUp(baseUrl,auth, contenttype);
	}
	
	
	private void applyParams(RequestSpecification request,Map<String, String>queryParam,Map<String, String> pathParam)
	{
		if(queryParam!=null)
		{
			request.queryParams(queryParam);
		}
		if(pathParam!=null)
		{
			request.queryParams(pathParam);
		}
	}
}
