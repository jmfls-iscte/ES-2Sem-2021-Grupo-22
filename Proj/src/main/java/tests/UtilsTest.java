package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import CodeSmellDetectionEvaluator.ClassEvaluator;
import CodeSmellDetectionEvaluator.EvaluatorType;
import CodeSmellDetectionEvaluator.MethodEvaluator;
import CodeSmellDetectionEvaluator.PackageEvaluator;
import CodeSmellDetectionEvaluator.Utils;
import metrics.Class;
import metrics.Package;


class UtilsTest {

	static List<Package> packagelst = new ArrayList<Package>();
	
	static List<Class> classlst = new ArrayList<Class>();
	static Class c1 = new Class("c1");
	static Class c2 = new Class("c2");

	static List<metrics.Method> methodlst = new ArrayList<metrics.Method>();

	static metrics.Method m1 = new metrics.Method("m1");
	
	static List<PackageEvaluator> packageevaluatorlst = new ArrayList<PackageEvaluator>();
	
	static PackageEvaluator pe1 = new PackageEvaluator("pe1");
	static ClassEvaluator ce1 = new ClassEvaluator(c1);
	
	static ClassEvaluator ce2 = new ClassEvaluator(c2);
	static MethodEvaluator me1 = new MethodEvaluator(m1);

	static Map<EvaluatorType, Integer> mapa1;
	static Map<String, EvaluatorType> classcodesmellsmap; 
	static Map<String, EvaluatorType> methodcodesmellsmap; 
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		methodlst.add(m1);
		
		pe1.addClass(ce1);
		packageevaluatorlst.add(pe1);
		
		mapa1 = new HashMap<EvaluatorType, Integer>();
		classcodesmellsmap = new HashMap<String, EvaluatorType>();
		methodcodesmellsmap = new HashMap<String, EvaluatorType>();

		
		classcodesmellsmap.put("LOC_class", EvaluatorType.FP);
		ce2.setCodesmelssEvaluator(classcodesmellsmap);
		
		methodcodesmellsmap.put("LOC_method", EvaluatorType.TP);
		me1.setCodesmelssEvaluator(methodcodesmellsmap);
		ce2.addMethodList(me1);
		mapa1.put(EvaluatorType.FP, 0);
		mapa1.put(EvaluatorType.TP, 1);
		
	}
	
	@Test
	void testgetPackagebyName() {
		assertNull(Utils.getPackagebyName("test", packagelst));
	}
	
	@Test
	void testgetClassbyName() {
		assertNull(Utils.getClassbyName("test", classlst));
	}
	
	@Test
	void testgetMethodbyName() {
		assertNull(Utils.getMethodbyName("test", methodlst));
		assertEquals(m1, Utils.getMethodbyName("m1", methodlst));
	}
	
	@Test
	void testgetClassificationTotal() {
		assertNotNull(Utils.getClassificationTotal(mapa1, packageevaluatorlst));
	}
	
	@Test
	void testgetClassificationPackage() {
		assertNotNull(Utils.getClassificationPackage(mapa1, packageevaluatorlst, "pe1"));
	}
	
	@Test
	void testgetClassificationClass() {

		assertEquals(2, Utils.getClassificationClass(mapa1, ce2).size());
	}
	
	@Test
	void testgetClassificationClassRule() {
		
		assertNotNull(Utils.getClassificationClassRule(mapa1, ce2, "error"));
		assertEquals(2, Utils.getClassificationClassRule(mapa1, ce2, "LOC_class").size());
		assertEquals(2, Utils.getClassificationClassRule(mapa1, ce2, "LOC_method").size());

	}
	
	@Test
	void testgetClassificationRule() {
		pe1.addClass(ce2);
		assertNotNull(Utils.getClassificationRule(mapa1, pe1, "error"));
		assertEquals(2, Utils.getClassificationRule(mapa1, pe1, "LOC_class").size());
		assertEquals(2, Utils.getClassificationRule(mapa1, pe1, "LOC_method").size());
	}
	
}

