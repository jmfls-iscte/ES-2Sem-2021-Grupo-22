package CodeSmellDetectionEvaluator;

import java.util.HashMap;
import java.util.Map;

import ana_rules.Rule;

public class RulesEvaluator {
	
	private Map<Rule, EvaluatorType> rulesEvaluator;
	
	public RulesEvaluator()
	{
		rulesEvaluator = new HashMap<Rule, EvaluatorType>();
	}
	
	public void ruleAndType(Rule rule , EvaluatorType type)
	{
		rulesEvaluator.putIfAbsent(rule, type);
	}

}
