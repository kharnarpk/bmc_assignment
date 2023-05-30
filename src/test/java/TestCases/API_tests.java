package TestCases;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.math3.optim.nonlinear.vector.jacobian.AbstractLeastSquaresOptimizer;
import org.apache.http.HttpStatus;
import org.asynchttpclient.util.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.http.Route;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.Assert;
import PageObjectModel.SearchAndPrintResult;
import Resources.BaseClass;
import Resources.Constant;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import PageObjectModel.ValidateProdWithRange;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Hit and print status code and response of all below API's
//●	CreateBooking
//●	GetBooking
//●	UpdateBooking
//●	DeleteBooking


public class API_tests  {

	String token, bookingId;
	RequestSpecification request;
	Response response;
	@SuppressWarnings("rawtypes")
	ResponseBody body;
	JsonPath jPathPost;

	@Test(priority = 1)
	public void createBooking() {

		System.out.println("\n\n ---------- Create booking ---------- ");
		
		RestAssured.baseURI = Constant.createBooking_BASE_URL;
		String addProductsRequest = "{\"firstname\" : \"Jim\"," + "\"lastname\" : \"Brown\"," + "\"totalprice\" : 111,"
				+ "\"depositpaid\" : true," + "\"bookingdates\" : {" + "\"checkin\" : \"2023-04-04\","
				+ "\"checkout\" : \"2023-04-05\"" + "}," + "\"additionalneeds\" : \"Breakfast\"" + "}";
		
		System.out.println("Request Body is: " + addProductsRequest);
		request = RestAssured.given();
		request.header("Content-Type", "application/json");

		response = request.body(addProductsRequest).post().then().extract().response();
		System.out.println("Create booking Status code : " + response.getStatusCode());
		body = response.getBody();
		System.out.println("Response Body is: " + body.asString());

		jPathPost = response.jsonPath();
		bookingId = jPathPost.get("bookingid").toString();

		System.out.println("Booking id is: " + bookingId);
	}

	@Test(priority = 2)
	public void geBooking() throws IOException, InterruptedException {
		System.out.println("\n\n ---------- Get booking ---------- ");
		
		System.out.println("Get booking baseURI : " + Constant.getBooking_BASE_URL + bookingId);
		RestAssured.baseURI = Constant.getBooking_BASE_URL + bookingId;
		
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		response = request.get().then().extract().response();
		
		System.out.println("Get booking Status code : " + response.getStatusCode());
		System.out.println("Response : " + response.getBody().asString());

	}

	@Test(priority = 3)
	public void getToken() {

		System.out.println("\n\n ---------- Get token ----------  ");
		RestAssured.baseURI = Constant.createToken_BASE_URL;
		String addProductsRequest = "{\"username\" : \"admin\"," + "\"password\" : \"password123\"" + "}";

		System.out.println("Request Body is: " + addProductsRequest);
		request = RestAssured.given();
		request.header("Content-Type", "application/json");

		response = request.body(addProductsRequest).post().then().extract().response();

		body = response.getBody();
		System.out.println("Cretae Token status code : " + response.getStatusCode());
		jPathPost = response.jsonPath();
		token = jPathPost.get("token").toString();

	}

	@Test(priority = 4)
	public void updateBooking() {

		System.out.println("\n\n ---------- Update booking ----------  ");
		
		RestAssured.baseURI = Constant.updateBooking_BASE_URL + bookingId;
		System.out.println("Update booking baseURI : " + Constant.updateBooking_BASE_URL + bookingId);
		Cookie myCookie = new Cookie.Builder("token", token).build();

		request = RestAssured.given();
		request.header("Content-Type", "application/json").header("Accept","application/json").cookie(myCookie);

		String updateProductsRequest = "{\"firstname\" : \"Pallavi\"," + "\"lastname\" : \"Brown\","
				+ "\"totalprice\" : 111," + "\"depositpaid\" : true," + "\"bookingdates\" : {"
				+ "\"checkin\" : \"2023-04-04\"," + "\"checkout\" : \"2023-04-05\"" + "},"
				+ "\"additionalneeds\" : \"Breakfast\"" + "}";
		System.out.println("Request Body is: " + updateProductsRequest);

		response = request.body(updateProductsRequest).put().then().extract().response();
		System.out.println("Update booking Status code : " + response.getStatusCode());
		body = response.getBody();
		System.out.println("Response : " + body.asString());
		
		jPathPost = response.jsonPath();
		Assert.assertEquals("Pallavi", jPathPost.get("firstname").toString());

	}

	@Test(priority = 5)
	public void deleteBooking() {
		System.out.println("\n\n ---------- Delete booking ----------  ");
		RestAssured.baseURI = Constant.deleteBooking_BASE_URL + bookingId;

		RequestSpecification request = RestAssured.given();
		Cookie myCookie = new Cookie.Builder("token", token).build();
		request.header("Content-Type", "application/json").cookie(myCookie);

		response = request.delete().then().extract().response();
		System.out.println("Delete booking Status code : " + response.getStatusCode());
		body = response.getBody();
		System.out.println("Response : " + body.asString());
		Assert.assertEquals("Created", body.asString());

	}

}