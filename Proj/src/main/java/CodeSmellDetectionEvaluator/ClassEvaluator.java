package CodeSmellDetectionEvaluator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import metrics.Class;

import ana_rules.Rule;

/**
 * 
 * Represents a class to evaluate
 *
 */
public class ClassEvaluator {

	// Devia ser string so com nome???
	private Class classeval;
	private Map<String, EvaluatorType> codesmelssEvaluator;
	private List<MethodEvaluator> methodslst;

	/**
	 * Initializes ClassEvaluator
	 * 
	 * @param classval class to evaluate
	 */
	public ClassEvaluator(Class classval) {
		this.classeval = classval;
		setCodesmelssEvaluator(new HashMap<String, EvaluatorType>());
		methodslst = new ArrayList<MethodEvaluator>();
	}

	/**
	 * Initializes ClassEvaluator
	 * 
	 * @param classval class to evaluate
	 * @param codesmelssEvaluator code smell evaluator
	 */
	public ClassEvaluator(Class classval, Map<String, EvaluatorType> codesmelssEvaluator) {
		this.classeval = classval;
		this.setCodesmelssEvaluator(codesmelssEvaluator);
		methodslst = new ArrayList<MethodEvaluator>();
	}

	/**
	 * Initializes ClassEvaluator
	 * 
	 * @param classval class to evaluate
	 * @param codesmelssEvaluator code smell evaluator
	 * @param methodslst list of methods
	 */
	public ClassEvaluator(Class classval, Map<String, EvaluatorType> codesmelssEvaluator,
			List<MethodEvaluator> methodslst) {
		this.classeval = classval;
		this.setCodesmelssEvaluator(codesmelssEvaluator);
		this.methodslst = methodslst;
	}
	
	/**
	 * Adds a rule and a evaluator type to the code smell evaluator
	 * @param rule
	 * @param evaluatorType evaluator type
	 */
	public void addRuleAndVal(String rule, EvaluatorType evaluatorType) {
		getCodesmelssEvaluator().put(rule, evaluatorType);
	}

	/**
	 * Sets the methods list
	 * @param methodslst methods list
	 */
	public void setMethodList(List<MethodEvaluator> methodslst) {
		this.methodslst = methodslst;
	}

	/**
	 * Adds a method to the method list
	 * @param method to add
	 */
	public void addMethodList(MethodEvaluator method) {
		this.getMethodslst().add(method);
	}

	/**
	 * Gets the code smell evaluator
	 * @return the code smell Evaluator
	 */
	public Map<String, EvaluatorType> getCodesmelssEvaluator() {
		return codesmelssEvaluator;
	}

	/**
	 * Sets the code smell evaluator
	 * @param codesmellsEvaluator the code smell Evaluator
	 */
	public void setCodesmelssEvaluator(Map<String, EvaluatorType> codesmellsEvaluator) {
		this.codesmelssEvaluator = codesmellsEvaluator;
	}

	/**
	 * Gets the class to evaluate
	 * @return the class to evaluate
	 */
	public Class getClasseval() {
		return classeval;
	}

	/**
	 * Gets the evaluator type given the name
	 * @param name
	 * @return the evaluator type given the name
	 */
	public EvaluatorType getEvalByName(String name) {
		return codesmelssEvaluator.get(name);
	}
	
	/**
	 * Gets a list of the methods
	 * @return a list of the methods
	 */
	public List<MethodEvaluator> getMethodslst() {
		return methodslst;
	}

}
