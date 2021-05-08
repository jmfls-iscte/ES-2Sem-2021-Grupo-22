package rules;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Represents the rules used to detect code smells
 * 
 */
public class Rule {

	// os thresholds têm que ser passados de string para ints

	private String name;
	private String type;//class or method
	private ArrayList<RuleObject> rule_info = new ArrayList<RuleObject>();

	// Recebe da GUI um array de RuleObject com toda a informação da regra.
	// quando o código recebe a action para criar uma regra nova, faz-se no
	// codigo respectivo "Rule rule = new Rule(Arraylist RuleObject)".

	/**
	 * Initializes Rule
	 * @param name
	 * @param type
	 * @param new_rule 
	 */
	public Rule(String name, String type, ArrayList<RuleObject> new_rule) {
		this.rule_info = new_rule;
		this.name = name;
		this.type = type;
	}
	
	/**
	 * Initializes Rule
	 * @param name
	 * @param type
	 * @param new_rule
	 * @param verification
	 * @throws Exception if the structure of the rule is invalid
	 */
	public Rule(String name, String type, ArrayList<RuleObject> new_rule,Boolean verification) throws Exception{
		//boolean valid=true;
		RuleObjectType ruleType;
		if(type.equals("class")) {
			ruleType= RuleObjectType.CLASSMETRIC;
		}else if(type.equals("method")) {
			ruleType= RuleObjectType.METHODMETRIC;
		}else {
			throw new IllegalArgumentException("invalid type, try \"class\" or \"method\"");
		}
			
		for (int x = 0; x < new_rule.size(); x++) {
			if(x%4==0) {
				if(!new_rule.get(x).getLabel().equals(ruleType)) {
					throw new IllegalArgumentException("Rule structure wasn't followed, RuleObejct #"+x+" is wrong");
				}
			}else if(x%4==1) {
				if(!new_rule.get(x).getLabel().equals(RuleObjectType.COMPARISON_OPERATOR)) {
					throw new IllegalArgumentException("Rule structure wasn't followed, RuleObejct #"+x+" is wrong");
				}
				
			}else if(x%4==2) {
				if(!new_rule.get(x).getLabel().equals(RuleObjectType.THRESHOLD)) {
					throw new IllegalArgumentException("Rule structure wasn't followed, RuleObejct #"+x+" is wrong");
				}
			}else {//x%4==3
				if(!new_rule.get(x).getLabel().equals(RuleObjectType.LOGIC_OPERATOR)) {
					throw new IllegalArgumentException("Rule structure wasn't followed, RuleObejct #"+x+" is wrong");
				}
			}
		}
		if(!new_rule.get(new_rule.size()-1).getLabel().equals(RuleObjectType.THRESHOLD)) {
			throw new IllegalArgumentException("Last element of the rule isn't threshold");
		}
		this.rule_info = new_rule;
		this.name = name;
		this.type = type;	
	}

	// Assumimos em discussão de grupo prévia que a única coisa editável são os
	// limites (thresholds)

	/**
	 * Edits rule threshold	
	 * @param old_info rule to change
	 * @param new_info new rule
	 */
	public void editThreshold(RuleObject old_info, RuleObject new_info) {

		// recebe da GUI o novo limite e o limite antigo (ou seja, gera dois objectos:
		// 1 com a info antiga (que o utilizador tem que ter acesso) e 1 com a
		// informacao nova que o utilizador dá)

		if (new_info.getLabel().equals(RuleObjectType.THRESHOLD) && old_info.getLabel().equals(RuleObjectType.THRESHOLD)) {
			int id = getObjectID(old_info);
			rule_info.set(id, new_info);
		}
	}
	
	
	/**
	 * Sets the rule's info
	 * @param object arrayList of rule object
	 */
	public void setInfo(ArrayList<RuleObject> object){
		rule_info = object;
	}

	/**
	 * Gets the object id
	 * @param object rule object
	 * @return the object id
	 */
	public int getObjectID(RuleObject object) {
		return rule_info.indexOf(object);
	}

	/**
	 * Gets the name of the string
	 * @return the name of the string
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the rule's info
	 * @return the rule's info
	 */
	public ArrayList<RuleObject> getInfo() {
		return rule_info;
	}

	/**
	 * Gets the type of rule
	 * @return the type of rule
	 */
	public String getType() {
		return type;

	}
	
	/**
	 * Transforms a rule into a string
	 * @return the rule in a string form
	 */
	public String toString() {
		String rule = "";
		for (RuleObject r : rule_info) {
			if (r.getLabel().equals(RuleObjectType.COMPARISON_OPERATOR)) {
				rule += Comparator_Operator.valueOf(r.getInfo()).getString() + " ";
			} else {

				rule += r.getInfo().toString() + " ";
			}
			
		}
		return rule;
	}
	
	/**
	 * Gets a rule given its name and a list of rules
	 * @param rulelst rule list
	 * @param name of the rule
	 * @return a rule given its name and a list of rules
	 */
	public static Rule getRuleFromList(List<Rule> rulelst,String name) {
		for(Rule rule: rulelst) {
			if(rule.getName().equals(name)) {
				return rule;
			}
		}
		return null;
	}

}
