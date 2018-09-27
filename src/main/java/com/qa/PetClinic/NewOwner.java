package com.qa.PetClinic;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewOwner {
	@FindBy(xpath="//*[@id=\"firstName\"]")
	private WebElement firstName;
	
	@FindBy(xpath="//*[@id=\"lastName\"]")
	private WebElement lastName;

	@FindBy(xpath="//*[@id=\"address\"]")
	private WebElement address;

	@FindBy(xpath="//*[@id=\"city\"]")
	private WebElement city;

	@FindBy(xpath="//*[@id=\"telephone\"]")
	private WebElement telephone;

	
	public WebElement getFirst() {
		return firstName;
	}
	
	public WebElement getLast() {
		return lastName;
	}
	
	public WebElement getAddress() {
		return address;
	}
	
	public WebElement getCity() {
		return city;
	}
	
	public WebElement getPhone() {
		return telephone;
	}

}
