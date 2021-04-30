package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import CodeSmellDetectionEvaluator.EvaluatorType;
import CodeSmellDetectionEvaluator.MethodEvaluator;
import metrics.Method;

class MethodEvaluatorTest {

	static MethodEvaluator me1;
	static Method m1; 
	static Map<String, EvaluatorType> codesmelssEvaluator;
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

	//FALTAM METODOS
	
	
}
