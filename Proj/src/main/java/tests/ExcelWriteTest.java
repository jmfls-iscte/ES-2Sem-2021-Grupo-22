package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
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
//	static Metrics metric = new Metrics(0,"package","class","method",1,2,3,false,4,5,true);
	static ArrayList<Metrics> arrayM = new ArrayList<Metrics>();
	static XSSFWorkbook workbook;
	static String path = "/Users/anantunes0/Metrics.xlsl";
	
	
	
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
		
		/*File file = new File(path); 
		FileOutputStream fileOut = new FileOutputStream(path);
		
		Assertions.assertNotNull(fileOut);
		Assertions.assertNotNull(path);
		assertEquals(false, file.exists());
		
		ew.writeFile(path, arrayP);
		
		Assertions.assertNotNull(fileOut);*/
	}	
	
	
	
	/*
	@Test
	void testWriteFileFalse() throws IOException {
	//	Assertions.assertNotNull(ew.exists);
	//	assertFalse(ew.exists);
		
	//	ew.createWorkBook();
		assertNotNull(ew.workbook);
		
	}
	
	
	@Test
	void testWriteFileTrue() throws IOException {
		
		
	//	Assertions.assertNotNull(ew.exists);
		
	//	ew.sendAlert();
		
	
		
	}

	*/

}
