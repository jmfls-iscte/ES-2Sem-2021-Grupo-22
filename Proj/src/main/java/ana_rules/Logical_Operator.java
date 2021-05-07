package ana_rules;

/**
 * 
 * Represents the logical operators
 *
 */
public enum Logical_Operator {
	AND, OR;

	/**
	 * Gets a boolean given two boolean values and a logical operator
	 * 
	 * @param one      first boolean value
	 * @param operator
	 * @param two      second boolean value
	 * @return a boolean given two boolean values and a logical operator
	 */
	public static boolean logicOperation(boolean one, Logical_Operator operator, boolean two) {
		switch (operator) {
		case AND:
			return one && two;
		case OR:
			return one || two;
		default:
			return true; // unreachable code
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
			Logical_Operator.valueOf(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
