package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excel.*;
import metrics.*;
import metrics.Class;
import metrics.Package;

class ExcelWriteTest {
	static ExcelWrite ew;
	static ArrayList<Package> arrayP = new ArrayList<Package>();
	static Package p1= new Package();
	static Class c1 = new Class();
	static Method m1 = new Method();
	static ArrayList<Metrics> arrayM = new ArrayList<Metrics>();
	static XSSFWorkbook workbook;
	static String path = "/Users/anantunes0/metrics.xlsx";
	
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ew = new ExcelWrite();
		
		c1.addMethod(m1);
		p1.addClass(c1);
		arrayP.add(p1);
    }

	@Test
	void testAddMetrics() {
		ew.addMetrics(arrayP);
		assertEquals(1, ew.getMetrics().size());				
	}
	
	@Test
	void testWriteFile(){
		ew.writeFile(path, arrayP);
		File file = new File(path);
		assertTrue(file.exists());
	}	
}
