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

	@Test
	void testException() {
		/*
		List<Rule> r = new ArrayList<Rule>();
		RuleObject obj1 = new RuleObject("LOC_METHOD", "METHODMETRIC");
		RuleObject obj2 = new RuleObject("GREATER", "COMPARISON_OPERATOR");
		RuleObject obj3 = new RuleObject("50", "THRESHOLD");
		RuleObject obj4 = new RuleObject("AND", "LOGIC_OPERATOR");
		RuleObject obj5 = new RuleObject("CYCLO_METHOD", "METHODMETRIC");
		RuleObject obj6 = new RuleObject("GREATER", "COMPARISON_OPERATOR");
		RuleObject obj7 = new RuleObject("10", "THRESHOLD");
		ArrayList<RuleObject> ruleinfo = new ArrayList<RuleObject>();
		ruleinfo.add(obj1);
		ruleinfo.add(obj2);
		ruleinfo.add(obj3);
		ruleinfo.add(obj4);
		ruleinfo.add(obj5);
		ruleinfo.add(obj6);
		ruleinfo.add(obj7);
		
		RuleObject obj21 = new RuleObject("WMC_CLASS", "CLASSMETRIC");
		RuleObject obj22 = new RuleObject("GREATER", "COMPARISON_OPERATOR");
		RuleObject obj23 = new RuleObject("50", "THRESHOLD");
		RuleObject obj24 = new RuleObject("OR", "LOGIC_OPERATOR");
		RuleObject obj25 = new RuleObject("NOM_CLASS", "CLASSMETRIC");
		RuleObject obj26 = new RuleObject("GREATER", "COMPARISON_OPERATOR");
		RuleObject obj27 = new RuleObject("10", "THRESHOLD");
		ArrayList<RuleObject> ruleinfo2 = new ArrayList<RuleObject>();
		ruleinfo2.add(obj21);
		ruleinfo2.add(obj22);
		ruleinfo2.add(obj23);
		ruleinfo2.add(obj24);
		ruleinfo2.add(obj25);
		ruleinfo2.add(obj26);
		ruleinfo2.add(obj27);
		*/
		
		/*
		public static void runCodeSmells(List<Rule> allrules, List<Package> pacotes) {
			List<Rule> classrule = splitRules(allrules).get(0);
			List<Rule> methodrule =splitRules(allrules).get(1);
			List<metrics.Class> classlst= allClass(pacotes);
			List<metrics.Method> methodlst=allMethod(classlst);
			CsClass(classrule, classlst);
			CsMethod(methodrule, methodlst);	
		}
		*/
		
		

	}
	
}
