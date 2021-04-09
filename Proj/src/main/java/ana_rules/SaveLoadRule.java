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

	
	private String pathFile ;
	
	

	public SaveLoadRule() throws IOException {
		pathFile = "RuleClass.txt";
		
	}
	public SaveLoadRule(String path) {
		pathFile = path;
	}

	
	

	
	

	/*
	 * Regras no ficheiro serão guardadas com o segunte layout
	 * Nome:Tipo:Atributi1:Atributo2:....
	 * EX: GodClass;Class;metric:WMC_Class;comparator:>;threshold:50;...
	 * 
	 */
	public void SaveRules(ArrayList<Rule> rules) {
		
		
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
	
	
	/*
	 * Regras no ficheiro serão guardadas com o segunte layout
	 * Nome:Tipo:Atributi1:Atributo2:....
	 * EX: GodClass;Class;metric:WMC_Class;comparator:>;threshold:50;...
	 * 
	 * 
	 * São lidas linha a linha e depois faz-se o split para ir buscar passo a passo o que se quer
	 * Constroi-se o array a medida que se vai lendo e fazendo o split
	 * Primeiro split por ";"
	 *    - Com isto temos o nome, o tipo e as partes da regra
	 *    - Para cada parte da regra faz-se o split por ":"
	 * 
	 */
	
	public ArrayList<Rule> LoadRules() {
		ArrayList<Rule> rules = new ArrayList<Rule>();
		
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
				rules.add(rule);
				
			}
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rules;
	
	}
	
	
	
	public String RuleToString(ArrayList<RuleObject> rule_info)
	{
		String aux ="";
		String rule_info_String=null;
		
		for(RuleObject ro : rule_info)
		{
			rule_info_String = aux.concat(";").concat(ro.getLabel()).concat(":").concat(ro.getInfo());
			aux = rule_info_String;
		}
		return rule_info_String;
		
	}

}
