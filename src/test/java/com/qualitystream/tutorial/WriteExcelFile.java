package com.qualitystream.tutorial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcelFile {

	public WriteExcelFile() {
		//contructor
	}
	
	public void writeExcel(String filepath, String sheetName, String[] dataToWrite) throws IOException {
		/**
		 * filepath: ubicacion del archivo
		 * sheetName: hoja a leer 
		 * String[]: arreglo de string
		**/
		// Intanciamos el archivo
		File file = new File(filepath);
		//Archivo de entrada
		FileInputStream inputStream = new FileInputStream(file);
		//Intanciamos el libro
		XSSFWorkbook newWorkbook = new XSSFWorkbook(inputStream);
		//Instanciamos la hoja
		XSSFSheet newSheet = newWorkbook.getSheet(sheetName);
		//Contamos las filas
		int rowCount = newSheet.getLastRowNum() - newSheet.getFirstRowNum();
		//Guardamos una fila
		XSSFRow row = newSheet.getRow(0);
		//Creamos una fila
		XSSFRow newRow = newSheet.createRow(rowCount+1);
		//Recorremos las filas
		for (int i = 0; i < row.getLastCellNum(); i++) {
			//Creamos la celda por iteracion de i
			XSSFCell newCell = newRow.createCell(i);
			//Escribimos en las nuevas celdas
			newCell.setCellValue(dataToWrite[i]);
		}
		//Detenemos la escritura (cerramos el archivo)
		inputStream.close();
		// Creamos un archivo de salida
		FileOutputStream outputStream = new FileOutputStream(file);
		//Escribimos en el archivo
		newWorkbook.write(outputStream);
		//Cerramos el archivo
		outputStream.close();
	}
	
	public void writeCellValue(String filepath, String sheetName, int rowNumber, int cellNumber, String resultText) throws IOException {
		/**
		 * filepath: ubicacion del archivo
		 * sheetName: hoja a leer 
		 * rowNumber: numero de fila
		 * cellNumber: numero de celda ¡HARA REFERENCIA A LA SEGUNDA CELDA EN EL EXCEL!
		 * resultText: texto a escribir en celda
		**/
		//Copiamos del metodo anterior: 
		File file = new File(filepath);
		//Archivo de entrada
		FileInputStream inputStream = new FileInputStream(file);
		//Intanciamos el libro
		XSSFWorkbook newWorkbook = new XSSFWorkbook(inputStream);
		//Instanciamos la hoja
		XSSFSheet newSheet = newWorkbook.getSheet(sheetName);
		//Creamos la fila
		XSSFRow row = newSheet.getRow(rowNumber);
		//Aqui vamos a ver la pagina a automatizar antes de continuar (Ver Onenote Cap6, Youtube: min 19:16)
		//Creamos una nueva celda
		XSSFCell firstCell = row.getCell(cellNumber-1);
		System.out.println("First cell value is: " + firstCell.getStringCellValue());
		
		XSSFCell nextCell = row.createCell(cellNumber);
		nextCell.setCellValue(resultText);
		
		System.out.println("Nextcell Value: "+ nextCell.getStringCellValue());
		
		inputStream.close();
		
		FileOutputStream outputStream = new FileOutputStream(file);
		
		newWorkbook.write(outputStream);
		
		outputStream.close();
		
		
		
		
	}
	
}
