package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excel.ExcelRead;
import rules.Rule;
import rules.RuleObject;

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
		path = "Code_Smells_forEval.xlsx"; //Colocar o path do file Code_Smells_xlsx
		er = new ExcelRead(path, rules);					
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
