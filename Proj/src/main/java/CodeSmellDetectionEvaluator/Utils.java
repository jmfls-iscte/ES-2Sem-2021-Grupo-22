package CodeSmellDetectionEvaluator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.functors.SwitchClosure;

import ana_rules.ClassMetric;
import metrics.Class;
import metrics.Method;
import metrics.Package;

public class Utils {

	public static Package getPackagebyName(String name, List<Package> packagelst) {
		for (Package p : packagelst) {
			if (p.getName_Package().equals(name))
				return p;
		}
		return null;
	}

	public static Class getClassbyName(String name, List<Class> classlst) {
		for (Class c : classlst) {
			if (c.getName_Class().equals(name))
				return c;
		}
		return null;
	}

	public static Method getMethodbyName(String name, List<Method> methodlst) {
		for (Method m : methodlst) {
			if (m.getName_method().equals(name))
				return m;
		}
		return null;
	}
	
	
	public static Map<EvaluatorType, Integer> getClassificationTotal(Map<EvaluatorType, Integer> mapa,
			List<PackageEvaluator> packagelst) {

		for (PackageEvaluator p : packagelst) {
				List<ClassEvaluator> classlst = p.getClasslst();
				for (ClassEvaluator c : classlst) {
					mapa = getClassificationClass(mapa, c);
				}
			
		}

		return mapa;
	}
	

	public static Map<EvaluatorType, Integer> getClassificationPackage(Map<EvaluatorType, Integer> mapa,
			List<PackageEvaluator> packagelst, String packageName) {

		for (PackageEvaluator p : packagelst) {
			if (p.getName().equals(packageName)) {
				List<ClassEvaluator> classlst = p.getClasslst();
				for (ClassEvaluator c : classlst) {
					mapa = getClassificationClass(mapa, c);
				}
			}
		}

		return mapa;
	}

	public static Map<EvaluatorType, Integer> getClassificationClass(Map<EvaluatorType, Integer> map,
			ClassEvaluator classeval) {

		Map<String, EvaluatorType> evalclass = classeval.getCodesmelssEvaluator();
		for (String s : evalclass.keySet()) {
			map = mapIncrement(map, evalclass.get(s));
		}

		for (MethodEvaluator m : classeval.getMethodslst()) {
			Map<String, EvaluatorType> evalmethod = m.getCodesmelssEvaluator();
			for (String s2 : evalmethod.keySet()) {
				map = mapIncrement(map, evalmethod.get(s2));
			}
		}

		return map;
	}

	public static Map<EvaluatorType, Integer> getClassificationClassRule(Map<EvaluatorType, Integer> mapa,
			ClassEvaluator classeval, String ruleName) {

		Map<String, EvaluatorType> evalclass = classeval.getCodesmelssEvaluator();
		if (classeval.getCodesmelssEvaluator().containsKey(ruleName))
			mapa = mapIncrement(mapa, classeval.getCodesmelssEvaluator().get(ruleName));
		else {
			List<MethodEvaluator> methodlst = classeval.getMethodslst();
			for (MethodEvaluator m : methodlst) {
				if (m.getCodesmelssEvaluator().containsKey(ruleName))
					mapa = mapIncrement(mapa, m.getCodesmelssEvaluator().get(ruleName));
			}
		}

		return mapa;
	}

	public static Map<EvaluatorType, Integer> getClassificationRule(Map<EvaluatorType, Integer> mapa,
			PackageEvaluator packageval, String ruleName) {

		List<ClassEvaluator> classlst = packageval.getClasslst();
		for (ClassEvaluator c : classlst) {

			if (c.getCodesmelssEvaluator().containsKey(ruleName))
				mapa = mapIncrement(mapa, c.getCodesmelssEvaluator().get(ruleName));
			else {
				List<MethodEvaluator> methodlst = c.getMethodslst();
				for (MethodEvaluator m : methodlst) {
					if (m.getCodesmelssEvaluator().containsKey(ruleName))
						mapa = mapIncrement(mapa, m.getCodesmelssEvaluator().get(ruleName));
				}

			}
		}

		return mapa;

	}

	private static Map<EvaluatorType, Integer> mapIncrement(Map<EvaluatorType, Integer> map, EvaluatorType evaltype) {
		map.replace(evaltype, map.get(evaltype) + 1);
		return map;
	}

}
