package ana_rules;

import java.util.ArrayList;

public class Rule {

	// os thresholds têm que ser passados de string para ints

	private String name;
	private String type;
	private ArrayList<RuleObject> rule_info = new ArrayList<RuleObject>();

	// Recebe da GUI um array de RuleObject com toda a informação da regra.
	// quando o código recebe a action para criar uma regra nova, faz-se no
	// codigo respectivo "Rule rule = new Rule(Arraylist RuleObject)".

	public Rule(String name, String type, ArrayList<RuleObject> new_rule) {
		for (int x = 0; x < new_rule.size(); x++) {
			if (new_rule.get(x).get_label().equals("thresholds")) {
				try {
					int i = Integer.parseInt(new_rule.get(x).get_info()); // confirma se o limite colocado é um inteiro
				} catch (Exception e) {
					throw new IllegalArgumentException("O limite inserido não é um número");
				}
			}
		}
		this.rule_info = new_rule;
		this.name = name;
		this.type = type;
	}

	// Assumimos em discussão de grupo prévia que a única coisa editável são os
	// limites (thresholds)

	public void editThreshold(RuleObject new_info, RuleObject old_info) {

		// recebe da GUI o novo limite e o limite antigo (ou seja, gera dois objectos:
		// 1 com a info antiga (que o utilizador tem que ter acesso) e 1 com a
		// informacao nova que o utilizador dá)

		if (new_info.label.equals("threshold") && old_info.label.equals("thresholds")) {
			int id = getObjectID(old_info);
			rule_info.set(id, new_info);
		}
	}

	public int getObjectID(RuleObject object) {
		return rule_info.indexOf(object);
	}

	public String getName() {
		return this.name;
	}

	public ArrayList<RuleObject> getInfo() {
		return this.rule_info;
	}

	public String getType() {
		return type;

	}

	public ArrayList<RuleObject> getRule() {
		return rule_info;
	}

}
//verificar se a regra nao existe -> guardar regra