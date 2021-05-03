package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import CodeSmellDetectionEvaluator.ClassEvaluator;
import CodeSmellDetectionEvaluator.EvaluatorType;
import CodeSmellDetectionEvaluator.MethodEvaluator;
import metrics.DirectoryGetter;
import metrics.Class;

class ClassEvaluatorTest {
	
	static ClassEvaluator ce1;
	static Class c1;
	static Map<String, EvaluatorType> codesmelssEvaluator;
	static List<MethodEvaluator> methodslst;
	static EvaluatorType evaluatorType;
	static MethodEvaluator method;

	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		c1 = new Class();
	}
	
	@Test
	void testClassEvaluator() {
		ce1 = new ClassEvaluator(c1);
		assertNotNull(ce1);
	}

	@Test
	void testClassEvaluator2() {
		ce1 = new ClassEvaluator(c1, codesmelssEvaluator);
		assertNotNull(ce1);
	}
	
	
	@Test
	void testClassEvaluator3() {
		ce1 = new ClassEvaluator(c1, codesmelssEvaluator, methodslst);
		assertNotNull(ce1);
	}
		
	@Test
	void testAddRuleAndVal() {
		ce1.addRuleAndVal("rule", evaluatorType);;
		assertEquals(1, ce1.getCodesmelssEvaluator().size());
	}
	
	@Test
	void testsetMethodList() {
		ce1.setMethodList(methodslst);
		assertEquals(methodslst, ce1.getMethodslst());
	}
	@Test
	void testaddMethodList() {
		ce1.addMethodList(method);
		assertEquals(1, ce1.getMethodslst().size());
	}
	
	@Test
	void testgetClasseval() {
		assertEquals(c1, ce1.getClasseval());
	}
	
	@Test
	void testgetEvalByName() {
		assertNull(ce1.getEvalByName(null));
	}	
}
