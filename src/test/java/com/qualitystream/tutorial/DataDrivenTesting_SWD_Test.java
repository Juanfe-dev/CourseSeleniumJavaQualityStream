package com.qualitystream.tutorial;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DataDrivenTesting_SWD_Test {

	private WebDriver driver;	
	private WriteExcelFile writeFile;
	private ReadExcelFile readFile;
	private By searchBoxLocator = By.id("search_query_top"); 
	private By searchBtnLocator = By.name("submit_search");
	private By resultTextLocator = By.cssSelector("span.heading-counter");
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		writeFile= new WriteExcelFile();
		readFile = new ReadExcelFile();
		
		driver.get("http://automationpractice.com/index.php");
	}

	@After
	public void tearDown() throws Exception {
		//driver.quit();
	}

	@Test
	public void test() throws IOException {
		//Archivo a leer
		String filepath = "C:\\Users\\jgomez\\Documents\\CampusSC\\EclipseWorkspace\\QualityStreamTutorial\\excelDocuments\\Test.xlsx";
		//Termino a buscar (primera celda (0, 0): dresses)
		String searchText = readFile.getCellValue(filepath, "Sheet1", 0, 0);
		//buscamos el primer termino en la web
		driver.findElement(searchBoxLocator).sendKeys(searchText);
		//Clic en buscar
		driver.findElement(searchBtnLocator).click();
		//Guardamos el mensaje de resultado
		String resultText = driver.findElement(resultTextLocator).getText();
		//Imprimimos que esta pasando
		System.out.println("Page result text: "+resultText);
		
		//Escribimos el resultado en la celda siguiente
		//Leemos el archivo
		readFile.readExcel(filepath, "Sheet1");
		//Escribimos en la fila 1, columna 2 (0,1)
		writeFile.writeCellValue(filepath, "Sheet1", 0, 1, resultText);
		//Corroboramos que si se escribio
		readFile.readExcel(filepath, "Sheet1");
		
	
	}

}
