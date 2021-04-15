package ana_rules;

import org.apache.commons.collections4.EnumerationUtils;

public class RuleObject {
	private String info;
	private RuleObjectType label; //Tipos de labels: name, metric, comparator, threshold, logic_operator 
	
	//criar enumerado com os tipos de label na interface, para ser acessivel a todas as classes e 
	//nao existir repetiçao de codigo
	
	
	public RuleObject(String info, String label) throws IllegalArgumentException{ 
		this.label = RuleObjectType.valueOf(label);
		if(this.label.equals(RuleObjectType.THRESHOLD) && !isNumeric(info)) {
			throw new IllegalArgumentException("invalid rule Object");
		}else if(this.label.equals(RuleObjectType.LOGIC_OPERATOR) && !Logical_Operator.IsValid(info) ) {
			throw new IllegalArgumentException("invalid rule Object");
		}else if(this.label.equals(RuleObjectType.CLASSMETRIC) && !ClassMetric.IsValid(info)) {
			throw new IllegalArgumentException("invalid rule Object");
		}else if(this.label.equals(RuleObjectType.COMPARISON_OPERATOR) && !Comparator_Operator.IsValid(info)) {
			throw new IllegalArgumentException("invalid rule Object");
		}else if(this.label.equals(RuleObjectType.METHODMETRIC) && !MethodMetric.IsValid(info)) {
			throw new IllegalArgumentException("invalid rule Object");
		}
		this.info = info;;
	}
	
	public String getInfo() {
		return info;
	}
	
	public RuleObjectType getLabel() {
		return label;
	}
	
	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}


