package CodeSmellDetectionEvaluator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import metrics.Class;

import ana_rules.Rule;

public class ClassEvaluator {
	
	//Devia ser string so com nome???
	private Class classeval;
	private Map<String, EvaluatorType> codesmelssEvaluator;
	private List<MethodEvaluator> methodslst;
	
	public ClassEvaluator(Class classval)
	{
		this.classeval=classval;
		setCodesmelssEvaluator(new HashMap<String, EvaluatorType>());
		methodslst = new ArrayList<MethodEvaluator>();
	}
	
	public ClassEvaluator(Class classval,Map<String, EvaluatorType> codesmelssEvaluator )
	{
		this.classeval=classval;
		this.setCodesmelssEvaluator(codesmelssEvaluator);
		methodslst = new ArrayList<MethodEvaluator>();
	}
	
	public ClassEvaluator(Class classval,Map<String, EvaluatorType> codesmelssEvaluator, List<MethodEvaluator> methodslst )
	{
		this.classeval=classval;
		this.setCodesmelssEvaluator(codesmelssEvaluator);
		this.methodslst = methodslst;
	}
	
	public void addRuleAndVal (String rule, EvaluatorType evaluatorType)
	{
		getCodesmelssEvaluator().put(rule, evaluatorType);
	}
	
	public void setMethodList(List<MethodEvaluator> methodslst)
	{
		this.methodslst = methodslst;
	}
	
	public void addMethodList(MethodEvaluator method)
	{
		this.getMethodslst().add(method);
	}

	public Map<String, EvaluatorType> getCodesmelssEvaluator() {
		return codesmelssEvaluator;
	}

	public void setCodesmelssEvaluator(Map<String, EvaluatorType> codesmelssEvaluator) {
		this.codesmelssEvaluator = codesmelssEvaluator;
	}

	public Class getClasseval() {
		return classeval;
	}
	
	public EvaluatorType getEvalByName(String name)
	{
		return codesmelssEvaluator.get(name);
	}

	public List<MethodEvaluator> getMethodslst() {
		return methodslst;
	}
	
		

}
