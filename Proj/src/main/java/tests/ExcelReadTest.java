package tests;

import static org.junit.Assert.assertThrows;
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

class ExcelReadTest {
	
	private static String path_2;
	private static ArrayList<Rule> rules_2 = new ArrayList<Rule>();
	private static ArrayList<RuleObject> rulesObj_2 = new ArrayList<RuleObject>();
	static ExcelRead er_2;
	 
	static ExcelRead er_3;
	static Rule rule = new Rule("NOM_class", "class", rulesObj_2);

	static Cell currentCell;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		path_2 = "/Users/anantunes0/Code_Smells.xlsx"; //É NECESSÁRIO POR UM PATH CORRECTO
		er_2 = new ExcelRead(path_2, rules_2);					
	}
	
	@Test
	void testExcelRead() {
		er_2 = new excel.ExcelRead(rules_2);
		assertNotNull(er_2);
	}

	@Test
	void testExcelRead2() {
		er_2 = new excel.ExcelRead(path_2, rules_2);
		assertNotNull(er_2);
	}
	
	@Test
	void testReadFile() {
		 assertNotNull(er_2.ReadFile());
	}
	
	@Test
	void testVerifyExistsCodeSmellFail() {
		assertNull(er_2.VerifyExistsCodeSmell("rule"));
	}
	
	@Test
	void testVerifyExistsCodeSmell() {
		rules_2.add(rule);
		er_3 = new excel.ExcelRead(path_2, rules_2);
		assertNotNull(er_3.VerifyExistsCodeSmell("NOM_class"));
	}
	
	@Test
	void testVerifyExistsCodeSmellFailOnIf() {
		assertNull(er_3.VerifyExistsCodeSmell("test"));
		rules_2.remove(rule);
	}
	
	@Test
	void testChooseRule() {
		
	}
	 
}
