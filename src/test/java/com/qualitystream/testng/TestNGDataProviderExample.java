package com.qualitystream.testng;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class TestNGDataProviderExample {

	WebDriver driver;
	By signInLocator = new By.ByLinkText("Sign in");
	By emailLocator = new By.ById("email");
	By passwordLocator = new By.ById("passwd");
	By signInBtnLocator = new By.ById("SubmitLogin");
	By signOutBtnLocator = new By.ByCssSelector("a.logout");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");
	}

	@Test(dataProvider = "authenticationData")
	public void login(String email, String password) {
		
		if(driver.findElement(signInLocator).isDisplayed()) {
			driver.findElement(signInLocator).click();
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			//Esperar hasta que veamos el campo email
			wait.until(ExpectedConditions.presenceOfElementLocated(emailLocator));
			
			//Autenticar
			driver.findElement(emailLocator).sendKeys(email);
			driver.findElement(passwordLocator).sendKeys(password);
			driver.findElement(signInBtnLocator).click();
			
			//Autenticados
			assertEquals(driver.findElement(signOutBtnLocator).getText(), "Sign out");
			
			//Sign out
			driver.findElement(signOutBtnLocator).click();
		} else {
			System.out.println("Sign in link not present");
		}
		
	}
	
	//Por buenas practicas le declaramos el name por si hay un refactor
	@DataProvider(name= "authenticationData")
	//Le pasamos ese nombre al Test login
	//Renombramos el dp
	public Object[][] getData() {
		//Creamos un arreglo bidimensional de tipo object
		Object[][] data = new Object[2][2]; //Matriz 2x2 de tipo object
		
		data[0][0] = "qs123@gmail.com"; data[0][1]="qs123"; // email y pass del primer usuario
		data[1][0] = "testng_qs@gmail.com"; data[1][1]="qs123"; // email y pass del segundo usuario
		return data;
	}

	@AfterClass
	public void afterClass() {
	//driver.close();
	}
	
}
