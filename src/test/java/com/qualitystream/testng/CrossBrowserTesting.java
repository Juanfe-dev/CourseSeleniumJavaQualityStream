package com.qualitystream.testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class CrossBrowserTesting {

	private WebDriver driver;
	By searchBoxLocator = new By.ByName("q");
	By videoLocator = new By.ByCssSelector("a[href=\"https://www.youtube.com/watch?v=R_hh3jAqn8M\"]");

	@BeforeClass
	// Llamo parametros que estan en el testng.xml
	@Parameters({ "URL", "BrowserType" })
	// Modifico el metodo Before con estos parametros anteriores
	public void beforeClass(String url, String browserType) {

		// Debemos determinar que navegador settear, equalsIgnoreCase no discrimna mayus
		// y minus
		if (browserType.equalsIgnoreCase("Chrome")) {

			System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browserType.equalsIgnoreCase("Firefox")) {

			System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver/geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browserType.equalsIgnoreCase("Microsoft Edge")) {

			System.setProperty("webdriver.edge.driver", "./src/test/resources/edgedriver/msedgedriver.exe");
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.get(url);
		System.out.println("Opening: " + browserType);

	}

	@Test
	public void googleSearch() {

		WebElement searchBox = driver.findElement(searchBoxLocator);
		searchBox.clear();
		searchBox.sendKeys("Introduccion a la automatizacion de pruebas de software");
		searchBox.submit();

		// Creamos una espera
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		// Esperamos hasta que el mensaje resultado este en la pagina
		wait.until(ExpectedConditions.presenceOfElementLocated(videoLocator));
		// Validacion
		assertTrue(driver.findElement(videoLocator).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
	//driver.quit();
	}

}
