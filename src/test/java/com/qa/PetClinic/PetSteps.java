package com.qa.PetClinic;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.equalTo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class PetSteps {
	
	WebDriver driver;
	public static ExtentTest test;
	public static ExtentReports report;
	private RequestSpecification request;
	private Response response;
	private int updateiD = 1;
	private int deleteiD = 26;
	
	@Before
	public void setup() {
		report = new ExtentReports("C:\\Users\\Admin\\Desktop\\eclipse-workspace\\PetClinic\\report.html",false);
		test = report.startTest("test");
		System.setProperty("webdriver.chrome.driver",Constants.driverPath);
		driver= new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@After
	public void teardown() {
		report.flush();
		driver.close();
		driver.quit();
	}
	
	@Given("^a vet$")
	public void a_vet() {
		RestAssured.baseURI = "http://10.0.10.10:9966/petclinic/api/pets";
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
	}

	@When("^I click on some records$")
	public void i_click_on_some_records() {
		response = request.when().get("/"+8);
		test.log(LogStatus.INFO, "Check animal ID: 8");
	}

	@Then("^I can see the care available for animals$")
	public void i_can_see_the_care_available_for_animals() {
	    response.then().body("visits[0].description",equalTo("neutered"));
	    if(response.body().asString().contains("neutered")) {
	    	test.log(LogStatus.PASS, "Check care for animal successful");
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Check care for animal unsuccessful");
	    }
	}

	@Given("^an admin$")
	public void an_admin() {
		RestAssured.baseURI = "http://10.0.10.10:9966/petclinic/api/";
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		driver.get(Constants.url);
	}

	@When("^I update a record$")
	public void i_update_a_record() {
		
		test.log(LogStatus.INFO, "Check update record");
		request.when().get("pets/");
		
		JSONObject requestParams = new JSONObject();
		JSONObject type = new JSONObject();
		JSONObject owner = new JSONObject();
		JSONObject pet = new JSONObject();
		JSONArray visits = new JSONArray();
		JSONArray pets = new JSONArray();
		JSONObject visit = new JSONObject();
		
		visit.put("date", "2018/1/1");
		visit.put("description", "2018/1/1");
		visit.put("id", 1);
		visit.put("pet", pet);
		pets.add(pet);
		
		visits.add(visit);
		
		
		
		
		owner.put("id",1);
		owner.put("firstName", "test");
		owner.put("lastName", "test");
		owner.put("address", "test");
		owner.put("city", "test");
		owner.put("telephone", "test");
		owner.put("firstName", "test");
		owner.put("pets", pets);
		
		
		type.put("id", 1);
		type.put("name", "testName");
		System.out.println(request.body("name"));
		
		requestParams.put("name", "ted");
		requestParams.put("id", updateiD);
		requestParams.put("birthDate", "2018/1/1");
		requestParams.put("type", type);
		requestParams.put("owner", owner);
		requestParams.put("visits", visits);
		
		
		
		System.out.println(requestParams.toString());

		System.out.println(request.body(requestParams.toString()));
		
		request.put("/1");
		response = request.when().get("pets/"+updateiD);
	}

	@Then("^the correct details are now shown$")
	public void the_correct_details_are_now_shown() {
		response.then().body("name",equalTo("ted"));
		if(response.body().asString().contains("ted")) {
	    	test.log(LogStatus.PASS, "Update record successful");
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Update record unsuccessful");
	    }
	}

	@When("^I delete a animal$")
	public void i_delete_a_animal() throws Throwable {
		test.log(LogStatus.INFO, "Check delete animal");
	    response = request.when().delete("pets/"+deleteiD);
	    	
	}

	@Then("^emails arent sent to deceased annimals$")
	public void emails_arent_sent_to_deceased_annimals() throws Throwable {
	    response.then().statusCode(204);
	    if(response.statusCode() == 204) {
	    	test.log(LogStatus.PASS, "Update record successful");
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Update record unsuccessful");
	    }
	}

	@When("^I add new records$")
	public void i_add_new_records() throws Throwable {
		
		
		test.log(LogStatus.INFO, "Check add record");
		
		JSONObject requestParams = new JSONObject();
		JSONObject type = new JSONObject();
		JSONObject owner = new JSONObject();
		JSONObject pet = new JSONObject();
		JSONArray visits = new JSONArray();
		JSONArray pets = new JSONArray();
		JSONObject visit = new JSONObject();
		
		visit.put("date", "2018/1/1");
		visit.put("description", "2018/1/1");
		visit.put("id", 1);
		visit.put("pet", pet);
		pets.add(pet);
		
		visits.add(visit);
		
		
		
		
		owner.put("id",1);
		owner.put("firstName", "test");
		owner.put("lastName", "test");
		owner.put("address", "test");
		owner.put("city", "test");
		owner.put("telephone", "test");
		owner.put("firstName", "test");
		owner.put("pets", pets);
		
		
		type.put("id", 1);
		type.put("name", "testName");
		System.out.println(request.body("name"));
		
		requestParams.put("name", "ted");
		requestParams.put("id", 22);
		requestParams.put("birthDate", "2018/1/1");
		requestParams.put("type", type);
		requestParams.put("owner", owner);
		requestParams.put("visits", visits);
		
		
		
		System.out.println(requestParams.toString());

		System.out.println(request.body(requestParams.toString()));
		
		response =request.post("pets/");
	}

	@Then("^the records are correct$")
	public void the_records_are_correct() throws Throwable {
	    response.then().statusCode(201);
	    if(response.statusCode() == 201) {
	    	test.log(LogStatus.PASS, "Add record successful");
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Add record unsuccessful");
	    }
	}

	@When("^I add new owners to the records$")
	public void i_add_new_owners_to_the_records() throws Throwable {
		
		Actions action = new Actions(driver);
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		NewOwner newOwner = PageFactory.initElements(driver, NewOwner.class);
		
		action.click(homePage.getOwners()).perform();
		action.click(homePage.getAddOwner()).perform();
		newOwner.getFirst().sendKeys("bert");
		newOwner.getAddress().sendKeys("berterson");
		newOwner.getCity().sendKeys("berterson");
		newOwner.getPhone().sendKeys("3433443");
		newOwner.getLast().sendKeys("berterson");
		newOwner.getLast().submit();
		test.log(LogStatus.INFO, "Check add owner");
		
		request.when().get("owners/");

		JSONObject requestParams = new JSONObject();
		JSONObject type = new JSONObject();
		JSONObject owner = new JSONObject();
		JSONObject testowner = new JSONObject();
		JSONObject pet = new JSONObject();
		JSONArray visits = new JSONArray();
		JSONArray pets = new JSONArray();
		JSONObject visit = new JSONObject();
		
		visit.put("date", "2018/1/1");
		visit.put("description", "2018/1/1");
		visit.put("id", 1);
		visit.put("pet", pet);
		pets.add(pet);
		
		visits.add(visit);
		
		
		
		
		owner.put("id",14);
		owner.put("firstName", "test");
		owner.put("lastName", "test");
		owner.put("address", "test");
		owner.put("city", "test");
		owner.put("telephone", "123123123");
		owner.put("firstName", "test");
		owner.put("pets", requestParams);
		
		
		type.put("id", 1);
		type.put("name", "testName");
		System.out.println(request.body("name"));
		
		requestParams.put("name", "ted");
		requestParams.put("id", 4);
		requestParams.put("birthDate", "2018/1/1");
		requestParams.put("type", type);
		requestParams.put("owner", testowner);
		requestParams.put("visits", visits);
		
		
		
		System.out.println(owner.toString());

		System.out.println(request.body(owner.toString()));
		
		response =request.post("owners/");
	}

	@Then("^the details show the change$")
	public void the_details_show_the_change() throws Throwable {
	    response.then().statusCode(201);
	    if(response.statusCode() == 201) {
	    	test.log(LogStatus.PASS, "Add owner successful");
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Add owner unsuccessful");
	    }
	}


}
