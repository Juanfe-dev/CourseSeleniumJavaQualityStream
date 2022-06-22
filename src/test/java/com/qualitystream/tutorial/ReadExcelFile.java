package com.qualitystream.tutorial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {

	public ReadExcelFile() {
		//Contructor
	}
	
	//Metodo que permite leer toda la hoja de Excel
	public void readExcel(String filepath, String sheetName) throws IOException {
		/**
		 * file path: ubicacion del archivo
		 * sheetName: hoja a leer 
		**/
		//Instancio para el archivo
		File file = new File(filepath);
		//Archivo de entrada
		FileInputStream inputStream = new FileInputStream(file);
		//Creo el libro de trabajo y le envio el archivo leido
		XSSFWorkbook newWorkbook = new XSSFWorkbook(inputStream);
		//Creo la hoja y le envio el nombre de la hoja leida
		XSSFSheet newSheet = newWorkbook.getSheet(sheetName);
		//Cantidad de filas
		int rowCount = newSheet.getLastRowNum() - newSheet.getFirstRowNum();
		//Contador que itera entre fila y fila
		for(int i = 0; i <= rowCount; i++) { 
			XSSFRow row = newSheet.getRow(i); //Objeto fila que lee fila por fila de la hoja de Excel
			//Contador que itera entre todas las celdas de la fila
			for (int j = 0; j < row.getLastCellNum(); j++) { //Suponiendo que todas las filas tendran la misma cantidad de celdas
				System.out.println(row.getCell(j).getStringCellValue() + " | | ");	
			}
		}
	}
	
	public String getCellValue(String filepath, String sheetName, int rowNumber, int cellNumber) throws IOException {
		/**
		 * file path: ubicacion del archivo
		 * sheetName: hoja a leer 
		 * rowNumber: numero de fila
		 * cellNumber: numero de celda
		**/
		//reutilizamos el workbook, la hoja y el inputstream
		//Instancio para el archivo
		File file = new File(filepath);
		//Archivo de entrada
		FileInputStream inputStream = new FileInputStream(file);
		//Creo el libro de trabajo y le envio el archivo leido
		XSSFWorkbook newWorkbook = new XSSFWorkbook(inputStream);
		//Creo la hoja y le envio el nombre de la hoja leida
		XSSFSheet newSheet = newWorkbook.getSheet(sheetName);
		
		//Vamos a leer una fila especifica, entonces:
		XSSFRow row = newSheet.getRow(rowNumber);
		//Vamos a leer una celda especifica, entonces:
		XSSFCell cell = row.getCell(cellNumber);
		//Retornamo la informacion de la celda en la columna row, celda cell (row, cell)
		return cell.getStringCellValue();
	}
}


