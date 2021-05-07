package tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import CodeSmellDetectionEvaluator.CodeSmellDetectionEvaluator;
import CodeSmellDetectionEvaluator.Utils;
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
	static Package pg1_3 = new Package("test"); 
	static Package pg2_3 = new Package("test"); 
		
	List<Class> classlst = new ArrayList<Class>();
	Class c1 = new Class("test");
	
	List<Method> methodlst = new ArrayList<Method>();
	Method m1 = new Method("test");

	
	//set csde4
	static CodeSmellDetectionEvaluator csde_4;
	static List<Package> packagesDetectionlst_4 = new ArrayList<Package>();
	static List<Package> packagesExcellst_4 = new ArrayList<Package>();
		
	static Package pg1_4 = new Package("pg_4"); 
	static Package pg2_4 = new Package("pg_4"); 
		
	static Class c2 = new Class("c2");
	static Method m2 = new Method("m2");

	static Class c3 = new Class("c3");
	static Method m3 = new Method("m3");
		
	static Class c4 = new Class("c4");
	static Class c5 = new Class("c4");


	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		
		m2.addSmell("LOC_METHOD", true);
		c2.addMethod(m2);
		c2.addSmell("NOM_CLASS", false);
		
		m3.addSmell("CYCLO_METHOD", true);
		c3.addMethod(m3);
		c3.addSmell("LOC_CLASS", true);
		
		c4.addSmell("LOC_CLASS", false);
		c5.addSmell("LOC_CLASS", true);
	
		pg1_4.addClass(c2);
		pg1_4.addClass(c3);
		pg1_4.addClass(c4);
		pg2_4.addClass(c5);

		c4.addSmell("NOM_CLASS", true);
		c5.addSmell("NOM_CLASS", false);

		pg2_4.addClass(c2);
		pg2_4.addClass(c3);
		pg2_4.addClass(c4);
		pg2_4.addClass(c5);
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
	void testCodeSmellDetectionEvaluator_Working() {
		packagesDetectionlst_4.add(pg1_4);
		packagesDetectionlst_4.add(pg1_4);

		packagesExcellst_4.add(pg2_4);
		packagesExcellst_4.add(pg2_4);

		csde_4 = new CodeSmellDetectionEvaluator(packagesDetectionlst_4, packagesExcellst_4);	
		assertNotNull(csde_4);
		
		assertEquals(0, csde_4.getRulesName().size());
	}
	
	@Test
	void testGetRulesName() {
		assertEquals(0, csde_4.getRulesName().size());
	}
	@Test
	void testgetClassesName() {
		assertEquals(0,csde_4.getClassesName("pg_4").size());
	}
	
	@Test
	void testgetgetPackagesName() {
		assertEquals(2, csde_4.getPackagesName().size());
	}
	
	@Test
	void testGetClassificationClassRule() {
		csde_4.getClassificationClassRule("pg_4","c3", "NOM_CLASS");
	}
	
	@Test
	void testGetClassificationPackageRule() {
		assertEquals(4,csde_4.getClassificationPackageRule("pg_4","NOM_CLASS").size());
	}	
}
