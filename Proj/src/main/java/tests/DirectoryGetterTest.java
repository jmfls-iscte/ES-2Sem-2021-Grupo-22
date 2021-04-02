package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import metrics.DirectoryGetter;


class DirectoryGetterTest {

	static String teste ;
	static DirectoryGetter dg;
	static File teste_file;
	static List<Package> lst;

	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		teste = "teste";
		dg = new DirectoryGetter();
		teste_file = new File("teste");
		lst = new ArrayList<Package>();

		//dg.createPackage("teste", teste_file, lst);
	}
	
	@Test
	final void SetDirTest() {
		Assertions.assertNotNull(dg);		
		//Assertions.assertNotNull(dg.baseFile);
		
		//assertThrows();
		
	}

	@Test
	final void FindSrcTest() {
	//	Assertions.assertNotNull(dg.src);
		//assertThrows();
		//if
	}

	@Test
	final void FindPackagesTest() {
		//Assertions.assertNotNull(dg.Packages);

	}

	@Test
	final void createPackageTest() {
		//assertEquals(lst, dg.createPackage("teste", teste_file, lst));
	}
	
	@Test
	final void getPackageList() {
		
	}
	
	
}
