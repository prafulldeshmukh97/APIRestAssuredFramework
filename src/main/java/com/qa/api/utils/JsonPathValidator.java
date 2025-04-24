package com.qa.api.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

public class JsonPathValidator {

	/**
	 * It will just retrun Jsonresponse as a string
	 * @param response
	 * @return
	 */
	public static String getJSonResponseAsString(Response response)
	{
		return response.getBody().asString();
	}
	/**
	 * it will give any type of data 
	 * @param <T>
	 * @param response
	 * @param jsonPath
	 * @return
	 */
	public static <T> T read(Response response, String jsonPath)	{
		String jsonResponse=getJSonResponseAsString(response);
		DocumentContext ctx=JsonPath.parse(jsonResponse);
		return ctx.read(jsonPath);
	}
/**
 * It will list of any type of data
 * @param <T>
 * @param response
 * @param jsonPath
 * @return
 */
	public static<T> List<T> readList(Response response,String jsonPath)
	{
		String jsonResponse=getJSonResponseAsString(response);
		DocumentContext ctx=JsonPath.parse(jsonResponse);
		return ctx.read(jsonPath);
	}
	
	/**
	 * It will give List of Map type of data
	 * @param <T>
	 * @param response
	 * @param jsonPath
	 * @return
	 */
	public static<T> List<Map<String, T>> readListOdMap(Response response,String jsonPath)
	{
		String jsonResponse=getJSonResponseAsString(response);
		DocumentContext ctx=JsonPath.parse(jsonResponse);
		return ctx.read(jsonPath);
	}
}
