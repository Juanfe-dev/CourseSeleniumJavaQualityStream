package com.webElements.packg;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.project.pom.Base;

public class DropdownList_Page extends Base {

	public DropdownList_Page(WebDriver driver) {
		super(driver);
	}
	
	//Dropdownlist clasico - MercuryToursPage https://demo.guru99.com/test/newtours
	//Estos estan despues de loggearse en la pestaña "Flights"
	By dropdownList_Passengers = By.name("passCount");
	By dropdownList_DepartingFrom = By.name("fromPort");
	
	
	//Dropdown Button react-bootstrap : https://react-bootstrap.github.io/components/dropdowns/
	By dropdownListBtn = By.id("dropdown-basic");
	By option2 = By.cssSelector("div[aria-labelledby='dropdown-basic']>a[href='#/action-2']");
	
	//Traemos el metodo de SignIn de ejercicios anteriores com.project.pom:SignInPage.class
	By userLocator = By.name("userName");
	By passLocator = By.name("password");
	By signInBtnLocator = By.name("submit");
	By flightsPage = By.cssSelector("a[href='reservation.php']");
	
	//Login
	public void signIn() {
		if(isDisplayed(userLocator)) {
			type("qualitystream", userLocator);
			type("pass1", passLocator);
			click(signInBtnLocator);
			click(flightsPage);
		} else {
			System.out.println("Username textbox was not present");
		}
	}
	// Despues del login iremos a la pagina de "Flights"
	//Metodo para verificar que estamos en flights
	public Boolean isFlightsPageDisplayed() {
		return isDisplayed(flightsPage);
	}

	//¡TENER EN CUENTA QUE ESTA ES UNA MANERA LARGA DE HACERLO!
	//Metodo para el dropdown de pasajeros (Passengers)
	//Guardaremos las opciones del primer dropdown
	public String selectDropdownList_Passengers() {
		WebElement dropdownList = findElement(dropdownList_Passengers);
		//Lista de opciones
		List<WebElement> options = dropdownList.findElements(By.tagName("option"));
		//Iteramos entre esa lista
		for (int i = 0; i < options.size(); i++) {
			//Al final de cada opcion en la web hay un espacio " ", entonces seria ej: "4 "
			if(getText(options.get(i)).equals("4") ) {
				click(options.get(i)); //Vamos a la clase base a hacer una sobrecarga de click
				//Usaremos "4 " ya que es la opcion que deseamos automatizar
			}
		}
		//Vamos a guardar la opcion (si esta seleccionada) en una variable
		String selectedOption = "";
		for(int i=0; i < options.size(); i++) {
			if(options.get(i).isSelected()) {
				selectedOption = getText(options.get(i));
			}
		}
		//retornamos la opcion
		return selectedOption;
	}
	
	//¡PODEMOS VER QUE ESTA OPCION ES MAS DIRECTA!
	public String selectedDropdownList_DepartingFrom() {
		//A diferencia del metodo anterior, en este caso si trabajaremos con la etiqueta tipo: Select
		Select selectList = new Select(findElement(dropdownList_DepartingFrom));
		//Seleccionar el que diga Paris
		selectList.selectByVisibleText("Paris");
		//Devolvemos el texto de la opcion seleccionada
		return getText(selectList.getFirstSelectedOption());
	}
	// METODO PARA EL REACT-BOOTSTRAP
	public void selectReactDropdownList() throws InterruptedException {
		click(dropdownListBtn);
		Thread.sleep(3000);
		click(option2);
	}
}
