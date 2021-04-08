package ana_rules;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import gui.MainGui;

public class SaveLoadRule {

	private ArrayList<Rule> rules;
	private String pathFile ;
	
	
	public static void main(String[] args) {
		try {
			SaveLoadRule SLR = new SaveLoadRule();
			ArrayList<Rule> rules3 = SLR.LoadRules();
			
			System.out.println("So para ler");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	public SaveLoadRule() throws IOException {
		rules = new ArrayList<Rule>();
		pathFile = "C:\\Users\\Tiago\\Desktop\\ES_PROJECT\\RuleClass.txt";
		
	}

	public SaveLoadRule(ArrayList<Rule> rules) {
		this.rules = rules;
		pathFile = "C:\\Users\\Tiago\\Desktop\\ES_PROJECT\\RuleClass.txt";
	}

	
	

	/*
	 * Regras no ficheiro serão guardadas com o segunte layout
	 * Nome:Tipo:Atributi1:Atributo2:....
	 * EX: GodClass:Class:WMC_Class:>:50:OR:NOM_Class:>:10
	 * 
	 */
	public void SaveRules() {
		try {
			
			FileWriter fileRules = new FileWriter(pathFile);
			//FileWriterfileRulesClass = new FileWriter(pathFile);
			//FileWriter fileRulesMethod = new FileWriter(pathFile);
			
			for (Rule r : rules) {
				String rule_info_String = RuleToString(r.getInfo());
				
				fileRules.append(r.getName()+";"+r.getType()+rule_info_String);
				fileRules.append("\n");
				
				/*if (r.getType().equals("Class")) 
				{
					fileRulesClass.append(r.getName()+";"+r.getType()+rule_info_String);
					fileRulesClass.append("\n");
					System.out.println("Escrito\n");
				} else if (r.getType().equals("Method")) {
					fileRulesMethod.append(r.getName()+";"+r.getType()+rule_info_String);
					fileRulesMethod.append("\n");
					System.out.println("Escrito\n");
				} else 
				{ 
					/// ERROR
				}*/
			}
			
			
			//fileRulesClass.close();
			//fileRulesMethod.close();
			
			fileRules.close();

		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Rule> LoadRules() {
		ArrayList<Rule> rules2 = new ArrayList<Rule>();
		
		try {
			File fileRules = new File(pathFile);
			Scanner reader = new Scanner(fileRules);
			
			while (reader.hasNextLine()) 
			{
				ArrayList<RuleObject> rule_info = new ArrayList<RuleObject>();
				String line = reader.nextLine();
				String[] splited = line.split(";");
				String name = splited[0];
				String type = splited[1];
				for(int i=2; i<splited.length;i++)
				{
					String[] partsOfRule = splited[i].split(":");
					String label = partsOfRule[0];
					String info = partsOfRule[1];
					
					RuleObject ro = new RuleObject(info, label);
					rule_info.add(ro);
				}
				
				Rule rule = new Rule(name, type, rule_info);
				rules2.add(rule);
				
			}
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rules2;
		
	}
	
	
	
	public String RuleToString(ArrayList<RuleObject> rule_info)
	{
		String aux ="";
		String rule_info_String=null;
		
		for(RuleObject ro : rule_info)
		{
			rule_info_String = aux.concat(";").concat(ro.getLabel().toString()).concat(":").concat(ro.getInfo());
			aux = rule_info_String;
		}
		return rule_info_String;
		
	}

}
