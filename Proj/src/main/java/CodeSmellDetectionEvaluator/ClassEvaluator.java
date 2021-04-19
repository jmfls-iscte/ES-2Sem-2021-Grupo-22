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
	private Map<Rule, EvaluatorType> codesmelssEvaluator;
	private List<MethodEvaluator> methodslst;
	
	public ClassEvaluator(Class classval)
	{
		this.classeval=classval;
		codesmelssEvaluator = new HashMap<Rule, EvaluatorType>();
		methodslst = new ArrayList<MethodEvaluator>();
	}
	
	public ClassEvaluator(Class classval,Map<Rule, EvaluatorType> codesmelssEvaluator )
	{
		this.classeval=classval;
		this.codesmelssEvaluator = codesmelssEvaluator;
		methodslst = new ArrayList<MethodEvaluator>();
	}
	
	public ClassEvaluator(Class classval,Map<Rule, EvaluatorType> codesmelssEvaluator, List<MethodEvaluator> methodslst )
	{
		this.classeval=classval;
		this.codesmelssEvaluator = codesmelssEvaluator;
		this.methodslst = methodslst;
	}
	
	public void addRuleAndVal (Rule rule, EvaluatorType evaluatorType)
	{
		codesmelssEvaluator.put(rule, evaluatorType);
	}
	
	public void setMethodList(List<MethodEvaluator> methodslst)
	{
		this.methodslst = methodslst;
	}
	
	public void addMethodList(MethodEvaluator methodslst)
	{
		this.methodslst.add(methodslst);
	}
	
		

}
