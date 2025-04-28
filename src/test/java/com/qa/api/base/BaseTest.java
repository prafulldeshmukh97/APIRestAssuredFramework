package com.qa.api.base;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.api.client.RestClinet;
import com.qa.api.mocking.WiremockSetup;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {
	// *******************8all API Base URLS
	protected static final String BASE_URL_REQ_RES = "https://reqres.in";
	protected static final String BASE_URL_PRODUCTS = "https://fakestoreapi.com";
	protected static final String BASE_URL_GOREST = "https://gorest.co.in";
	protected static final String BASE_URL_CONTACTS = "https://thinking-tester-contact-list.herokuapp.com";
	protected static final String BASE_URL_CIRCUIT = "https://ergast.com";
	protected static final String BASE_URL_BASIC_AUTH = "https://the-internet.herokuapp.com";
	protected static final String BASE_URL_AMADEUS = "https://test.api.amadeus.com";
	protected static final String BASE_URL_LOCAL_HOST_PORT = "http://localhost:8089";
	
	// ***********AppEndpoints**************
		protected static final String GOREST_USERS_ALL_ENDPOINT = "/public/v2/users";
		protected static final String RESTFULBOOKER_CREATE_TOKEN_ENDPOINT = "/auth";
		protected static final String RESTFULBOOKER_BOOKING_IDS_ENDPOINT = "/booking";
		protected static final String REQ_RES_ALL_USERS_ENDPOINT = "/users?page=2";
		protected static final String FAKESTORE_PRODUCTS_ALL_ENDPOINT = "/products";
		protected static final String FAKESTORE_USERS_ALL_ENDPOINT = "/users";
		protected static final String CONTACTS_USER_LOGIN_ENDPOINT = "/users/login";
		protected static final String CONTACTS_ALL_ENDPOINT = "/contacts";

	protected RestClinet restClient;

	@BeforeTest
	public void setUp() {
		WiremockSetup.createWireMockServer();
		RestAssured.filters(new AllureRestAssured());
		restClient = new RestClinet();
		
	}
	
	@AfterTest
	public void stopMockServer() {
		WiremockSetup.stopWireMockServer();;
		
		
	}

}
