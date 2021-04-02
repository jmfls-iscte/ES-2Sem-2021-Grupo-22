package tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ana_rules.Rule;
import ana_rules.RuleObject;
class RuleTest {
	
	static Rule r1;
	static Rule r2; //testa o threshold com erros 

	static ArrayList<RuleObject> rule_info = new ArrayList<>();
	static ArrayList<RuleObject> rule_info_2 = new ArrayList<>();

	static RuleObject metric;
	static RuleObject threshold1;
	static RuleObject threshold2;
	static RuleObject threshold3;
	static RuleObject logic_operator;
		
	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		
		//set r1
		r1 = new Rule("name_test", "type_test", rule_info);
		
		metric = new RuleObject("LOC_method", "metric");
		threshold1 = new RuleObject("10", "threshold");
		threshold2 = new RuleObject("20", "threshold");
		
		r1.setInfo(metric);
		r1.setInfo(threshold1);
				
		//set r2
		threshold3 = new RuleObject("test", "threshold");
		rule_info_2.add(metric);
		rule_info_2.add(threshold3);
		
		logic_operator = new RuleObject("and", "logic_operator");
	}
	
	@Test
	 void testRule() {
		Assertions.assertNotNull(r1);
		assertEquals("name_test", r1.getName());
		assertEquals("type_test", r1.getType());
	}
	
	@Test
	void exceptionTestingFail() {	
	    Throwable exception = assertThrows(NumberFormatException.class, () -> r2 = new Rule("name_test_2", "type_test_2", rule_info_2));
	    assertEquals("O limite inserido não é um número", exception.getMessage());
	}
	
	@Test
	void exceptionTestingPass() {	
		//quando nao lança excepcao 
	}

	@Test
	final void testSetInfo() {
		Assertions.assertNotNull(metric);
		assertEquals(metric, r1.getInfo().get(0));
	}
	
	@Test
	final void testEditThresholdIf() { //têm os 2 thresholds 
		Assertions.assertNotNull(threshold1);
		Assertions.assertNotNull(threshold2);
				
		r1.editThreshold(threshold1, threshold2);
		
		assertTrue(threshold1.getLabel().equals("threshold"));
		assertTrue(threshold2.getLabel().equals("threshold")); 
		
		assertEquals(threshold2, r1.getInfo().get(1));	
		
		r1.editThreshold(threshold2, threshold1);

	}
	
	@Test
	final void testEditThresholdElse1() { //um deles nao tem threshold como label
		
		r1.editThreshold(threshold1, metric);
		assertTrue(threshold1.getLabel().equals("threshold"));
		assertFalse(metric.getLabel().equals("threshold"));
						
		assertNotEquals(metric, r1.getInfo().get(1));	
	}
	
	@Test
	final void testEditThresholdElse2() { //um deles nao tem threshold como label
		
		r1.editThreshold(metric,threshold1);
		assertTrue(threshold1.getLabel().equals("threshold"));
		assertFalse(metric.getLabel().equals("threshold"));
						
		assertEquals(metric, r1.getInfo().get(0));	
	}
	
	@Test
	final void testEditThresholdElse3() { //dois nao tem threshold como label 
		r1.editThreshold(metric, logic_operator);
		assertFalse(metric.getLabel().equals("threshold"));
		assertFalse(logic_operator.getLabel().equals("threshold"));
		
		assertNotEquals(logic_operator, r1.getInfo().get(0));	
	}
	
	@Test
	final void testGetObjectID() {
		assertEquals(0, r1.getObjectID(metric));
	}
	
	@Test
	final void testGetName() {
		assertEquals("name_test", r1.getName());
	}
	

	@Test
	final void testGetInfo() {
		assertEquals(rule_info, r1.getInfo());
	}
	
	@Test
	final void testGetType() {
		assertEquals("type_test", r1.getType());
	}
}
