package CodeSmellDetectionEvaluator;

import java.util.HashMap;
import java.util.Map;

import metrics.Method;
import rules.Rule;

/**
 * 
 * Represents a method to evaluate
 *
 */
public class MethodEvaluator {

	private Method methodEval;
	private Map<String, EvaluatorType> codesmellsEvaluator;

	/**
	 * Creates MethodEvaluator
	 * 
	 * @param methodEval method to evaluate
	 */
	public MethodEvaluator(Method methodEval) {
		this.methodEval = methodEval;
		setCodesmelssEvaluator(new HashMap<String, EvaluatorType>());
	}

	/**
	 * Creates MethodEvaluator
	 * 
	 * @param methodEval          method to evaluate
	 * @param codesmellsEvaluator code smell evaluator
	 */
	public MethodEvaluator(Method methodEval, Map<String, EvaluatorType> codesmellsEvaluator) {
		this.methodEval = methodEval;
		this.setCodesmelssEvaluator(codesmellsEvaluator);
	}

	/**
	 * Adds a rule and a evaluator type to the code smell evaluator
	 * 
	 * @param rule
	 * @param evaluatorType evaluator type
	 */
	public void addRuleAndVal(String rule, EvaluatorType evaluatorType) {
		getCodesmelssEvaluator().put(rule, evaluatorType);
	}

	/**
	 * Gets the code smell evaluator
	 * 
	 * @return the code smell Evaluator
	 */
	public Map<String, EvaluatorType> getCodesmelssEvaluator() {
		return codesmellsEvaluator;
	}

	/**
	 * Sets the code smell evaluator
	 * 
	 * @param codesmelssEvaluator the code smell Evaluator
	 */
	public void setCodesmelssEvaluator(Map<String, EvaluatorType> codesmelssEvaluator) {
		this.codesmellsEvaluator = codesmelssEvaluator;
	}

	/**
	 * Gets the evaluator type given the name
	 * 
	 * @param name
	 * @return the evaluator type given the name
	 */
	public EvaluatorType getEvalByName(String name) {
		return getCodesmelssEvaluator().get(name);
	}

	/**
	 * Gets the method to evaluate
	 * 
	 * @return the method to evaluate
	 */
	public Method getMethodEval() {
		return methodEval;
	}
}
