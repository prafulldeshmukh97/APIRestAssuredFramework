package com.qa.producs.api.test;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.ProductsPojo;
import com.qa.api.utils.JsonUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetProductsAPIWithDeserializationTest extends BaseTest {
	@Test
	public void getProductsDeserializationTest() {
		Response response = restClient.get(BASE_URL_PRODUCTS,"/users", null, null, AuthType.NO_AUTH, ContentType.JSON);
		ProductsPojo[] product = JsonUtils.getDeserializaResponse(response, ProductsPojo[].class);
		for (ProductsPojo e : product) {
			System.out.println("ID=" + e.getId());
			System.out.println("Title=" + e.getTitle());
			System.out.println("Price=" + e.getPrice());
			System.out.println("Description =" + e.getDescription());
			System.out.println("Category=" + e.getCategory());
			System.out.println("Image" + e.getImage());
			System.out.println("Rate=" + e.getRating().getRate());
			System.out.println("Count=" + e.getRating().getCount());
			System.out.println("==================================");

		}

	}

}
