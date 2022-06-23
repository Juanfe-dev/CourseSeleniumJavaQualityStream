package com.project.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage extends Base {

	By userLocator = By.name("userName");
	By passLocator = By.name("password");
	By signInBtnLocator = By.name("submit");
	By homePage = By.linkText("Home");
	
	public SignInPage(WebDriver driver) {
		super(driver);
	}
	
	public void signIn() {
		if(isDisplayed(userLocator)) {
			type("qualitystream", userLocator);
			type("pass1", passLocator);
			click(signInBtnLocator);
		} else {
			System.out.println("Username textbox was not present");
		}
	}

	public Boolean isHomePageDisplayed() {
		return isDisplayed(homePage);
	}

}
