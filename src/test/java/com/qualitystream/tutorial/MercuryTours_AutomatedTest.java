package com.qualitystream.tutorial;

import static org.junit.Assert.*;

import java.time.Duration;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MercuryTours_AutomatedTest {

	private WebDriver driver;
	
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
	
	
	
	
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize(); 
		driver.get("https://demo.guru99.com/test/newtours/"); 
	}
	
	@After
	public void tearDown() throws Exception {
		//driver.quit();
	}

	@Test
	public void registerUser() {
		// primer paso dar clic en el boton de registro
		// completar campos para registro
		//confirmar registro
		
		driver.findElement(registerLinkLocator).click();
		//Thread.sleep(3000); //espera de 3 segundos, no es muy recomendado
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3)); //mas viable
		if(driver.findElement(registerPageLocator).isDisplayed()) {
			driver.findElement(userNameLocator).sendKeys("Juan Felipe");
			driver.findElement(userPasswordLocator).sendKeys("12345");
			driver.findElement(confirmPasswordLocator).sendKeys("12345");
			driver.findElement(registerButtonLocator).click();
		} else {
			System.out.println("Register pages not found");
		}
			// no detecta la libreria java.util.List
			// List<WebElement> fonts = driver.findElement(By.tagName("font"));
			// Opte por especificar la libreria
			java.util.List<WebElement> fontsRegisterPage = driver.findElements(By.tagName("font"));
			//vamos a comparar los textos con el tag 6 es decir al index = 5 ya que va de 0 a 5.
			assertEquals("Note: Your user name is Juan Felipe.",fontsRegisterPage.get(5).getText());
		}
		
		@Test
		public void singIn() {
		
			if(driver.findElement(userLocator).isDisplayed()) {
				driver.findElement(userLocator).sendKeys("Juan Felipe");
				driver.findElement(passLocator).sendKeys("12345");
				driver.findElement(submitButtonLocator).click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
				java.util.List<WebElement> fontsLoginPage = driver.findElements(By.tagName("font"));
				driver.findElement(homePage).click();
				assertEquals("Thank you for Loggin.",fontsLoginPage.get(3).getText());
				
		
			}	else 
				System.out.print("Username textBox was not present");
			
			
			
			
		}

}
