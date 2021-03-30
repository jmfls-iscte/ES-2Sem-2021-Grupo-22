package ana_rules;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class SaveLoadRule {

	ArrayList<Rule> rules;

	public SaveLoadRule() {
		rules = new ArrayList<Rule>();
	}

	public SaveLoadRule(ArrayList<Rule> rules) {
		this.rules = rules;
	}

	
	
	
	/*
	 * Regras no ficheiro serão guardadas com o segunte layout
	 * Nome:Tipo:Atributi1:Atributo2:....
	 * EX: GodClass:Class:WMC_Class:>:50:OR:NOM_Class:>:10
	 * 
	 */
	public void SaveRules() {
		try {
			FileWriter fileRulesClass = new FileWriter("C:\\Users\\Tiago\\Desktop\\ES_PROJECT\\RuleClass.txt");
			FileWriter fileMethodClass = new FileWriter("C:\\Users\\Tiago\\Desktop\\ES_PROJECT\\RuleMethod.txt");
			
			

			for (Rule r : rules) {
				String rule_info_String = RuleToString(r.getRule());
				
				if (r.getType().equals("Class")) 
				{
					fileRulesClass.append(r.getName()+":"+r.getType()+":"+rule_info_String);
					fileRulesClass.append("\n");
					System.out.println("Escriton\n");
				} else if (r.getType().equals("Method")) {
					fileMethodClass.write(r.getName()+":"+r.getType()+":"+rule_info_String);
					fileMethodClass.write("\n");
					System.out.println("Escrito\n");
				} else 
				{ 
					/// ERROR
				}
			}
			
			
			fileRulesClass.close();
			fileMethodClass.close();

		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	
	public String RuleToString(ArrayList<RuleObject> rule_info)
	{
		
		return "tentativa";
		
	}

}
