package com.qualitystream.testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNGExample {

	WebDriver driver;
	By searchBoxLocator = new By.ById("search_query_top");
	By resultsLocator = new By.ByCssSelector("span[class=\"heading-counter\"]");

	@Test
	public void search_Blouses() {
		WebElement searchBox = driver.findElement(searchBoxLocator);
		searchBox.clear();
		searchBox.sendKeys("blouse");
		searchBox.submit();
		
		//Creamos una espera
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		//Esperamos hasta que el mensaje resultado este en la pagina
		wait.until(ExpectedConditions.presenceOfElementLocated(resultsLocator));
		
		System.out.println("This is the result number: "+ driver.findElement(resultsLocator).getText());
		
		//Validacion
		assertTrue(driver.findElement(resultsLocator).isDisplayed(), "The result number is not present");
		
	}

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");

	}

	@AfterClass
	public void afterClass() {
		//driver.close();
	}

	/*
	 * 
	 * @BeforeMethod public void beforeMethod() { }
	 * 
	 * @AfterMethod public void afterMethod() { }
	 * 
	 * @BeforeTest public void beforeTest() { }
	 * 
	 * @AfterTest public void afterTest() { }
	 * 
	 * @BeforeSuite public void beforeSuite() { }
	 * 
	 * @AfterSuite public void afterSuite() { }
	 * 
	 */

}
