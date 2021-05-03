package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import CodeSmellDetectionEvaluator.EvaluatorType;
import CodeSmellDetectionEvaluator.MethodEvaluator;
import metrics.Method;

class MethodEvaluatorTest {

	static MethodEvaluator me1;
	static Method m1; 
	static Map<String, EvaluatorType> codesmelssEvaluator = new HashMap<String, EvaluatorType>();
	static EvaluatorType et1 = EvaluatorType.FN;	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		m1 = new Method();
	}
	
	
	@Test
	void testMethodEvaluator() {
		me1 = new MethodEvaluator(m1);
		assertNotNull(me1);
	}
	
	@Test
	void testMethodEvaluator2() {
		me1 = new MethodEvaluator(m1, codesmelssEvaluator);
		assertNotNull(me1);
	}
	@Test
	void testaddRuleAndVal() {
		me1.addRuleAndVal("rule", et1);
		codesmelssEvaluator.put("rule", et1);
		assertEquals(codesmelssEvaluator, me1.getCodesmelssEvaluator());	
	}
	
	@Test
	void testgetEvalByName() {
		assertEquals(et1, me1.getEvalByName("rule"));
	}
	
	@Test
	void testgetMethodEval() {
		assertEquals(m1, me1.getMethodEval());

	}
}
