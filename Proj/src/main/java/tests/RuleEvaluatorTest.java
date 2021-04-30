package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ana_rules.Rule;
import ana_rules.RuleEvaluator;
import metrics.Package;


class RuleEvaluatorTest {
	
	static RuleEvaluator re1;
	static List<Rule> array_r1 = new ArrayList<Rule>();
	static List<Package> array_p1 = new ArrayList<Package>();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		re1 = new RuleEvaluator();
	}
	
	
	@Test
	void testrunCodeSmells() {
		re1.runCodeSmells(array_r1, array_p1);
	}

}
