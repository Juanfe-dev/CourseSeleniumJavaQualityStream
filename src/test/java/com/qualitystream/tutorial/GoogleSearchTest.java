package com.qualitystream.tutorial;

import static org.junit.Assert.*;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchTest {

	private WebDriver driver;
	By videoLinkLocator = By.cssSelector("a[href='https://www.youtube.com/watch?v=R_hh3jAqn8M']");
	@Before
	public void setUp() {
		
		// Asignamos propiedades del driver (donde esta el ejecutable)
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		// Instanciamos el driver
		driver = new ChromeDriver();
		driver.manage().window().maximize(); // maximiza ventana
		driver.get("https://www.google.com/"); // Abre google.com
	}
	
	@Test
	public void testGooglePage() {
		
		WebElement searchbox = driver.findElement(By.name("q")); // By.name es un localizador
		searchbox.clear();
		searchbox.sendKeys("quality-stream Introducción a la Automatización de Pruebas de SoftWare");
		searchbox.submit(); // es como dar enter
		
		// implicit wait
			// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		// explicit wait
			//reemplazo con un explicit wait a modo de practica WebDriverWait:ExpectedConditions
			// En la version antigua no era necesario el Duration.ofSeconds(10), solo con ingresar el entero funcionaba
			//WebDriverWait ewait = new WebDriverWait(driver, Duration.ofSeconds(10));
			//Espera hasta que el titulo tenga: quality-stream hasta los 10 segundos
			//ewait.until(ExpectedConditions.titleContains("quality-stream"));
		
			// FluentWait se define en el documento 
				Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver)
						.withTimeout(Duration.ofSeconds(10))
						.pollingEvery(Duration.ofSeconds(2))
						.ignoring(NoSuchElementException.class);
				WebElement video = fwait.until(new Function<WebDriver, WebElement>(){
					public WebElement apply(WebDriver driver) {
						return driver.findElement(videoLinkLocator);
					}
				}
				);
				// verificamos con junit
				assertTrue(driver.findElement(videoLinkLocator).isDisplayed());
				
				
		
		
		// revisamos resultados de la busqueda con junit
		assertEquals("quality-stream Introducción a la Automatización de Pruebas de SoftWare - Buscar con Google", driver.getTitle());
	}
	
	@After 
	public void tearDown() {
		driver.quit();
	}

}
