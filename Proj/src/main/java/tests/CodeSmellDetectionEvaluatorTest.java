package tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import CodeSmellDetectionEvaluator.CodeSmellDetectionEvaluator;
import CodeSmellDetectionEvaluator.Utils;
import metrics.Class;
import metrics.Method;
import metrics.Package;

class CodeSmellDetectionEvaluatorTest {
	
	//set package
	static CodeSmellDetectionEvaluator csde_test;
	static List<Package> packagesDetectionlst_test = new ArrayList<Package>();
	static List<Package> packagesExcellst_tes = new ArrayList<Package>();
	static Package pg_test = new Package("pg_test") ;
	static Class c1_test = new Class("c1_test");
	static Method m1_test = new Method("m1_test");
	static Class c2_test = new Class("c2_test");
	static Method m2_test = new Method("m2_test");


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



	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		c1_test.setLOC_class(10);
		c1_test.setNOM_class(20);
		c1_test.setWMC_class(30);
		
		m1_test.setLOC_method(10);
		m1_test.setCYCLO_method(20);
		m1_test.setBegin(0);
		m1_test.setEnd(1);
		m1_test.addSmell("test_method", true);
		m1_test.addSmell("test_2_method", true);
		
		c1_test.addMethod(m1_test);
		
		c1_test.addSmell("test_class", false);
		c1_test.addSmell("test_2_class", true);
		c1_test.addSmell("test_3_class", true);
		
		pg_test.addClass(c1_test);
		 
		c2_test.setLOC_class(10);
		c2_test.setNOM_class(20);
		c2_test.setWMC_class(30);
		
		
		m2_test.setLOC_method(10);
		m2_test.setCYCLO_method(20);
		m2_test.setBegin(0);
		m2_test.setEnd(1);
		m2_test.addSmell("test_method", true);
		m2_test.addSmell("test_2_method", true);
		
		c2_test.addMethod(m2_test);
		
		c2_test.addSmell("test_class", false);
		c2_test.addSmell("test_2_class", true);
		c2_test.addSmell("test_3_class", true);
		
		pg_test.addClass(c2_test);

		packagesDetectionlst_test.add(pg_test);
		packagesExcellst_tes.add(pg_test);
	
	}
	
	@Test
	void testCodeSmellDetectionEvaluator() {
		assertNotNull(csde_test = new CodeSmellDetectionEvaluator(packagesDetectionlst_test,packagesExcellst_tes));
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
	void testGetRulesName() {
		assertEquals(5, csde_test.getRulesName().size());
	}
	
	@Test
	void testgetClassesName() {
		assertEquals(2,csde_test.getClassesName("pg_test").size());
	}
	
	@Test
	void testgetgetPackagesName() {
		assertEquals(1, csde_test.getPackagesName().size());
	}
	
	@Test
	void testGetClassificationClassRule() {
		assertEquals(4, csde_test.getClassificationClassRule("pg_test","c2_test", "test_2_class").size());
	}
	
	@Test
	void testGetClassificationPackageRule() {
		assertEquals(4,csde_test.getClassificationPackageRule("pg_test","test_2_class").size());
	}	
	
	@Test
	void testgetClassificationRule() {
		assertEquals(4,csde_test.getClassificationRule("test_2_class").size());
	}
	
	@Test
	void testgetClassificationClass() {
		assertEquals(4,csde_test.getClassificationClass("pg_test", "c1_test").size());
		assertNull(csde_test.getClassificationClass("pg_test", "error"));
		assertNull(csde_test.getClassificationClass("error", "error"));
	}
	
	@Test
	void testgetClassificationPackage() {
		assertEquals(4,csde_test.getClassificationPackage("pg_test").size());
	}
	
	@Test
	void testgetClassificationTotal() {
		assertEquals(4,csde_test.getClassificationTotal().size());

	}
	

}
