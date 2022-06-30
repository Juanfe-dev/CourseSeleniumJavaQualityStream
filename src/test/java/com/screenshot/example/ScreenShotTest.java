package com.screenshot.example;

import static org.junit.Assert.*;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.io.FileUtils;

public class ScreenShotTest {

	private WebDriver driver;

	@Before
	public void setUp() throws Exception {

		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void googleSearchTest() {
		driver.get("https://www.google.com");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.clear();
		searchBox.sendKeys("quality-stream Introduccion a La Automatizacion de Pruebas de Software");
		searchBox.submit();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// VAMOS A HACER QUE FALLE:
		assertEquals("ESTO OCASIONA UN ERROR", driver.getTitle());
		// CUANDO FALLE TOMAREMOS UNA SCREENSHOT
	}

	@Rule
	public TestWatcher watcher = new TestWatcher() {
		@Override
		protected void failed(Throwable throwable, Description description) {
			// Tomamos un screenshot y lo guardamos
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				// IMPORTAMOS APACHE COMMONS IO DE MAVEN
				// Hace una copia desde un archivo fuente que es el screen que tomamos en las
				// sentencias
				// anteriores y lo copiamos en el destino que asignemos
				FileUtils.copyFile(screenshotFile,
						new File("error_" + description.getMethodName() + getDate() + ".png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		@Override
		//AQUI CERRAREMOS EL NAVEGADOR
		//NO USAMOS EL TURNDOWN DE JUNIT YA QUE SI UN TEST FALLA
		//EL METODO TURNDOWN NUNCA SE EJECUTARIA
		protected void finished(Description description) {
			driver.quit();
		}
	};

	public String getDate() {
		// Especificamos en el constructor como queremos el formato de la fecha
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
