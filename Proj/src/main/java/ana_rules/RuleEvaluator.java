package ana_rules;
import java.util.ArrayList;
import java.util.List;

import metrics.*;

public class RuleEvaluator {
	
	public static boolean EvalClass(Rule rule,metrics.Class classe) throws IllegalStateException {
		if(rule.getType().equals("class")) {
			return auxClass(rule.getInfo(), classe);
		}else {
			throw new IllegalArgumentException("invoking EvalClass on a Method rule");
		}

	}
	
	private static boolean auxClass(List<RuleObject> rules,metrics.Class classe) {
		if(rules.size()==3) {
			int metric=ClassMetric.getMetric(ClassMetric.valueOf(rules.get(0).getInfo()), classe);
			Comparator_Operator operator=Comparator_Operator.valueOf(rules.get(1).getInfo());
			int threshold=Integer.parseInt(rules.get(2).getInfo());
			return Comparator_Operator.compare(metric,operator ,threshold );
		}else {
			int metric=ClassMetric.getMetric(ClassMetric.valueOf(rules.get(0).getInfo()), classe);
			Comparator_Operator operator=Comparator_Operator.valueOf(rules.get(1).getInfo());
			int threshold=Integer.parseInt(rules.get(2).getInfo());
			List<RuleObject> newRules= rules.subList(4, rules.size());
			Logical_Operator Loperator=Logical_Operator.valueOf(rules.get(3).getInfo());
			return Logical_Operator.logicOperation(Comparator_Operator.compare(metric,operator ,threshold), Loperator, auxClass(newRules, classe));
		}
	}
	
	
	public static boolean EvalMethod(Rule rule,metrics.Method method) {
		if(rule.getType().equals("method")) {
			return auxMethod(rule.getInfo(), method);
		}else {
			throw new IllegalArgumentException("invoking EvalClass on a Method rule");
		}
	}

	private static boolean auxMethod(List<RuleObject> rules, Method method) {
		if(rules.size()==3) {
			int metric=MethodMetric.getMetric(MethodMetric.valueOf(rules.get(rules.size()-3).getInfo()), method);
			Comparator_Operator operator=Comparator_Operator.valueOf(rules.get(rules.size()-2).getInfo());
			int threshold=Integer.parseInt(rules.get(rules.size()-1).getInfo());
			return Comparator_Operator.compare(metric,operator ,threshold );
		}else {
			int metric=MethodMetric.getMetric(MethodMetric.valueOf(rules.get(rules.size()-3).getInfo()), method);
			Comparator_Operator operator=Comparator_Operator.valueOf(rules.get(rules.size()-2).getInfo());
			int threshold=Integer.parseInt(rules.get(rules.size()-1).getInfo());
			List<RuleObject> newRules= rules.subList(0, rules.size()-4);
			Logical_Operator Loperator=Logical_Operator.valueOf(rules.get(rules.size()-4).getInfo());
			return Logical_Operator.logicOperation(Comparator_Operator.compare(metric,operator ,threshold), Loperator, auxMethod(newRules, method));
		}
	}
	

}
