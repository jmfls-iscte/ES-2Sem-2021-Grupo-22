package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ana_rules.Rule;
import ana_rules.RuleObject;
import excel.ExcelRead;
import excel.ExcelWrite;
import metrics.Class;
import metrics.Method;
import metrics.Metrics;
import metrics.Package;

class NunoExcelReadTest {

	private static String path;
	private static Scanner scanner; // Create a Scanner object
	private static int n_reads;
	private static ArrayList<String> cells = new ArrayList<String>();
	private static ArrayList<Package> packages = new ArrayList<Package>();
	private static ArrayList<Rule> rules = new ArrayList<Rule>();
	private static ArrayList<RuleObject> rulesObj = new ArrayList<RuleObject>();
	 static Package currentPackage;
	 static Class currentClass;
	 static Method currentMethod;
	 static ExcelRead er;

	private static ArrayList<String> codeSmells_Class= new Class().get_name_code_Smells();
	private static ArrayList<String> codeSmells_Method = new Method().get_name_code_Smells();
	
	private static int currentCellInt;

	static Cell currentCell;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws IOException {
		path = "C:\\\\Users\\\\ASUS\\\\Desktop\\\\ES\\\\Code_Smells.xlsx";
		n_reads = 0;
		currentCellInt = 0;
		cells = new ArrayList<String>();
		packages = new ArrayList<Package>();
		rules = new ArrayList<Rule>();
		er = new ExcelRead(path, rules);


		codeSmells_Class = new Class().get_name_code_Smells();
		codeSmells_Method = new Method().get_name_code_Smells();
		
		
		
		/*FileInputStream excelFile = new FileInputStream(new File(path));
		Workbook workbook = new XSSFWorkbook(excelFile);
		org.apache.poi.ss.usermodel.Sheet datatypeSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = datatypeSheet.iterator();
		Row currentRow = iterator.next();
		Iterator<Cell> cellIterator = currentRow.iterator();
		currentCell = cellIterator.next();*/
		
	}
	
	@Test
	void testExcelRead() {
		er = new excel.ExcelRead(path, rules);
		assertNotNull(er);
	}



	@Test
	void testReadFile() {
		fail("Not yet implemented");
	}

	@Test
	void testChooseMetricNOM_class() throws IOException {
		String var ="NOM_class";
		assertNotNull(var);
		
		
		assertTrue(var.equals("NOM_class"));
		assertEquals(0, er.getCurrentClass().getNOM_class());
		er.ChooseMetric(var, currentCell);
		
		assertEquals(er.getCurrentClass().getNOM_class(),(int)currentCell.getNumericCellValue());
	}
	
	@Test
	void testChooseMetricLOC_class() {
		String var ="LOC_class";
		assertNotNull(var);
		
		assertTrue(var.equals("LOC_class"));
		assertEquals(0,currentClass.getLOC_class());
		
		assertEquals(currentClass.getLOC_class(),(int)currentCell.getNumericCellValue());
	}
	
	@Test
	void testChooseMetricWMC_class() {
		String var ="WMC_class";
		assertNotNull(var);
		
		assertTrue(var.equals("WMC_class"));
		assertEquals(0,currentClass.getWMC_class());
		
		assertEquals(currentClass.getWMC_class(),(int)currentCell.getNumericCellValue());
	}
	
	@Test
	void testChooseMetricLOC_method() {
		String var ="LOC_method";
		assertNotNull(var);
		
		assertTrue(var.equals("LOC_method"));
		assertEquals(0,currentMethod.getLOC_method());
		
		assertEquals(currentMethod.getLOC_method(),(int)currentCell.getNumericCellValue());
	}
	
	@Test
	void testChooseMetricCYCLO_method() {
		String var ="NOM_class";
		assertNotNull(var);
		
		assertTrue(var.equals("NOM_class"));
		assertEquals(0,currentMethod.getCYCLO_method());
		
		assertEquals(currentMethod.getCYCLO_method(),(int)currentCell.getNumericCellValue());
	}
	
	
	@Test
	void testVerifyExistsCodeSmell() {
		String name = "rule";
		Rule r = new  Rule("rule", "tipo", rulesObj);
		er.getRules().add(r);
		er.VerifyExistsCodeSmell(name);
		
		assertEquals(r.getName(), name);
	}
	
	@Test
	void testChooseCell() {
		
		currentCellInt = 1;
		assertNotNull(currentCellInt);
		assertEquals(1, currentCellInt);
		
		er.ChooseCell(currentCell);
		
	//	assertNotEquals(0, er.packages.size());
	}
	
	@Test
	void testClearVars() {
		
		er.ClearVars();
		
		assertEquals(0, er.getCurrentCellInt());
		assertNull(er.getCurrentClass());
		assertNull(er.getCurrentPackage());
		assertNull(er.getCurrentMethod());
	}

}
