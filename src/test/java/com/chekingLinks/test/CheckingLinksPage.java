package com.chekingLinks.test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckingLinksPage {

	private WebDriver driver;

	public CheckingLinksPage(WebDriver driver) {
		// Constructor
		this.driver = driver;
	}

	public boolean CheckingPageLinks() {

		// Creamos una lista de links
		// Usamos el findElements en plural!
		// La linea siguiente dice que encuentra y guarda en una lista todos los
		// elementos de etiquetas <a>
		List<WebElement> links = driver.findElements(By.tagName("a"));

		// Guardara la url de cada link
		String url = "";

		// Lista para links con problemas, invalidos
		List<String> brokenLinks = new ArrayList<String>();
		// Lista para links sin problemas, validos
		List<String> okLinks = new ArrayList<String>();

		// Nos permite realizar una conexion http
		HttpURLConnection httpConection = null;
		// Codigo de estado de conexion con la pagina
		int responseCode = 200;

		// Creamos un iterador para la lista de links
		Iterator<WebElement> it = links.iterator();

		// Iteramos sobre la lista
		while (it.hasNext()) {

			url = it.next().getAttribute("href");

			if (url == null || url.isEmpty()) {
				System.out.println(url + "url is not configure or it is empty.");
				continue;
			}

			try {

				// Establecer la conexion http
				// Casting para convertir lo que nos devuelve a httpurlconnection
				httpConection = (HttpURLConnection) (new URL(url).openConnection());
				// Usualmente se usa GET para llamar una pagina,
				// En este caso llamamos al HEAD ya que con el encabezado encontraremos el
				// codigo de estado que necesitamos
				httpConection.setRequestMethod("HEAD");
				httpConection.connect();
				// Guardamos el codigo de estado de la conexion
				responseCode = httpConection.getResponseCode();
				// Códigos de respuesta de conexión http:
				// https://developer.mozilla.org/es/docs/Web/HTTP/Status
				// Verifico si el link esta roto o no
				if (responseCode >= 400) {
					System.out.println("ERROR BROKEN LINK: -- " + url);
					// Agrego el link a la lista de links invalidos
					brokenLinks.add(url);
				} else {
					System.out.println("VALID LINK: -- " + url);
					// Agrego el link a la lista de links validos
					okLinks.add(url);
				}
			} catch (Exception e) {
				// Imprimir en la consola la excepcion que capture
				e.printStackTrace();
			}
		}
		//Mensaje a la consola con el total de links validos
		System.out.println("Valid Links: " + okLinks.size());
		//Mensaje a la consola con el total de links invalidos
		System.out.println("Invalid Links: " + brokenLinks.size());
		
		//Vamos a retornar a quien lo necesite la lista de links rotos
		if(brokenLinks.size() > 0) {
			System.out.println("***** ERROR------------------------ Broken Links: ");
			 for (int i = 0; i < brokenLinks.size(); i++) {
				 System.out.println(brokenLinks.get(i));
			 }
			 return false;
		} else {
			return true;
		}
	}
}
