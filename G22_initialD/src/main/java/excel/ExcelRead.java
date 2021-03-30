package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import metricasCD.*;
import metricasCD.Class;
import metricasCD.Package;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {

	private String path;
	private Scanner scanner; // Create a Scanner object
	private int n_reads;
	private ArrayList<String> cells;
	private ArrayList<Package> packages;
	private Package currentPackage;
	private Class currentClass;
	private Method currentMethod;

	private ArrayList<String> codeSmells_Class;
	private ArrayList<String> codeSmells_Method;

	private int currentCellInt;

	public ExcelRead() {
		// Scanner vai ser alterado quando GUI enviar path
		scanner = new Scanner(System.in);
		n_reads = 0;
		currentCellInt = 0;
		cells = new ArrayList<String>();
		packages = new ArrayList<Package>();

		codeSmells_Class = new Class().get_name_code_Smells();
		codeSmells_Method = new Method().get_name_code_Smells();

		System.out.println("Insira o caminho do ficheiro: ");
		path = scanner.nextLine();

	}

	public ExcelRead(String path) {
		this.path = path;
		n_reads = 0;
		currentCellInt = 0;
		cells = new ArrayList<String>();
		packages = new ArrayList<Package>();
		codeSmells_Class = new Class().get_name_code_Smells();
		codeSmells_Method = new Method().get_name_code_Smells();
	}

	public void ClearVars() {
		currentCellInt = 0;
		currentClass = null;
		currentPackage = null;
		currentMethod = null;
	}

	public void ReadFile() {

		try {
			FileInputStream excelFile = new FileInputStream(new File(path));
			Workbook workbook = new XSSFWorkbook(excelFile);
			org.apache.poi.ss.usermodel.Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();

			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();

				ClearVars();

				while (cellIterator.hasNext()) {

					Cell currentCell = cellIterator.next();
					if (n_reads == 0) {
						/*
						 * Se n_reads = 0 significa que estou na linha do cabeçalho e extraio os nomes
						 * das colunas todas (MethodID--package--class--method--NOM_class--LOC_class...)
						 * E adiciono num array para posteriormente saber qual a métrica onde me
						 * encontro
						 */
						cells.add(currentCell.getStringCellValue());
					} else {
						
						ChooseCell(currentCell);
						currentCellInt++;
					} // Fim do else
					
				} // Fim do while das celulas
				
				n_reads++;

			}//Fim do while das linhas
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.print("So para parar");

	}

	public void ChooseMetric(String var, Cell currentCell) {
		switch (var) {
		case "NOM_class":
			if (currentClass.getNOM_class() == 0)
				currentClass.setNOM_class((int) currentCell.getNumericCellValue());
			break;
		case "LOC_class":
			if (currentClass.getLOC_class() == 0)
				currentClass.setLOC_class((int) currentCell.getNumericCellValue());
			break;
		case "WMC_class":
			if (currentClass.getWMC_class() == 0)
				currentClass.setWMC_class((int) currentCell.getNumericCellValue());
			break;
		case "LOC_method":
			if (currentMethod.getLOC_method() == 0)
				currentMethod.setLOC_method((int) currentCell.getNumericCellValue());
			break;
		case "CYCLO_method":
			if (currentMethod.getCYCLO_method() == 0)
				currentMethod.setCYCLO_method((int) currentCell.getNumericCellValue());
			break;

		default:
			break;
		}

	}
	
	
	
	public void ChooseCell(Cell currentCell)
	{
		switch (currentCellInt) {
		case 0:
			// Method id é para ignorar?
			break;
		case 1:
			// criar ou verificar se existe package com o nome atual da célula
			String package_name = currentCell.getStringCellValue();
			for (Package p : packages) {
				if (p.getName_Package() == package_name)
					currentPackage = p;
			}

			if (currentPackage == null) {
				currentPackage = new Package(package_name);
				packages.add(currentPackage);
			}
			break;

		case 2:
			// criar ou verificar se existe classe com o nome atual da célula
			String class_name = currentCell.getStringCellValue();
			currentClass = currentPackage.get_ClassByName(class_name);
			if (currentClass == null) {
				currentClass = new Class(class_name);
				currentPackage.addClass(currentClass);
			}
			break;

		case 3:
			// criar ou verificar se existe metodo com o nome atual da célula

			String method_name = currentCell.getStringCellValue();
			currentMethod = currentClass.get_MethodByName(method_name);
			if (currentMethod == null) {
				currentMethod = new Method(method_name);
				currentClass.addMethod(currentMethod);
			}
			break;

		default:
			/*
			 * verificar em que metrica estou com ajuda do array String metricaAtual =
			 * cells.get(currentCell);
			 * 
			 * Entrar no package Entrar na classe Entrar no metodo inserir metrica + valor
			 */

			String var = cells.get(currentCellInt);
			ChooseMetric(var, currentCell);
			break;
		} // Fim switch
	}

}