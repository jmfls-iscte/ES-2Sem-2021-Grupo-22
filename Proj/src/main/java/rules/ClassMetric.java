package rules;

import metrics.Method;

import metrics.Class;

/**
 * Represents the metrics of a class in the rules
 */
public enum ClassMetric {
	NOM_CLASS, LOC_CLASS, WMC_CLASS;

	/**
	 * Gets the value of the metric
	 * 
	 * @param metric
	 * @param classe class
	 * @return if input type is valid
	 */
	public static int getMetric(ClassMetric metric, Class classe) {
		if (metric.equals(ClassMetric.NOM_CLASS)) {
			return classe.getNOM_class();
		} else if (metric.equals(ClassMetric.LOC_CLASS)) {
			return classe.getLOC_class();
		} else if (metric.equals(ClassMetric.WMC_CLASS)) {
			return classe.getWMC_class();
		} else {
			return 0; // unreachable code
		}
	}

	/**
	 * Verifies if the type of input is valid
	 * 
	 * @param input
	 * @return true if the type of input is valid
	 */
	public static boolean IsValid(String input) {
		try {
			ClassMetric.valueOf(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
