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
	
	private static String path;
	private static ArrayList<Rule> rules = new ArrayList<Rule>();
	private static ArrayList<RuleObject> rulesObj = new ArrayList<RuleObject>();
	static ExcelRead er;
	 
	static ExcelRead er_2;
	static Rule rule = new Rule("NOM_class", "class", rulesObj);

	static Cell currentCell;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		path = "/Users/anantunes0/Code_Smells.xlsx"; //Colocar o path do file Code_Smells_xlsx
		er = new ExcelRead(path, rules);					
	}
	
	@Test
	void testExcelRead() {
		er = new excel.ExcelRead(rules);
		assertNotNull(er);
	}

	@Test
	void testExcelRead2() {
		er = new excel.ExcelRead(path, rules);
		assertNotNull(er);
	}
	
	@Test
	void testReadFile() {
		 assertNotNull(er.ReadFile());
	}
	
	@Test
	void testVerifyExistsCodeSmellFail() {
		assertNull(er.VerifyExistsCodeSmell("rule"));
	}
	
	@Test
	void testVerifyExistsCodeSmell() {
		rules.add(rule);
		er_2 = new excel.ExcelRead(path, rules);
		assertNotNull(er_2.VerifyExistsCodeSmell("NOM_class"));
	}
	
	@Test
	void testVerifyExistsCodeSmellFailOnIf() {
		assertNull(er_2.VerifyExistsCodeSmell("test"));
		rules.remove(rule);
	}
		 
}
