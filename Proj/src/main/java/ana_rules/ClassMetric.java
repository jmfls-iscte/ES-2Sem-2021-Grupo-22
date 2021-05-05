package ana_rules;

import metrics.Method;

import metrics.Class;

public enum ClassMetric {
	NOM_CLASS,
	LOC_CLASS,
	WMC_CLASS;
	
	public static int getMetric(ClassMetric metric,Class classe ) {
		if(metric.equals(ClassMetric.NOM_CLASS)) {
			return classe.getNOM_class();
		}else if (metric.equals(ClassMetric.LOC_CLASS)) {
			return classe.getLOC_class();
		}else if (metric.equals(ClassMetric.WMC_CLASS)) {
			return classe.getWMC_class();
		}else { 
			return 0; //unreachable code
		}
	}
	
	public static boolean IsValid(String input) {
		try {
			ClassMetric.valueOf(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
