package com.qa.PetClinic;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
	@FindBy(xpath="/html/body/app-root/div[1]/nav/div/ul/li[2]/ul/li[2]/a/span[2]")
	private WebElement addOwner;
	
	@FindBy(xpath="/html/body/app-root/div[1]/nav/div/ul/li[2]/a")
	private WebElement owners;
	
	public WebElement getAddOwner () {
		return addOwner;
	}
	
	public WebElement getOwners() {
		return owners;
	}

}
