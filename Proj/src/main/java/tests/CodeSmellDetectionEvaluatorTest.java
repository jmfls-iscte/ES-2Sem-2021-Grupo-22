package tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import CodeSmellDetectionEvaluator.CodeSmellDetectionEvaluator;
import metrics.Class;
import metrics.Method;
import metrics.Package;

class CodeSmellDetectionEvaluatorTest {

	//set csde1
	static CodeSmellDetectionEvaluator csde;
	static List<Package> packagesDetectionlst = new ArrayList<Package>();
	static List<Package> packagesExcellst = new ArrayList<Package>();
	
	//set csde2
	static CodeSmellDetectionEvaluator csde_2;
	static List<Package> packagesDetectionlst_2 = new ArrayList<Package>();
	static List<Package> packagesExcellst_2 = new ArrayList<Package>();
	static Package pg1 = new Package("test"); 
	static Package pg2 = new Package("test"); 
	
	//set csde3
	static CodeSmellDetectionEvaluator csde_3;
	static List<Package> packagesDetectionlst_3 = new ArrayList<Package>();
	static List<Package> packagesExcellst_3 = new ArrayList<Package>();
	static Package pg1_3 = new Package(); 
	static Package pg2_3 = new Package(); 
		
	List<Class> classlst = new ArrayList<Class>();
	Class c1 = new Class("test");
	
	List<Method> methodlst = new ArrayList<Method>();
	Method m1 = new Method("test");

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		pg1_3.setName_Package("test");
		pg2_3.setName_Package("test");		
	}

	
	@Test
	void testCodeSmellDetectionEvaluatorEmpty() {
		csde = new CodeSmellDetectionEvaluator(packagesDetectionlst, packagesExcellst);
		assertNotNull(csde);
	}
	
	@Test
	void testCodeSmellDetectionEvaluatorException() {
		packagesDetectionlst_2.add(pg1);
		packagesDetectionlst_2.add(pg2);
		assertThrows(IllegalArgumentException.class, () -> csde_2 = new CodeSmellDetectionEvaluator(packagesDetectionlst_2, packagesExcellst_2));
	}
	
	@Test
	void testCodeSmellDetectionEvaluator() {
		packagesDetectionlst_3.add(pg1_3);
		packagesExcellst_3.add(pg1_3);
		csde_3 = new CodeSmellDetectionEvaluator(packagesDetectionlst_3, packagesExcellst_3);	
		assertNotNull(csde_3);
	}
	
	@Test
	void testGetPackagebyNameFail() {
		assertNull(csde_3.getPackagebyName("error", packagesDetectionlst_3));
	}
	
	@Test
	void testGetClassbyName() {
		 classlst.add(c1);
		 assertEquals(c1, csde_3.getClassbyName("test", classlst));	 
	}
	
	@Test
	void testGetClassbyNameFail() {	
		 assertNull(csde_3.getClassbyName("error", classlst));	 
	}
	
	@Test
	void testGetMethodbyName() {
		 methodlst.add(m1);
		 assertEquals(m1, csde_3.getMethodbyName("test", methodlst));	 
	}
	
	@Test
	void testGetMethodbyNameFail() {	
		 assertNull(csde_3.getMethodbyName("error", methodlst));	 
	}
	
// FALTAM OS PRIVATES
	
	
}
