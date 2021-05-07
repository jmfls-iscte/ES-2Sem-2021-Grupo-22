package rules;

public enum Logical_Operator {
	AND,
	OR;

	public static boolean logicOperation(boolean one,Logical_Operator operator, boolean two) {
		switch(operator) {
		case AND:
			return one && two;
		case OR:
			return one || two;
		default:
			return true;  //unreachable code
		}
	}
	
	public static boolean IsValid(String input) {
		try {
			Logical_Operator.valueOf(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}

