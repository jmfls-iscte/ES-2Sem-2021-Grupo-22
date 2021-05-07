package ana_rules;

import java.util.ArrayList;
import java.util.List;

import metrics.*;
import metrics.Class;
import metrics.Package;

/**
 * 
 * Represents a rule to evaluate
 *
 */
public class RuleEvaluator {

	/**
	 * Verifies if the rule is of the type class and calls auxClass
	 * @param rule
	 * @param classe
	 * @return boolean based on auxClass
	 * @throws IllegalStateException
	 */
	private static boolean EvalClass(Rule rule, metrics.Class classe) throws IllegalStateException {
		if (rule.getType().equals("class")) {
			return auxClass(rule.getInfo(), classe);
		} else {
			throw new IllegalArgumentException("invoking EvalClass on a Method rule");
		}

	}

	/**
	 * Creates the metrics of the given class
	 * @param rules list of rules
	 * @param classe class
	 * @return true if the rule is valid
	 */
	private static boolean auxClass(List<RuleObject> rules, metrics.Class classe) {
		if (rules.size() == 3) {
			int metric = ClassMetric.getMetric(ClassMetric.valueOf(rules.get(0).getInfo()), classe);
			Comparator_Operator operator = Comparator_Operator.valueOf(rules.get(1).getInfo());
			int threshold = Integer.parseInt(rules.get(2).getInfo());
			return Comparator_Operator.compare(metric, operator, threshold);
		} else {
			int metric = ClassMetric.getMetric(ClassMetric.valueOf(rules.get(0).getInfo()), classe);
			Comparator_Operator operator = Comparator_Operator.valueOf(rules.get(1).getInfo());
			int threshold = Integer.parseInt(rules.get(2).getInfo());
			List<RuleObject> newRules = rules.subList(4, rules.size());
			Logical_Operator Loperator = Logical_Operator.valueOf(rules.get(3).getInfo());
			return Logical_Operator.logicOperation(Comparator_Operator.compare(metric, operator, threshold), Loperator,
					auxClass(newRules, classe));
		}
	}

	/**
	 * Verifies if the rule is of the type method and calls auxMethod
	 * @param rule
	 * @param method
	 * @return boolean based on auxMethod
	 * @throws IllegalStateException
	 */
	private static boolean EvalMethod(Rule rule, metrics.Method method) {
		if (rule.getType().equals("method")) {
			return auxMethod(rule.getInfo(), method);
		} else {
			throw new IllegalArgumentException("invoking EvalClass on a Method rule");
		}
	}

	/**
	 * Creates the metrics of the given method
	 * @param rules list of rules
	 * @param method
	 * @return true if the rule is valid
	 */
	private static boolean auxMethod(List<RuleObject> rules, Method method) {
		if (rules.size() == 3) {
			int metric = MethodMetric.getMetric(MethodMetric.valueOf(rules.get(rules.size() - 3).getInfo()), method);
			Comparator_Operator operator = Comparator_Operator.valueOf(rules.get(rules.size() - 2).getInfo());
			int threshold = Integer.parseInt(rules.get(rules.size() - 1).getInfo());
			return Comparator_Operator.compare(metric, operator, threshold);
		} else {
			int metric = MethodMetric.getMetric(MethodMetric.valueOf(rules.get(rules.size() - 3).getInfo()), method);
			Comparator_Operator operator = Comparator_Operator.valueOf(rules.get(rules.size() - 2).getInfo());
			int threshold = Integer.parseInt(rules.get(rules.size() - 1).getInfo());
			List<RuleObject> newRules = rules.subList(0, rules.size() - 4);
			Logical_Operator Loperator = Logical_Operator.valueOf(rules.get(rules.size() - 4).getInfo());
			return Logical_Operator.logicOperation(Comparator_Operator.compare(metric, operator, threshold), Loperator,
					auxMethod(newRules, method));
		}
	}

	/**
	 * Adds code smells to every class and method off the package list
	 * @param allrules all rules
	 * @param pacotes list of packages
	 */
	public static void runCodeSmells(List<Rule> allrules, List<Package> pacotes) {
		List<Rule> classrule = splitRules(allrules).get(0);
		List<Rule> methodrule =splitRules(allrules).get(1);
		List<metrics.Class> classlst= allClass(pacotes);
		List<metrics.Method> methodlst=allMethod(classlst);
		CsClass(classrule, classlst);
		CsMethod(methodrule, methodlst);	
	}

	/**
	 * Organizes all rules into class rules and then method rules
	 * @param allrules all rules
	 * @return organized list of all rules
	 */
	private static List<List<Rule>> splitRules(List<Rule> allrules) {
		List<Rule> classrules = new ArrayList<Rule>();
		List<Rule> methodrules = new ArrayList<Rule>();
		for (Rule x : allrules) {
			if (x.getType().equals("class")) {
				classrules.add(x);
			} else if (x.getType().equals("method")) {
				methodrules.add(x);
			}
		}
		List<List<Rule>> r = new ArrayList<List<Rule>>();
		r.add(classrules);
		r.add(methodrules);
		return r;
	}

	/**
	 * Adds a code smell to a class and a boolean if representing if the code smell is present
	 * @param classrule class rule
	 * @param classlst class list
	 */
	private static void CsClass(List<Rule> classrule, List<metrics.Class> classlst) {
		for (metrics.Class x : classlst) {
			for (Rule y : classrule) {
				try {
					boolean cs = EvalClass(y, x);
					x.addSmell(y.getName(), cs);
				} catch (Exception e) {
					// isto nunca vai acontecer
				}
			}
		}
	}

	/**
	 * Adds a code smell to a method and a boolean if representing if the code smell is present
	 * @param methodRule method rule
	 * @param methodlst method list
	 */
	private static void CsMethod(List<Rule> methodRule, List<metrics.Method> methodlst) {
		for (metrics.Method x : methodlst) {
			for (Rule y : methodRule) {
				try {
					boolean cs = EvalMethod(y, x);
					x.addSmell(y.getName(), cs);
				} catch (Exception e) {
					// amen
				}
			}
		}
	}

	
	/**
	 * Gets all classes in a list of packages
	 * @param pacotes list of packages
	 * @return all classes in a list of packages
	 */
	private static List<metrics.Class> allClass(List<Package> pacotes){
		List<metrics.Class> r= new ArrayList<metrics.Class>();
		for( Package x: pacotes) {
			r.addAll(x.getClass_list());
			}
		return r;
	}
	
	/**
	 * Gets all methods in a list of classes
	 * @param classes list of classes
	 * @return all methods in a list of classes
	 */
	private static List<metrics.Method> allMethod(List<metrics.Class> classes){
		List<metrics.Method> r= new ArrayList<Method>();
		for(metrics.Class x: classes) {
			r.addAll(x.getMethod_list());
		}
		return r;
	}

	/**
	 * Creates the base rules
	 * @return list of the base rules
	 */
	public static List<Rule> BASERULES() {
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
		
		try {
			Rule longmethod = new Rule("is_Long_Method", "method", ruleinfo, true);
			Rule Is_God_Class= new Rule("is_God_Class" ,"class",ruleinfo2,true);
			r.add(Is_God_Class);
			r.add(longmethod);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;

	}

}
