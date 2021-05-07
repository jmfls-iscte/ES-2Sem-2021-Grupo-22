package tests;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ana_rules.*;
class RuleTest {
	
	static Rule r1;
	static Rule r2; 
	static Rule r3; 
	static Rule r4; 
	static Rule r5; 
	
	static ArrayList<RuleObject> rule_info = new ArrayList<>();
	static ArrayList<RuleObject> rule_info_1 = new ArrayList<>();
	static ArrayList<RuleObject> rule_info_2 = new ArrayList<>();
	static ArrayList<RuleObject> rule_info_3 = new ArrayList<>();
	static ArrayList<RuleObject> rule_info_5 = new ArrayList<>();
	
	//ruleobjects r1
	static RuleObject metric;
	static RuleObject threshold1;
	static RuleObject threshold_test;

	//ruleobjects r2
	static RuleObject metric_2;
	static RuleObject comparator_2;
	static RuleObject threshold1_2;
	static RuleObject logical_operator;
	static RuleObject metric2_2;
	static RuleObject comparator2_2;
	static RuleObject threshold2_2;
	
	//ruleobjects r3
	static RuleObject metric_3;
	static RuleObject comparator_3;
	static RuleObject threshold1_3;
	
	//rulobjects r5
	static RuleObject metric_5_fail;
		
	static ArrayList<Rule> arrayrl = new ArrayList<>();

	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		//set r1
		r1 = new Rule("name_test", "class", rule_info);
		
		metric = new RuleObject("LOC_METHOD", "METHODMETRIC");
		threshold1 = new RuleObject("10", "THRESHOLD");
		threshold_test = new RuleObject("20", "THRESHOLD");
		
		rule_info_1.add(metric);
		rule_info_1.add(threshold1);

		r1.setInfo(rule_info_1);
		
		//set r2
		metric_2 = new RuleObject("LOC_METHOD", "METHODMETRIC");
		comparator_2 = new RuleObject("GREATER", "COMPARISON_OPERATOR");
		threshold1_2 = new RuleObject("2", "THRESHOLD");
		logical_operator = new RuleObject("AND", "LOGIC_OPERATOR");
		metric2_2 = new RuleObject("LOC_METHOD", "METHODMETRIC");
		comparator_2 = new RuleObject("LESS", "COMPARISON_OPERATOR");
		threshold1_2 = new RuleObject("40", "THRESHOLD");
		
		rule_info_2.add(metric_2);
		rule_info_2.add(comparator_2);
		rule_info_2.add(threshold1_2);
		rule_info_2.add(logical_operator);
		rule_info_2.add(metric2_2);
		rule_info_2.add(comparator_2);
		rule_info_2.add(threshold1_2);
		
		r2 = new Rule("name_test", "method", rule_info_2, true);
		
		//set r3
		metric_3 = new RuleObject("NOM_CLASS", "CLASSMETRIC");
		comparator_3 = new RuleObject("GREATER", "COMPARISON_OPERATOR");
		threshold1_3 = new RuleObject("2", "THRESHOLD");
				
		rule_info_3.add(metric_3);
		rule_info_3.add(comparator_3);
		rule_info_3.add(threshold1_3);
		
		r3 = new Rule("name_test", "class", rule_info_3, true);
		
		//set r5
		metric_5_fail = new RuleObject("NOM_CLASS", "CLASSMETRIC"); //0
		rule_info_5.add(metric_5_fail);
				
	}
	
	@Test
	 void ruleTest() {
		Assertions.assertNotNull(r1);
		assertEquals("name_test", r1.getName());
		assertEquals("class", r1.getType());
	}
	
	@Test
	 void ruleTest2() {
		Assertions.assertNotNull(r2);
		assertEquals("name_test", r2.getName());
		assertEquals("method", r2.getType());
	}
		
	@Test
	 void ruleTest3() {
		Assertions.assertNotNull(r3);
		assertEquals("name_test", r3.getName());
		assertEquals("class", r3.getType());
	}

	@Test
	 void ruleTest4() {
		assertThrows(IllegalArgumentException.class, () -> r4 = new Rule("teste", "type_test", rule_info, true));		
	}
	
	@Test
	 void ruleTest5() {
		
		assertThrows(IllegalArgumentException.class, () -> r5 = new Rule("name_test", "method", rule_info_5, true));	
		
		rule_info_5.add(new RuleObject("1","THRESHOLD")); //1
		assertThrows(IllegalArgumentException.class, () -> r5 = new Rule("name_test", "class", rule_info_5, true));	
		
		
		rule_info_5.set(1, comparator_2);
		rule_info_5.add(new RuleObject("LOC_METHOD","METHODMETRIC")); //2
		assertThrows(IllegalArgumentException.class, () ->  r5 = new Rule("name_test", "class", rule_info_5, true));	
	
		rule_info_5.set(2, threshold1_2);
		rule_info_5.add(new RuleObject("LOC_METHOD","METHODMETRIC")); //3
		assertThrows(IllegalArgumentException.class, () -> r5 = new Rule("name_test", "class", rule_info_5, true));	
		
		rule_info_5.set(3, logical_operator);
		assertThrows(IllegalArgumentException.class, () -> r5 = new Rule("name_test", "class", rule_info_5, true));	
	}
	
	@Test
	 void editThresholdTest() {
		Assertions.assertNotNull(threshold1);
		Assertions.assertNotNull(threshold_test);
		
		r1.editThreshold(threshold1, threshold_test);
		assertEquals(threshold_test.getInfo(), r1.getInfo().get(1).getInfo());	
	}
	
	@Test
	 void editThresholdTestFail() {
		Assertions.assertNotNull(metric);
		
		r1.editThreshold(threshold1, metric);
		assertEquals(threshold1.getInfo(), r1.getInfo().get(1).getInfo());	
	}
	
	@Test
	 void editThresholdTestFail2() {
		r1.editThreshold(metric, metric);
		assertEquals(metric.getInfo(), r1.getInfo().get(0).getInfo());	
	}
	
	@Test
	 void editThresholdTestFail3() {
		r1.editThreshold(metric, threshold1);
		assertEquals(metric.getInfo(), r1.getInfo().get(0).getInfo());	
	}
	@Test
	 void testtoString() {
		assertNotNull(r1.toString());		
		assertNotNull(r2.toString());
	}
	
	@Test
	 void testgetRuleFromList() {
		arrayrl.add(r2);
		assertEquals(r2, Rule.getRuleFromList(arrayrl,"name_test"));
		assertNull(Rule.getRuleFromList(arrayrl,"error"));
	}
	
	@Test
	 void testvalueOfLabel() {
		assertEquals(Comparator_Operator.GREATER, Comparator_Operator.valueOfLabel(">"));
		assertNull(Comparator_Operator.valueOfLabel("error"));

	}
	 
}
	
	
	