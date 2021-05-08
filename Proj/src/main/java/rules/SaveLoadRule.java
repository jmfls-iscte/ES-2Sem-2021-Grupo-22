package rules;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SaveLoadRule {

	/**
	 * Rules in the file will be saved with the following layout:
	 * Name:type:atribute1:atribute2:... EX:
	 * GodClass;Class;metric:WMC_Class;comparator:>;threshold:50;...
	 * 
	 * @param rules
	 * @param pathFile path of the file
	 */
	public static void SaveRules(ArrayList<Rule> rules, String pathFile) {
		if (pathFile == null || pathFile.equals(""))
			throw new IllegalArgumentException();

		try {

			FileWriter fileRules = new FileWriter(pathFile);

			for (Rule r : rules) {
				String rule_info_String = RuleToString(r.getInfo());

				fileRules.append(r.getName() + ";" + r.getType() + rule_info_String);
				fileRules.append("\n");
			}
			fileRules.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Loads the rules in the file
	 * 
	 * @param pathFile path of the file
	 * @return list of the rules
	 */
	public static List<Rule> LoadRules(String pathFile) {

		if (pathFile == null || pathFile.equals(""))
			throw new IllegalArgumentException();

		ArrayList<Rule> rules = new ArrayList<Rule>();

		try {
			File fileRules = new File(pathFile);
			Scanner reader = new Scanner(fileRules);

			while (reader.hasNextLine()) {
				ArrayList<RuleObject> rule_info = new ArrayList<RuleObject>();
				String line = reader.nextLine();
				String[] splited = line.split(";");
				String name = splited[0];
				String type = splited[1];
				for (int i = 2; i < splited.length; i++) {
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

	/**
	 * Turns a rule into a string
	 * 
	 * @param rule_info rule info
	 * @return rule in a string
	 */
	private static String RuleToString(ArrayList<RuleObject> rule_info) {
		String aux = "";
		String rule_info_String = null;

		for (RuleObject ro : rule_info) {
			rule_info_String = aux.concat(";").concat(ro.getLabel().toString()).concat(":").concat(ro.getInfo());
			aux = rule_info_String;
		}
		return rule_info_String;

	}

}
