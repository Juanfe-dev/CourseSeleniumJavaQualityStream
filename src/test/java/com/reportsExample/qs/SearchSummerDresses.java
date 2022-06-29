package com.reportsExample.qs;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class SearchSummerDresses {

	WebDriver driver;
	By searchBoxLocator = new By.ById("search_query_top");
	By resultsLocator = new By.ByCssSelector("span[class=\"heading-counter\"]");

	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");
	}
	
	@Test
	public void search_SummerDresses() {
		WebElement searchBox = driver.findElement(searchBoxLocator);
		searchBox.clear();
		searchBox.sendKeys("Summer Dresses");
		searchBox.submit();

		// Creamos una espera
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		// Esperamos hasta que el mensaje resultado este en la pagina
		wait.until(ExpectedConditions.presenceOfElementLocated(resultsLocator));

		System.out.println("This is the result number: " + driver.findElement(resultsLocator).getText());

		// Validacion
		assertEquals(driver.findElement(resultsLocator).getText(), "4 results have been found.");
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
