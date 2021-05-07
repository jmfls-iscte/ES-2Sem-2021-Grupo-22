package rules;

public enum Comparator_Operator {
	EQUALS("=="),
	NOTEQUALS("!="),
	GREATER(">"),
	LESS("<"),
	GREATEREQUAL(">="),
	LESSEQUAL("<=");
	
	String Operator;
	private Comparator_Operator(String Operator) {
		this.Operator=Operator;
	}
	
	public String getString() {
		return this.Operator;
	}
	
	public static boolean compare(int metric,Comparator_Operator operator, int threshhold) {
		switch(operator) {
			case EQUALS:
				return metric==threshhold;
			case NOTEQUALS:
				return metric!=threshhold;
			case GREATER:
				return metric>threshhold;
			case LESS:
				return metric<threshhold;
			case GREATEREQUAL:
				return metric>=threshhold;
			case LESSEQUAL:
				return metric<=threshhold;
			default:
				return false;  //unreachable code
		}
	}
	
	public static boolean IsValid(String input) {
		try {
			Comparator_Operator.valueOf(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static Comparator_Operator valueOfLabel(String label) {
	    for (Comparator_Operator e : values()) {
	        if (e.Operator.equals(label)) {
	            return e;
	        }
	    }
	    return null;
	}

}
