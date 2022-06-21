package com.project.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends Base {

	//Localizadores
		By registerLinkLocator = By.linkText("REGISTER");
		By registerPageLocator = By.xpath("//img[@src='images/mast_register.gif']");
		By userNameLocator = By.id("email");
		By userPasswordLocator = By.name("password"); // la confirmacion de password de otra manera a modo de practica con css selector
		By confirmPasswordLocator = By.cssSelector("input[name='confirmPassword']");
		By registerButtonLocator = By.name("submit");
		By userLocator = By.name("userName");
		By passLocator = By.name("password");
		By submitButtonLocator = By.name("submit");
		By homePage = By.linkText("Home");
		
		public RegisterPage(WebDriver driver){
			super(driver);
		}
		// Metodo para registar 
		public void registerUser() {
			click(registerLinkLocator);
			Thread.sleep(2000);
			if(isDisplayed(registerPageLocator)) {
				type("quelityadmin", userNameLocator);
				type("pass1", userPasswordLocator);
				type("pass1", confirmPasswordLocator);
				
				
				
			}
		}
		
		//Metodo para confirmar registro
		public String registeredMessage() {
			return "";
		}
	
}
