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
	static String test = "test";
	
	static String working_directory = "jasml_0.10_forEval";
	static String working_directory_2 = "images";
	
	private List<Package> packages_test= new ArrayList<Package>();

	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		dg1 = new DirectoryGetter();
		dg2 = new DirectoryGetter();
		dg3 = new DirectoryGetter();


		file = new File("test");
		working_directory_file = new File("jasml_0.10_forEval");

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
