package com.webElements.packg;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class DropdownList_Test {
	
	private WebDriver driver;
	DropdownList_Page ddLPage; //Objeto

	@Before
	public void setUp() throws Exception {
		ddLPage = new DropdownList_Page(driver);
		driver = ddLPage.chromeDriverConnection();
		driver.manage().window().maximize(); // ¡Este metodo deberia estar en la clase base!, por ahora lo usaremos asi
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testing_classic_DDL() {
		ddLPage.visit("https://demo.guru99.com/test/newtours/index.php");
		ddLPage.signIn();
		
		assertEquals(ddLPage.selectDropdownList_Passengers(), "4"); //Recordar el espacio "4 "
		assertEquals(ddLPage.selectedDropdownList_DepartingFrom(), "Paris");
	}
	@Test
	public void testing_React_DDL() throws InterruptedException {
		ddLPage.visit("https://react-bootstrap.github.io/components/dropdowns/");
		ddLPage.selectReactDropdownList();
	}
}

