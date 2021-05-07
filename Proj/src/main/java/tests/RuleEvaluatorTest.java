package tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ana_rules.Rule;
import ana_rules.RuleEvaluator;
import ana_rules.RuleObject;
import metrics.Package;
import metrics.Class;
import metrics.Method;


class RuleEvaluatorTest {
	
	static RuleEvaluator re;
	static List<Rule> array_r1 = new ArrayList<Rule>();
	static List<Package> array_p1 = new ArrayList<Package>();
	
	static RuleEvaluator re2;
	static List<Rule> array_r2 = new ArrayList<Rule>();
	
	static Package p1;
	static Class c1;
	
	static Rule r1;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		re = new RuleEvaluator();
		re2 = new RuleEvaluator();
						
		p1 = new Package();
		c1 = new Class(); 
		c1.addMethod(new Method());
		p1.addClass(c1);
		array_p1.add(p1);
		
		r1 = new Rule("test", "test", new ArrayList<RuleObject>());
		array_r2.add(r1);
	}
	
	@Test
	void testrunCodeSmells() {
		assertNotNull(re.BASERULES());
		re.runCodeSmells(re.BASERULES(), array_p1); 
		assertNotNull(re);
		
		re2.runCodeSmells(array_r2, array_p1); 
		assertNotNull(re2);
	}	
	
}
