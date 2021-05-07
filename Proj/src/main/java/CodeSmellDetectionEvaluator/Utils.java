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

	/**
	 * Gets a package given its name
	 * @param name
	 * @param packagelst package list
	 * @return a package given its name
	 */
	public static Package getPackagebyName(String name, List<Package> packagelst) {
		for (Package p : packagelst) {
			if (p.getName_Package().equals(name))
				return p;
		}
		return null;
	}

	/**
	 * Gets a class given its name
	 * @param name
	 * @param classlst class list
	 * @return a class given its name
	 */
	public static Class getClassbyName(String name, List<Class> classlst) {
		for (Class c : classlst) {
			if (c.getName_Class().equals(name))
				return c;
		}
		return null;
	}

	/**
	 * Gets a method given its name
	 * @param name
	 * @param methodlst method list
	 * @return a method given its name
	 */
	public static Method getMethodbyName(String name, List<Method> methodlst) {
		for (Method m : methodlst) {
			if (m.getName_method().equals(name))
				return m;
		}
		return null;
	}
	
	/**
	 * Gets a map with the classification of the project
	 * @param mapa map
	 * @param packagelst list of packages
	 * @return a map with the classification of the project
	 */
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
	
	/**
	 * Gets a map with the classification of the package
	 * @param mapa  map
	 * @param packagelst list of packages to evaluate
	 * @param packageName name of the package
	 * @return a map with the classification of the package
	 */
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

	/**
	 * Gets a map with the classification of the class
	 * @param map
	 * @param classeval class to evaluate
	 * @return a map with the classification of the class
	 */
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

	/**
	 * Gets a map with the classification of the class rule
	 * @param mapa map
	 * @param classeval class to evaluate
	 * @param ruleName rule name
	 * @return a map with the classification of the class rule
	 */
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

	/**
	 * Gets a map with the classification of the rule
	 * @param mapa map
	 * @param packageval package to evaluate
	 * @param ruleName rule name
	 * @return a map with the classification of the rule
	 */
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

	/**
	 * Increments the given map 
	 * @param map
	 * @param evaltype evaluator type 
	 * @return the incremented map
	 */
	private static Map<EvaluatorType, Integer> mapIncrement(Map<EvaluatorType, Integer> map, EvaluatorType evaltype) {
		map.replace(evaltype, map.get(evaltype) + 1);
		return map;
	}

}
