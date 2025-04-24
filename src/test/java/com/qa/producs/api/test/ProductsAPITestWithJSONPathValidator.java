package com.qa.producs.api.test;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.JsonPathUtil;
import com.qa.api.utils.JsonPathValidator;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductsAPITestWithJSONPathValidator extends BaseTest {
	
	@Test
	public void getProdcutsWithJSONPath()
	{
		Response response=restClient.get(BASE_URL_PRODUCTS,"/products", null, null,AuthType.NO_AUTH,ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
		
		List<Number>prices=JsonPathValidator.readList(response,"$[?(@.price>50)].price");
		System.out.println(prices);
		
		List<Number>ids=JsonPathValidator.readList(response,"$[?(@.price>50)].id");
		System.out.println(ids);
		
		List<Number>rates=JsonPathValidator.readList(response,"$[?(@.price>50)].rating.rate");
		System.out.println(rates);
		
		List<Number>counts=JsonPathValidator.readList(response,"$[?(@.price>50)].rating.count");
		System.out.println(counts);
		//list map
		List<Map<String ,Object>> jwelleryList=JsonPathValidator.readListOdMap(response, "$[?(@.category=='jewelery')].['title','price']");
		System.out.println(jwelleryList.size());
		for(Map<String ,Object>  e:jwelleryList)
		{
			String title=(String) e.get("title");
			Number price=(Number) e.get("price");
			System.out.println("Title="+title);
			System.out.println("Price="+price);
			System.out.println("-------------------");
		}
		//get min price 
		Double minprice=JsonPathValidator.read(response, "min($[*].price)");
		System.out.println("Min price ="+minprice);
		System.out.println("---------------------------");
		
	}
	
	 

}
