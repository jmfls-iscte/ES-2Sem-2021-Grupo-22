package ana_rules;

import java.util.ArrayList;


public class Rule {
	//os thresholds têm que ser passados de string para ints
	
		private String name;
		private String type;
		private ArrayList<RuleObject> rule_info = new ArrayList<RuleObject>();
		
		public Rule(String name, String tipo) { //Rules é criada na GUI antes de ser pedida informação ao user, vai sendo adicionada por partes
			this.name = name;
			this.type = tipo;
			this.rule_info = new ArrayList<RuleObject>();
		} 
		
		public void set_info(String info, String label) { //quando o user mete um novo elemento é adicionado ao array
			RuleObject object = new RuleObject(info, label);
			this.rule_info.add(object);
		}
		
		public void edit_thresholds(RuleObject new_info, int id) { //quando o objecto para ser editado é seleccionado tem que vir com o id da posição do threshold
			if (new_info.label.equals("threshold")) {
				rule_info.set(id, new_info);
			}
		}
		
		
		public String getType()
		{
			return type;
			
		}
		
		public ArrayList<RuleObject> getRule()
		{
			return rule_info;
		}
		
		public String getName()
		{
			return name;
		}
}
