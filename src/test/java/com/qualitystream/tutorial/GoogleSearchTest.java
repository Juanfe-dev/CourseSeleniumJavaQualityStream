package com.qualitystream.tutorial;

import static org.junit.Assert.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleSearchTest {

	private WebDriver driver;
	
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
		searchbox.submit();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// revisamos resultados de la busqueda con junit
		assertEquals("quality-stream Introducción a la Automatización de Pruebas de SoftWare - Buscar con Google", driver.getTitle());
	}
	
	@After 
	public void tearDown() {
		driver.quit();
	}

}
