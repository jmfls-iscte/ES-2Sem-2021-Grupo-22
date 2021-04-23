package CodeSmellDetectionEvaluator;

import java.util.HashMap;
import java.util.Map;

import ana_rules.Rule;
import metrics.Method;

public class MethodEvaluator {

	private Method methodEval;
	private Map<String, EvaluatorType> codesmelssEvaluator;
	
	
	public MethodEvaluator(Method methodEval)
	{
		this.methodEval = methodEval;
		setCodesmelssEvaluator(new HashMap<String, EvaluatorType>());
	}
	
	public MethodEvaluator(Method methodEval,Map<String, EvaluatorType> codesmelssEvaluator)
	{
		this.methodEval = methodEval;
		this.setCodesmelssEvaluator(codesmelssEvaluator);
	}
	
	public void addRuleAndVal (String rule, EvaluatorType evaluatorType)
	{
		getCodesmelssEvaluator().put(rule, evaluatorType);
	}

	public Map<String, EvaluatorType> getCodesmelssEvaluator() {
		return codesmelssEvaluator;
	}

	public void setCodesmelssEvaluator(Map<String, EvaluatorType> codesmelssEvaluator) {
		this.codesmelssEvaluator = codesmelssEvaluator;
	}
}
