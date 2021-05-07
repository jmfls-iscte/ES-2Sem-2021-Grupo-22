package CodeSmellDetectionEvaluator;

import java.util.HashMap;
import java.util.Map;

import metrics.Method;
import rules.Rule;

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
	
	public EvaluatorType getEvalByName(String name)
	{
		return getCodesmelssEvaluator().get(name);
	}

	public Method getMethodEval() {
		return methodEval;
	}
}
