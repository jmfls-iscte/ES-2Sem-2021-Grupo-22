package CodeSmellDetectionEvaluator;

import java.util.HashMap;
import java.util.Map;

import ana_rules.Rule;
import metrics.Method;

public class MethodEvaluator {

	//Devia ser uma string com nome??
	private Method methodEval;
	private Map<Rule, EvaluatorType> codesmelssEvaluator;
	
	
	public MethodEvaluator(Method methodEval)
	{
		this.methodEval = methodEval;
		codesmelssEvaluator = new HashMap<Rule, EvaluatorType>();
	}
	
	public MethodEvaluator(Method methodEval,Map<Rule, EvaluatorType> codesmelssEvaluator)
	{
		this.methodEval = methodEval;
		this.codesmelssEvaluator = codesmelssEvaluator;
	}
	
	public void addRuleAndVal (Rule rule, EvaluatorType evaluatorType)
	{
		codesmelssEvaluator.put(rule, evaluatorType);
	}
}
