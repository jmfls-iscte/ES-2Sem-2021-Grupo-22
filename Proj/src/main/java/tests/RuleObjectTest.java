package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ana_rules.RuleObject;

class RuleObjectTest {
	static RuleObject metric;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception{		
		metric = new RuleObject("LOC_method", "metric");	
	}
	
	@Test
	final void testRuleObject() {
		Assertions.assertNotNull(metric);
		assertEquals("LOC_method", metric.getInfo());
		assertEquals("metric", metric.getLabel()); 
	}
	
	@Test
	final void testGetInfo() {
		assertEquals("LOC_method", metric.getInfo());
	}
	
	@Test
	final void testGetLabel() {
		assertEquals("metric", metric.getLabel());
	}

}
