package com.project.pom;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage extends Base {

	//Localizadores de Register
		By registerLinkLocator = By.linkText("REGISTER");
		By registerPageLocator = By.xpath("//img[@src='images/mast_register.gif']");
		
		By userNameLocator = By.id("email");
		By userPasswordLocator = By.name("password"); // la confirmacion de password de otra manera a modo de practica con css selector
		By confirmPasswordLocator = By.cssSelector("input[name='confirmPassword']");
		
		By registerButtonLocator = By.name("submit");
		
	//Localizadores de Sign In
		By userLocator = By.name("userName");
		By passLocator = By.name("password");
		By signInBtnLocator = By.name("login");
		By homePage = By.linkText("Home");
		
	// Localizadores de register check
		By registeredMessage = By.tagName("font");
		
		public RegisterPage(WebDriver driver){
			super(driver);
		}
	// Metodo para registar 
		public void registerUser() throws InterruptedException {
			click(registerLinkLocator);
			Thread.sleep(2000);
			if(isDisplayed(registerPageLocator)) {
				type("qualityadmin", userNameLocator);
				type("pass1", userPasswordLocator);
				type("pass1", confirmPasswordLocator);
		
				click(registerButtonLocator);
			} else {
				System.out.println("Register pages not found");
			}
		}
		
	//Metodo para confirmar registro
		public String registeredMessage() {
			
			List<WebElement> fonts = findElements(registeredMessage);	
			return getText(fonts.get(5));
		}
	
}
