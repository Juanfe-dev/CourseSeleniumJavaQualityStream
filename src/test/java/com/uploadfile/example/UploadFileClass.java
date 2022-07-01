package com.uploadfile.example;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UploadFileClass {

	WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void test() {
		//Instanciamos el archivo a subir
		File file = new File("C:\\Users\\jgomez\\Documents\\CampusSC\\EclipseWorkspace\\QualityStreamTutorial\\TestFiles\\JuanFeTest1.0.txt");
		//Definimos un path absoluto para ese archivo
		String path = file.getAbsolutePath();
		//Abrimos la pagina web donde subiremos el archivo
		driver.get("https://the-internet.herokuapp.com/upload");
		//Adjuntamos el archivo
		driver.findElement(By.id("file-upload")).sendKeys(path);
		//Clic en subir archivo
		driver.findElement(By.id("file-submit")).click();
		
		//PROCEDEMOS A VERIFICAR QUE EL ARCHIVO SI FUE SUBIDO
		//Guardamos el texto del locator
		String text = driver.findElement(By.id("uploaded-files")).getText();
		//Hacemos la comparacion
		//Esperado , Actual
		assertEquals("JuanFeTest1.0.txt", text);
	}
	
	@After
	public void tearDown() throws Exception {
		//driver.quit();
	}

	

}
