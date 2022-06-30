package quality.stream.code.examples;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class CheckDownloadedFile {

	private WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/download");
	}

	@Test
	public void checkDownloadedFile() throws MalformedURLException, IOException {
	String link = driver.findElement(By.cssSelector("div.example>a")).getAttribute("href");
	
	//creamos conexion http con esa url del link del fichero
	HttpURLConnection httpConnection = (HttpURLConnection)(new URL(link).openConnection()); //recordar el casteo
	httpConnection.setRequestMethod("HEAD");
	httpConnection.connect();
	
	//nos interesa el tipo y tamaño de archivo
	String contentType = httpConnection.getContentType();
	int contentLength = httpConnection.getContentLength();
	
	System.out.println("Content Type: " + contentType);
	System.out.println("Content Length " + contentLength);
	
	//retorna true si coincide el tipo
	assertEquals(contentType, "application/octet-stream");
	//En el campo donde esta:  "application/octet-stream" ... ponemos el tipo de archivo que se esta descargando
	// tamaño mayor que cero bytes
	//Retorna true si no son iguales, y ese es el objetivo
	assertNotEquals(contentLength, 0);
	}

	@AfterClass
	public void afterClass() {
	}
}
