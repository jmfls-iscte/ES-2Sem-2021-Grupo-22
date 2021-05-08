package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
	static String path = "metrics.xlsx";
	
	static ExcelWrite ew_2;

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ew = new ExcelWrite();

		c1.addMethod(m1);
		p1.addClass(c1);
		arrayP.add(p1);
		
		ew_2 = new ExcelWrite();
    }

	@Test
	void testAddMetrics(){
		ew.addMetrics(arrayP);
		assertEquals(1, ew.getMetrics().size());				
	}
	
	@Test
	void testWriteFile() throws IOException {
		ew.writeFile(path, arrayP);
		File file = new File(path);
		assertTrue(file.exists());
	}
	
	@Test
	void testWriteFileFail(){
		assertThrows(IOException.class, () -> ew_2.writeFile(path, arrayP));
	}	
}
