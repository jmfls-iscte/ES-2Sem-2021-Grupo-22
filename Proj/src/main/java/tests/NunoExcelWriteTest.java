package tests;

import static org.junit.Assert.assertEquals;
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

class NunoExcelWriteTest {
	static ExcelWrite ew;
	static ArrayList<Package> arrayP = new ArrayList<Package>();
	static Package p1= new Package();
	static Class c1 = new Class();
	static Method m1 = new Method();
//	static Metrics metric = new Metrics(0,"package","class","method",1,2,3,false,4,5,true);
	static ArrayList<Metrics> arrayM = new ArrayList<Metrics>();
	static XSSFWorkbook workbook;
	static String path = "C:\\Users\\ASUS\\Desktop\\Metrics.xlsx";
	
	
	
	@BeforeAll
	static void setUpBeforeClass() throws IOException {
		ew = new ExcelWrite();
		
		arrayP.add(p1);
		p1.getClass_list().add(c1);
		c1.getMethod_list().add(m1);    
    }

	
	@Test
	void testAddMetrics() {
		assertEquals(0, ew.metrics.size());
		ew.addMetrics(arrayP);
		assertNotEquals(0, ew.metrics.size());				
	}
	

	@Test
	void testWriteFile() throws IOException {
		
		File file = new File(path);
		FileOutputStream fileOut = new FileOutputStream(path);
		
		Assertions.assertNotNull(fileOut);
		Assertions.assertNotNull(path);
		assertEquals(false, file.exists());
		
		ew.writeFile(path, arrayP);
		
		Assertions.assertNotNull(fileOut);
	}	
	
	@Test
	void testWriteFileFalse() throws IOException {
		Assertions.assertNotNull(ew.exists);
		assertFalse(ew.exists);
		
		ew.createWorkBook();
		assertNotNull(ew.workbook);
		
	}
	
	
	@Test
	void testWriteFileTrue() throws IOException {
		
		
	//	Assertions.assertNotNull(ew.exists);
		
	//	ew.sendAlert();
		
	
		
	}

	

}
