package excel;

import java.io.File;
import metrics.*;

import metrics.Class;
import metrics.Method;
import metrics.Package;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ana_rules.*;


public class ExcelRead {

	private String path;
	private Scanner scanner; // Create a Scanner object
	private ArrayList<String> cells;
	private Package currentPackage;
	private Class currentClass;
	private Method currentMethod;
	
	private ArrayList<Rule> rules;
	ArrayList<Package> packages;

	private int currentCellInt;
	private int method_id;
	
	
	
	// ========================== TO REMOVE =============================================
	public static void main(String[] args) {
		String path2 = "C:\\Users\\Tiago\\Desktop\\Code_Smells.xlsx";
		
		ArrayList<Rule> rules = new ArrayList<Rule>();
		rules.add(new Rule("is_God_Class", "Class", new ArrayList<RuleObject>()));
		rules.add(new Rule("is_Long_Method", "Method", new ArrayList<RuleObject>()));
		
		ExcelRead er = new ExcelRead(path2, rules);
		ArrayList<Package> p = er.ReadFile();
		for(Package pa:p)
			System.out.println(pa.toString());
	} // ========================== TO REMOVE =============================================

	public ExcelRead(ArrayList<Rule> rules) {
		// Scanner vai ser alterado quando GUI enviar path
		scanner = new Scanner(System.in);
		this.rules = rules;
		
		System.out.println("Insira o caminho do ficheiro: ");
		path = scanner.nextLine();

	}

	public ExcelRead(String path, ArrayList<Rule> rules) {
		this.path = path;
		this.rules = rules;
	}
	
	public void ClearVars() {
		currentCellInt = 0;
		currentClass = null;
		currentPackage = null;
		currentMethod = null;
	}

	public ArrayList<Package> ReadFile() {
		currentCellInt = 0;
		packages = new ArrayList<Package>();
		
		try {
			FileInputStream excelFile = new FileInputStream(new File(path));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = datatypeSheet.iterator();
			
			getCellsTypes(rowIterator);
			
			while (rowIterator.hasNext()) {

				Row currentRow = rowIterator.next();
				int cellInterator = 0;

				ClearVars();

				while (cellInterator < cells.size()) {
					Cell currentCell = currentRow.getCell(cellInterator);	
					ChooseCell(currentCell);
					currentCellInt++;
					cellInterator++;
				} // Fim do while das celulas
				
			}//Fim do while das linhas
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return packages;
	}
	
	
	
	public void getCellsTypes(Iterator<Row> rowIterator)
	{
		cells = new ArrayList<String>();
		Row currentRow = rowIterator.next();
		Iterator<Cell> cellIterator = currentRow.iterator();
		
		while (cellIterator.hasNext()) {
			Cell currentCell = cellIterator.next();
			String a = currentCell.getStringCellValue();
			cells.add(a);
		}
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
		try {
		
		switch (currentCellInt) {
		case 0:
				method_id = (int) currentCell.getNumericCellValue();
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
			if(class_name.contains(".")) {
				 String[] names = class_name.split("\\.");
				 class_name=names[1];
			}
			currentClass = currentPackage.get_ClassByName(class_name);
			if (currentClass == null) {
				currentClass = new Class(class_name);
				currentPackage.addClass(currentClass);
			}
			break;

		case 3:
			// criar ou verificar se existe metodo com o nome atual da célula

			String method_name = currentCell.getStringCellValue();
			if(method_name.contains(".")) {
				String args[] = method_name.split("\\,");
				method_name="";
				int count=0;
				for(String arg:args)
				{
					if(arg.contains(".")) {
						String[] names = arg.split("\\.");
						if(count==0)
							method_name+=names[1];
						else
							method_name+=","+names[1]; 
					}else {
						if(count==0)
							method_name+=arg;
						else
							method_name+=","+arg;
					}
					count++;
				}
			}
			currentMethod = currentClass.get_MethodByName(method_name);
			if (currentMethod == null) {
				currentMethod = new Method(method_name);
				currentMethod.setMethod_id(method_id);
				currentClass.addMethod(currentMethod);
			}
			break;
		case 4:
		case 5:
		case 6:
		case 8:
		case 9:
			/*
			 * verificar em que metrica estou com ajuda do array String metricaAtual =
			 * cells.get(currentCell);
			 * 
			 * Entrar no package Entrar na classe Entrar no metodo inserir metrica + valor
			 */

			String var = cells.get(currentCellInt);
			ChooseMetric(var, currentCell);
			break;
			
		default:
			ChooseRule(currentCell);
			break;
		} // Fim switch
		
		
		}catch (Exception e) {
			//Apanha a excessão das células vazias
		}
	}
	
	public void ChooseRule(Cell currentCell)
	{
		String rule_name = cells.get(currentCellInt);
		Rule r = VerifyExistsCodeSmell (rule_name);
		if(r!=null)
		{
			//Inserir resultado codesmel em algum lado
			if(r.getType().equals("class"))
			{
				currentClass.addSmell(r.getName(), currentCell.getBooleanCellValue());
			}else if (r.getType().equals("method"))
			{
				currentMethod.addSmell(r.getName(), currentCell.getBooleanCellValue());
			}
		}
	}

	
	public Rule VerifyExistsCodeSmell(String name)
	{
		/*
		 * Verifica se existe alguma regra com o nome daquele code_smell
		 * Se não existir não lê do excel
		 */
		for (Rule r : rules)
		{
			if (r.getName().equals(name))
				return r;
		}
		return null;
	}

}
