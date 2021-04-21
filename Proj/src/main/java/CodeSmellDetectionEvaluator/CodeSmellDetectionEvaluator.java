package CodeSmellDetectionEvaluator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ana_rules.Rule;
import ana_rules.RuleEvaluator;
import ana_rules.RuleObject;
import ana_rules.SaveLoadRule;
import excel.ExcelRead;
import gui.MainGui;
import metrics.*;
import metrics.Class;
import metrics.Package;

public class CodeSmellDetectionEvaluator {

	private int truePositive;
	private int falsePositive;
	private int trueNegative;
	private int falseNegative;
	private int totalDetetions;

	private List<Package> packagesDetectionlst;
	private List<Package> packagesExcellst;

	private List<PackageEvaluator> packagesEvaluatorlst;
	
	
	
	public static void main(String[] args) {
		
//		ArrayList<Rule> r = new ArrayList<Rule>();
//		RuleObject obj1 = new RuleObject("LOC_METHOD", "METHODMETRIC");
//		RuleObject obj2 = new RuleObject("GREATER", "COMPARISON_OPERATOR");
//		RuleObject obj3 = new RuleObject("50", "THRESHOLD");
//		RuleObject obj4 = new RuleObject("AND", "LOGIC_OPERATOR");
//		RuleObject obj5 = new RuleObject("CYCLO_METHOD", "METHODMETRIC");
//		RuleObject obj6 = new RuleObject("GREATER", "COMPARISON_OPERATOR");
//		RuleObject obj7 = new RuleObject("10", "THRESHOLD");
//		ArrayList<RuleObject> ruleinfo = new ArrayList<RuleObject>();
//		ruleinfo.add(obj1);
//		ruleinfo.add(obj2);
//		ruleinfo.add(obj3);
//		ruleinfo.add(obj4);
//		ruleinfo.add(obj5);
//		ruleinfo.add(obj6);
//		ruleinfo.add(obj7);
//		
//		
//		RuleObject obj8 = new RuleObject("WMC_CLASS", "CLASSMETRIC");
//		RuleObject obj9 = new RuleObject("GREATER", "COMPARISON_OPERATOR");
//		RuleObject obj10 = new RuleObject("50", "THRESHOLD");
//		RuleObject obj11 = new RuleObject("OR", "LOGIC_OPERATOR");
//		RuleObject obj12 = new RuleObject("NOM_CLASS", "CLASSMETRIC");
//		RuleObject obj13 = new RuleObject("GREATER", "COMPARISON_OPERATOR");
//		RuleObject obj14 = new RuleObject("10", "THRESHOLD");
//		ArrayList<RuleObject> ruleinfo2 = new ArrayList<RuleObject>();
//		ruleinfo2.add(obj8);
//		ruleinfo2.add(obj9);
//		ruleinfo2.add(obj10);
//		ruleinfo2.add(obj11);
//		ruleinfo2.add(obj12);
//		ruleinfo2.add(obj13);
//		ruleinfo2.add(obj14);
//		
//		try {
//			Rule long_method = new Rule("Is_Long_Method", "method", ruleinfo, true);
//			Rule Is_God_Class = new Rule("Is_God_Class", "class", ruleinfo2, true);
//			r.add(long_method);
//			r.add(Is_God_Class);
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
		
		
		ArrayList<Rule> rules = (ArrayList<Rule>) SaveLoadRule.LoadRules("rule.txt");
		ExcelRead excelRead = new ExcelRead("C:\\Users\\Tiago\\Desktop\\Code_Smells.xlsx", rules);
		List<Package> packagesExcel =  excelRead.ReadFile();

		metrics.DirectoryGetter dirget = new DirectoryGetter();
		dirget.SetDir("C:\\Users\\Tiago\\eclipse-workspace2\\jasml_0.10.zip_expanded");
		dirget.FindSrc();
		dirget.FindPackages();
		List<Package> packages = dirget.getPackageList();
		//RuleEvaluator.runCo
		
		CodeSmellDetectionEvaluator csde = new CodeSmellDetectionEvaluator(packages, packagesExcel);
		csde.evaluateCodeSmellsDetection();
		
	}
	
	
	
	
	
	
	

	public CodeSmellDetectionEvaluator(List<Package> packagesDetectionlst, List<Package> packagesExcellst) {
		this.packagesDetectionlst = packagesDetectionlst;
		this.packagesExcellst = packagesExcellst;

		evaluateCodeSmellsDetection();
	}

	private boolean verifyPossibleEvaluate() {
		return verifyPackages() && verifyClassesAndMethods();

	}

	private boolean verifyPackages() {
		if (packagesDetectionlst.size() != packagesExcellst.size()) {
			return false;
		}
		return true;
	}

	private boolean verifyClassesAndMethods() {
		for (int i = 0; i < packagesDetectionlst.size(); i++) {
			if (packagesDetectionlst.get(i).getClass_list().size() != packagesExcellst.get(i).getClass_list().size())
				return false;
			else {
				for (int j = 0; j < packagesDetectionlst.get(i).getClass_list().size(); j++) {
					if (!VerifyMethods(packagesDetectionlst.get(i).getClass_list().get(j),
							packagesExcellst.get(i).getClass_list().get(j)))
						return false;
				}
			}
		}

		return true;
	}

	private boolean VerifyMethods(Class classDetetcion, Class classExcel) {
		if (classDetetcion.getMethod_list().size() != classExcel.getMethod_list().size()) {
			return false;
		}
		return true;
	}

	private void evaluateCodeSmellsDetection() {
		/*
		 * Percorre todos os packages, e verifica as regras da classes e dos metodos,
		 * tanto da deteção como do excel e compara os resultados para verificar qual
		 * dos 4 tipos é TP, TN, FP, FN.
		 * 
		 */
		if (!verifyPossibleEvaluate())
			throw new IllegalArgumentException();

		packagesEvaluatorlst = new ArrayList<PackageEvaluator>();

		int packagesSize = packagesDetectionlst.size();
		for (int packagesindex = 0; packagesindex < packagesSize; packagesindex++) {

			Package currentPackage = packagesDetectionlst.get(packagesindex);

			List<Class> classDetectionlst = packagesDetectionlst.get(packagesindex).getClass_list();
			List<Class> classExcellst = packagesExcellst.get(packagesindex).getClass_list();

			List<ClassEvaluator> classlst = DetectionClasses(classDetectionlst, classExcellst);

			PackageEvaluator packageEvaluator = new PackageEvaluator(currentPackage.getName_Package(), classlst);
			packagesEvaluatorlst.add(packageEvaluator);

		}
		
		System.out.println("Só para ver");

	}

	private List<ClassEvaluator> DetectionClasses(List<Class> classDetectionlst, List<Class> classExcellst) {

		List<ClassEvaluator> classEvaluatorlst = new ArrayList<ClassEvaluator>();
		int classSize = classDetectionlst.size();
		for (int classindex = 0; classindex < classSize; classindex++) {

			Class currentClass = classDetectionlst.get(classindex);
			
			Map<String, Boolean> rulesMethodDetection = classDetectionlst.get(classindex).getCode_Smells();
			Map<String, Boolean> rulesMethodExcel = classExcellst.get(classindex).getCode_Smells();
			Map<String, EvaluatorType> detectionRules = DetectionRule(rulesMethodDetection, rulesMethodExcel);
			
			List<Method> methodDetectionlst = classDetectionlst.get(classindex).getMethod_list();
			List<Method> methodExcellst = classExcellst.get(classindex).getMethod_list();
			List<MethodEvaluator> detectionMethods = DetectionMethod(methodDetectionlst, methodExcellst);
			
			ClassEvaluator classEval = new ClassEvaluator(currentClass);
			classEval.setMethodList(detectionMethods);
			classEval.setCodesmelssEvaluator(detectionRules);
			
			
			classEvaluatorlst.add(classEval);
		}

		return classEvaluatorlst;
	}

	private List<MethodEvaluator> DetectionMethod(List<Method> methodDetectionlst, List<Method> methodExcellst) {

		List<MethodEvaluator> methodEvallst = new ArrayList<MethodEvaluator>();
		int methodsize = methodDetectionlst.size();
		for (int methodindex = 0; methodindex < methodsize; methodindex++) {

			Method currentMethod = methodDetectionlst.get(methodindex);

			Map<String, Boolean> rulesMethodDetection = methodDetectionlst.get(methodindex).getCode_Smells();
			Map<String, Boolean> rulesMethodExcel = methodExcellst.get(methodindex).getCode_Smells();

			MethodEvaluator methodEvaluator = new MethodEvaluator(currentMethod);

			Map<String, EvaluatorType> detection = DetectionRule(rulesMethodDetection, rulesMethodExcel);

			methodEvaluator.setCodesmelssEvaluator(detection);

			methodEvallst.add(methodEvaluator);
		}

		return methodEvallst;
	}

	private Map<String, EvaluatorType> DetectionRule(Map<String, Boolean> rulesDetection,
			Map<String, Boolean> rulesExcel) {
		Map<String, EvaluatorType> detection = new HashMap<String, EvaluatorType>();

		int rulesSize = rulesDetection.size();
		for (String rulename : rulesDetection.keySet()) {
			boolean ruleDetctionvalue = rulesDetection.get(rulename);
			boolean ruleExcelvalue = rulesExcel.get(rulename);

			if (ruleDetctionvalue == true && ruleExcelvalue == true)
				detection.put(rulename, EvaluatorType.TP);
			else if (ruleDetctionvalue == true && ruleExcelvalue == false)
				detection.put(rulename, EvaluatorType.FP);
			else if (ruleDetctionvalue == false && ruleExcelvalue == true)
				detection.put(rulename, EvaluatorType.FN);
			else if (ruleDetctionvalue == false && ruleExcelvalue == false)
				detection.put(rulename, EvaluatorType.TN);
		}

		return detection;

	}

}
