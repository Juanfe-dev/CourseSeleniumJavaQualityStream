package quality.stream.code.examples;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;

public class Download_File {

	WebDriver driver;
	private String downloadFilePath = "C:\\Users\\jgomez\\Documents\\CampusSC\\EclipseWorkspace\\QualityStreamTestingFramework\\TEST";
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		//Establecemos valores (llave , valor)
		//Deshabilitando la opcion que abre la ventana para la descarga
		chromePrefs.put("profile.default_content_settings.popups", 0);
		//Agregamos otra para asignar el directorio de descarga
		chromePrefs.put("download.default_directory", downloadFilePath);
		
		//setteamos las capabilities (opciones del navegador)
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		driver = new ChromeDriver(options);
	}

	@Test
	public void downloadFile() throws InterruptedException {
		
		driver.get("https://the-internet.herokuapp.com/download");
		//Descarga el archivo que este de primero en la lista de etiquetas a en div.example>a
		driver.findElement(By.cssSelector("div.example>a")).click();
		Thread.sleep(2000);	
		//Verificamos si se descargo
		File folder = new File(downloadFilePath);
		//Guardamos todos los archivos que se encuentren en la carpeta en el siguiente arreglo
		File[] listofFiles = folder.listFiles();
		assertTrue(listofFiles.length>0, "File not downloaded successfully");
	}

	@AfterClass
	public void afterClass() {
	}

}
