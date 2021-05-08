package rules;

/**
 * 
 * Represents the comparator in the rule
 *
 */
public enum Comparator_Operator {
	EQUALS("=="), NOTEQUALS("!="), GREATER(">"), LESS("<"), GREATEREQUAL(">="), LESSEQUAL("<=");

	String Operator;

	/**
	 * Initiates the Comparator_Oparator
	 * 
	 * @param operator
	 */
	private Comparator_Operator(String Operator) {
		this.Operator = Operator;
	}

	/**
	 * Gets the operator string
	 * 
	 * @return the operator string
	 */
	public String getString() {
		return this.Operator;
	}

	/**
	 * Compares the metric with threshold through the comparator
	 * 
	 * @param metric
	 * @param operator
	 * @param threshold
	 * @return value of the comparison
	 */
	public static boolean compare(int metric, Comparator_Operator operator, int threshhold) {
		switch (operator) {
		case EQUALS:
			return metric == threshhold;
		case NOTEQUALS:
			return metric != threshhold;
		case GREATER:
			return metric > threshhold;
		case LESS:
			return metric < threshhold;
		case GREATEREQUAL:
			return metric >= threshhold;
		case LESSEQUAL:
			return metric <= threshhold;
		default:
			return false; // unreachable code
		}
	}

	/**
	 * Verifies if input type is valid
	 * 
	 * @param input
	 * @return true if if input type is valid
	 */
	public static boolean IsValid(String input) {
		try {
			Comparator_Operator.valueOf(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Gets the value of a given label
	 * 
	 * @param label
	 * @return the value of a given label
	 */
	public static Comparator_Operator valueOfLabel(String label) {
		for (Comparator_Operator e : values()) {
			if (e.Operator.equals(label)) {
				return e;
			}
		}
		return null;
	}

}
