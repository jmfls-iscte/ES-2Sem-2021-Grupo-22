package tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import rules.Rule;
import rules.RuleObject;
import rules.SaveLoadRule;

class SaveLoadRuleTest {
	
	static SaveLoadRule svl1;
	static ArrayList<Rule> array_rules = new ArrayList<Rule>();
	static ArrayList<RuleObject> array_ruleobject = new ArrayList<RuleObject>();
	static Rule r1 = new Rule("test", "test", array_ruleobject);
	
	static RuleObject ro1;
	static RuleObject ro2;
	static RuleObject ro3;
	
	static String path = "/Users/anantunes0/rule_test.txt"; //mudar o path para o ficheiro certo 

	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		svl1 = new SaveLoadRule();
		array_rules.add(r1);
		
		ro1 = new RuleObject("NOM_CLASS","CLASSMETRIC");
		ro2 = new RuleObject("EQUALS","COMPARISON_OPERATOR");
		ro3 = new RuleObject("1","THRESHOLD");	
		
		array_ruleobject.add(ro1);
		array_ruleobject.add(ro2);
		array_ruleobject.add(ro3);
	}
	
	@Test
	void testSaveRules() {
		svl1.SaveRules(array_rules, path);
		assertNotNull(svl1);
	}
	
	@Test
	void testSaveRulesException1() {
		assertThrows(IllegalArgumentException.class, () -> svl1.SaveRules(null, ""));	
	}
	
	@Test
	void testSaveRulesException2() {
		assertThrows(IllegalArgumentException.class, () -> svl1.SaveRules(null, null));	
	}
	
	@Test
	void testLoadRules() {
		assertEquals(1, svl1.LoadRules(path).size());
	}

	@Test
	void testLoadRulesException1() {
		assertThrows(IllegalArgumentException.class, () -> svl1.LoadRules(""));	
	}
	
	@Test
	void testLoadRulesException2() {
		assertThrows(IllegalArgumentException.class, () -> svl1.LoadRules(null));	
	}

}
