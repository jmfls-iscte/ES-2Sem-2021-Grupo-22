package tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ana_rules.Rule;
import ana_rules.RuleObject;
import ana_rules.SaveLoadRule;

class SaveLoadRuleTest {
	
	static SaveLoadRule svl1;
	static ArrayList<Rule> array_rules = new ArrayList<Rule>();
	static ArrayList<RuleObject> array_ruleobject = new ArrayList<RuleObject>();
	static Rule r1 = new Rule("test", "test", array_ruleobject);

	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		svl1 = new SaveLoadRule();
		array_rules.add(r1);
		
	}
	
	@Test
	void testSaveRules() {
		svl1.SaveRules(array_rules, "test");
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
		assertNotNull(svl1.LoadRules("test"));
	}

	@Test
	void testLoadRulesException1() {
		assertThrows(IllegalArgumentException.class, () -> svl1.LoadRules(""));	
	}
	
	@Test
	void testLoadRulesException2() {
		assertThrows(IllegalArgumentException.class, () -> svl1.LoadRules(null));	
	}
	/*
	@Test
	void testLoadRulesException3() {
		assertThrows(FileNotFoundException.class, () -> svl1.LoadRules(".."));	
	}
*/
}
