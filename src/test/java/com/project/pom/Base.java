package com.project.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {

	// Declaramor el driver
	private WebDriver driver;
	//Contructor
	public Base(WebDriver driver ){
		this.driver = driver;
	}
	
	//Conexion del driver con el chromedriver
	//Permite crear una instancia del navegador chrome
	public WebDriver chromeDriverConnection() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		return driver;
	}
	//Creamos un envoltorio (Wrapper) donde creamos nuestro propio metodo a partir de los de selenium
	public WebElement findElement(By locator) {
		return driver.findElement(locator);
	}
	//Lista de elementos
	public List<WebElement> findElements(By locator){
		return driver.findElements(locator);
	}
	public String getText(WebElement element) {
		return element.getText();
	}
	//sobrecarga del anterior
	public String getText(By locator) {
		return driver.findElement(locator).getText();
	}
	// Llamaremos al sendkeys como type (escribir)
	public void type(String inputText, By locator) {
		driver.findElement(locator).sendKeys(inputText);
	}
	public void click(By locator) {
		driver.findElement(locator).click();
	}
	public Boolean isDisplayed(By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
	public void visit(String url) {
		driver.get(url);
	}
	
}
