package com.chekingLinks.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class CheckLinksTest {
	
	WebDriver driver;
	CheckingLinksPage page;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		page = new CheckingLinksPage(driver);
		driver.get("https://demo.guru99.com/test/newtours/");
	}

	@Test
	public void CheckingLinks() {
		assertTrue(page.CheckingPageLinks(), "There are broken links");
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
