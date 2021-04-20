package tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ana_rules.*;
import metrics.Method;
import metrics.Class;

class RuleObjectTest {
	static RuleObject metric_1;
	static RuleObject metric_2;
	static RuleObject metric_3;
	static RuleObject metric_4;
	static RuleObject metric_5;

	static Method method;
	static MethodMetric methodmetric;
	static MethodMetric methodmetric_fail;
	
	static Comparator_Operator comparator_operator;
	static Logical_Operator logical_operator;
	static ClassMetric class_metric;
	static Class c_1;

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception{		
		metric_1 = new RuleObject("LOC_METHOD", "METHODMETRIC");
		metric_2 = new RuleObject("1", "THRESHOLD");		
		metric_3 = new RuleObject("OR", "LOGIC_OPERATOR");		
		metric_4 = new RuleObject("NOM_CLASS", "CLASSMETRIC");		
		metric_5 = new RuleObject("EQUALS", "COMPARISON_OPERATOR");	
		
		method = new Method();
		methodmetric = MethodMetric.LOC_METHOD;
		method.setLOC_method(1);
		method.setCYCLO_method(1);

		comparator_operator = Comparator_Operator.EQUALS;
		logical_operator = Logical_Operator.AND;
		class_metric = ClassMetric.NOM_CLASS;
		c_1 = new Class();
	}
	
	@Test
	final void ruleObjectTest() { //não lança excepcao
		Assertions.assertNotNull(metric_1);
		assertEquals("LOC_METHOD", metric_1.getInfo());
	}
	
	@Test
	final void ruleObjectExceptionsTest() {
		assertThrows(IllegalArgumentException.class, () -> new RuleObject("teste", "THRESHOLD"));
		assertThrows(IllegalArgumentException.class, () -> new RuleObject("teste", "LOGIC_OPERATOR"));
		assertThrows(IllegalArgumentException.class, () -> new RuleObject("teste", "CLASSMETRIC"));
		assertThrows(IllegalArgumentException.class, () -> new RuleObject("teste", "COMPARISON_OPERATOR"));
		assertThrows(IllegalArgumentException.class, () -> new RuleObject("teste", "METHODMETRIC"));

	}
	
	@Test
	final void getInfoTest() {
		assertEquals("LOC_METHOD", metric_1.getInfo());
	}
	
	@Test
	final void getLabelTest() {
		assertEquals(RuleObjectType.valueOf("METHODMETRIC"), metric_1.getLabel()); 
	}
	
	@Test
	final void getMetricTest() {
		assertEquals(1, MethodMetric.getMetric(methodmetric, method)); 
		
		methodmetric = MethodMetric.CYCLO_METHOD;
		assertEquals(1, MethodMetric.getMetric(methodmetric, method)); 
		
		//ANA: FALTA TESTAR O RETURN ZERO
		//assertEquals(0, MethodMetric.getMetric(methodmetric_fail, method)); 
	}
	
	@Test
	final void compareTest() {
		Assert.assertFalse(Comparator_Operator.compare(1, comparator_operator, 0)); 
		Assert.assertTrue(Comparator_Operator.compare(0, comparator_operator, 0)); 


		comparator_operator = Comparator_Operator.NOTEQUALS;
		Assert.assertTrue(Comparator_Operator.compare(1, comparator_operator, 0)); 
		Assert.assertFalse(Comparator_Operator.compare(0, comparator_operator, 0)); 
		
		comparator_operator = Comparator_Operator.GREATER;
		Assert.assertTrue(Comparator_Operator.compare(1, comparator_operator, 0)); 
		Assert.assertFalse(Comparator_Operator.compare(0, comparator_operator, 0)); 


		comparator_operator = Comparator_Operator.LESS;
		Assert.assertFalse(Comparator_Operator.compare(1, comparator_operator, 0)); 
		Assert.assertTrue(Comparator_Operator.compare(0, comparator_operator, 1)); 

		comparator_operator = Comparator_Operator.GREATEREQUAL;
		Assert.assertTrue(Comparator_Operator.compare(1, comparator_operator, 0)); 
		Assert.assertFalse(Comparator_Operator.compare(0, comparator_operator, 1)); 
		
		comparator_operator = Comparator_Operator.LESSEQUAL;
		Assert.assertFalse(Comparator_Operator.compare(1, comparator_operator, 0)); 
		Assert.assertTrue(Comparator_Operator.compare(0, comparator_operator, 1)); 
		
		//ANA: FALTA TESTAR O RETURN ZERO
	}
	
	@Test
	final void logicOperationTest() {
		
		Assert.assertTrue(Logical_Operator.logicOperation(true, logical_operator, true)); 
		Assert.assertFalse(Logical_Operator.logicOperation(false, logical_operator, false)); 
		Assert.assertFalse(Logical_Operator.logicOperation(true, logical_operator, false)); 


		logical_operator = Logical_Operator.OR;
		Assert.assertTrue(Logical_Operator.logicOperation(true, logical_operator, true)); 
		Assert.assertTrue(Logical_Operator.logicOperation(true, logical_operator, false)); 
		Assert.assertTrue(Logical_Operator.logicOperation(false, logical_operator, true)); 
		Assert.assertFalse(Logical_Operator.logicOperation(false, logical_operator, false)); 

		//ANA: FALTA TESTAR O RETURN ZERO
	}
	
	@Test
	final void getClassMetricTest() {
		c_1.setNOM_class(0);
		assertEquals(0, ClassMetric.getMetric(class_metric, c_1)); 
		
		c_1.setLOC_class(0);
		class_metric = ClassMetric.LOC_CLASS;
		assertEquals(0, ClassMetric.getMetric(class_metric, c_1)); 

		c_1.setWMC_class(0);
		class_metric = ClassMetric.WMC_CLASS;
		assertEquals(0, ClassMetric.getMetric(class_metric, c_1)); 
	}

	//ANA: FALTA TESTAR O RETURN ZERO
}
	
	
	

