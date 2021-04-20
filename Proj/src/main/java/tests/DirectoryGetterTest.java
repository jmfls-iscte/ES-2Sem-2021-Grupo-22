package tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import metrics.DirectoryGetter;
import metrics.Package;

class DirectoryGetterTest {
	static DirectoryGetter dg1;
	static DirectoryGetter dg2;
	static DirectoryGetter dg3;


	static File file;
	static File working_directory_file;

	static List<Package> lst;
	static String test = "ana_test";
	
	static String working_directory = "/Users/anantunes0/eclipse-workspace_ee/tp3";
	static String working_directory_2 = "/Users/anantunes0/ES_directory_test_2";
	
	private List<Package> packages_test= new ArrayList<Package>();


	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		dg1 = new DirectoryGetter();
		dg2 = new DirectoryGetter();
		dg3 = new DirectoryGetter();


		file = new File("ana_test");
		working_directory_file = new File("/Users/anantunes0/eclipse-workspace_ee/tp3");

		lst = new ArrayList<Package>();
		
		dg3.SetDir(working_directory_2);

		

	}
	
	@Test
	final void getDirFailTest() {
		Assertions.assertNotNull(dg1);			
		assertThrows(IllegalArgumentException.class, () -> dg1.SetDir(test));
	}
	
	
	@Test
	final void getDirTest() { 
		Assertions.assertNotNull(dg2);			
		dg2.SetDir(working_directory);
		assertNotNull(dg2.getBaseFile());
		assertEquals(working_directory_file, dg2.getBaseFile());
	}
	
	/*
	@Test //NAO CONSIGO TESTAR ESTAS DUAS FUNÇÕES
	final void getBaseFileTestFail() {
		Assertions.assertNull(dg1.getBaseFile()); //PROBLEMAAASSS ????????
	}
	
	@Test
	final void getsrcTestFail() {
		assertNull(dg1.getsrc()); //PROBLEMAAASSS ????????
		
	}*/
	
	@Test
	final void getPackageNumberTest() {
		assertEquals(0, dg2.getPackageNumber());
		
	}
	
	@Test
	final void getPackagesTest() {
		assertEquals(packages_test, dg2.getPackages());
		
	}
	
	@Test
	final void getPackageListTest() {
		assertEquals(packages_test, dg2.getPackageList());		
	}
	
	@Test
	final void FindSrcTest() {
		dg2.FindSrc();
		assertEquals("src", dg2.getsrc().getName());
	}

	@Test
	final void FindSrcTestFail() {
		 assertThrows(IllegalStateException.class, () -> dg3.FindSrc());
	}
	
	@Test
	final void createPackageTest() {
		dg2.FindPackages();
		assertNotNull(dg2.getPackages());
		
		
	}

	
}
