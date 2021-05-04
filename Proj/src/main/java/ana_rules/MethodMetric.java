package ana_rules;

import metrics.Method;

public enum MethodMetric {
	LOC_METHOD,
	CYCLO_METHOD;
	
	public static int getMetric(MethodMetric metric,Method method ) {
		if(metric.equals(MethodMetric.LOC_METHOD)) {
			return method.getLOC_method();
		}else if (metric.equals(MethodMetric.CYCLO_METHOD)) {
			return method.getCYCLO_method();
		}else {
			return 0;  //unreachable code
		}
	}
	public static boolean IsValid(String input) {
		try {
			MethodMetric.valueOf(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
