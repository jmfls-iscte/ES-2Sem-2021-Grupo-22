package ana_rules;

import metrics.Method;

/**
 * 
 * Represents the metric of the method in the rules
 *
 */
public enum MethodMetric {
	LOC_METHOD,
	CYCLO_METHOD;
	
	/**
	 * Gets the value of a method's metric
	 * @param metric
	 * @param method
	 * @return the value of a method's metric
	 */
	public static int getMetric(MethodMetric metric,Method method ) {
		if(metric.equals(MethodMetric.LOC_METHOD)) {
			return method.getLOC_method();
		}else if (metric.equals(MethodMetric.CYCLO_METHOD)) {
			return method.getCYCLO_method();
		}else {
			return 0;  //unreachable code
		}
	}
	
	/**
	 * Verifies if the type of input is valid
	 * @param input
	 * @return true if the type of input is valid
	 */
	public static boolean IsValid(String input) {
		try {
			MethodMetric.valueOf(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
