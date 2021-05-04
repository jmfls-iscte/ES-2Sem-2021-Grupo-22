package CodeSmellDetectionEvaluator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hpsf.Util;

import ana_rules.Rule;
import ana_rules.RuleEvaluator;
import ana_rules.RuleObject;
import ana_rules.SaveLoadRule;
import excel.ExcelRead;
import metrics.*;
import metrics.Class;
import metrics.Package;

public class CodeSmellDetectionEvaluator {

	private int truePositive;
	private int falsePositive;
	private int trueNegative;
	private int falseNegative;

	private int totalDetetions;
	private int total;
	private int totalClasses;
	private int totalMethods;

	private List<Package> packagesDetectionlst;
	private List<Package> packagesExcellst;

	private List<PackageEvaluator> packagesEvaluatorlst;

	// ============================ MAIN TO BE REMOVED
	// =====================================
	public static void main(String[] args) {

		ArrayList<Rule> rules;
		try {
			rules = (ArrayList<Rule>) SaveLoadRule.LoadRules("rules.txt");
		} catch (Exception e) {
			rules = (ArrayList<Rule>) RuleEvaluator.BASERULES();
		}

		ExcelRead excelRead = new ExcelRead("C:\\Users\\Tiago\\Desktop\\Code_Smells.xlsx", rules);
		List<Package> packagesExcel = excelRead.ReadFile();

		metrics.DirectoryGetter dirget = new DirectoryGetter();
		dirget.SetDir("C:\\Users\\Tiago\\eclipse-workspace2\\jasml_0.10.zip_expanded");
		dirget.FindSrc();
		dirget.FindPackages();
		List<Package> packages = dirget.getPackageList();
		RuleEvaluator.runCodeSmells(rules, packages);

		packages.get(0);

		CodeSmellDetectionEvaluator csde = new CodeSmellDetectionEvaluator(packages, packagesExcel);

	}
	// ============================ MAIN TO BE REMOVED
	// =====================================

	public CodeSmellDetectionEvaluator(List<Package> packagesDetectionlst, List<Package> packagesExcellst) {
		this.packagesDetectionlst = packagesDetectionlst;
		this.packagesExcellst = packagesExcellst;

		evaluateCodeSmellsDetection();
	}

	private boolean verifyPackages() {
		if (packagesDetectionlst.size() != packagesExcellst.size()) {
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
		if (!verifyPackages())
			throw new IllegalArgumentException();

		packagesEvaluatorlst = new ArrayList<PackageEvaluator>();

		int packagesSize = packagesExcellst.size();
		for (int packagesindex = 0; packagesindex < packagesSize; packagesindex++) {

			System.out.println("Package atual = " + packagesindex);
			Package currentPackage = packagesExcellst.get(packagesindex);
			Package packageDet = Utils.getPackagebyName(currentPackage.getName_Package(), packagesDetectionlst);

			List<Class> classExcel = currentPackage.getClass_list();
			List<Class> classDet = packageDet.getClass_list();

			List<ClassEvaluator> classlst = DetectionClasses(classDet, classExcel);

			PackageEvaluator packageEvaluator = new PackageEvaluator(currentPackage.getName_Package(), classlst);
			packagesEvaluatorlst.add(packageEvaluator);

		}

		// So para fazer testes
		Map<EvaluatorType, Integer> map = getClassificationTotal(); //Todos os codeSmelss
		Map<EvaluatorType, Integer> map1 = getClassificationPackage("com.jasml.compiler"); //Apenas de um package
		Map<EvaluatorType, Integer> map2 = getClassificationClass("com.jasml.compiler", "Scanner"); //Apenas de uma classe de um determinado package
		Map<EvaluatorType, Integer> map3 = getClassificationRule("is_God_Class"); //Projeto todo mas de uma regra especifica
		Map<EvaluatorType, Integer> map4 = getClassificationPackageRule("com.jasml.compiler", "is_God_Class"); // Apenas de um package e regra especifica
		Map<EvaluatorType, Integer> map5 = getClassificationClassRule("com.jasml.compiler", "Scanner", "is_God_Class");//Apenas de uma classe de um determinado package e regra especifica
		Map<EvaluatorType, Integer> map6 = getClassificationClassRule("com.jasml.compiler", "Scanner", "is_Long_Method");//Apenas de uma classe de um determinado package e regra especifica

		System.out.println("Só para ver");

	}

	private List<ClassEvaluator> DetectionClasses(List<Class> classDetectionlst, List<Class> classExcellst) {

		List<ClassEvaluator> classEvaluatorlst = new ArrayList<ClassEvaluator>();
		int classSize = classExcellst.size();
		for (int classindex = 0; classindex < classSize; classindex++) {

			try {
				Class currentClass = classExcellst.get(classindex);
				System.out.println("Classe atual = " + currentClass.getName_Class());
				Class classDet = Utils.getClassbyName(currentClass.getName_Class(), classDetectionlst);

				Map<String, Boolean> rulesClassDetection = currentClass.getCode_Smells();
				Map<String, Boolean> rulesClassExcel = classDet.getCode_Smells();
				Map<String, EvaluatorType> detectionRules = DetectionRule(rulesClassDetection, rulesClassExcel);

				List<Method> methodExcel = currentClass.getMethod_list();
				List<Method> methodDet = classDet.getMethod_list();

				List<MethodEvaluator> detectionMethods = DetectionMethod(methodDet, methodExcel);

				ClassEvaluator classEval = new ClassEvaluator(currentClass);
				classEval.setMethodList(detectionMethods);
				classEval.setCodesmelssEvaluator(detectionRules);
				classEvaluatorlst.add(classEval);
				totalClasses++;
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

		return classEvaluatorlst;
	}

	private List<MethodEvaluator> DetectionMethod(List<Method> methodDetectionlst, List<Method> methodExcellst) {

		List<MethodEvaluator> methodEvallst = new ArrayList<MethodEvaluator>();
		int methodsize = methodExcellst.size();
		for (int methodindex = 0; methodindex < methodsize; methodindex++) {

			Method currentMethod = methodExcellst.get(methodindex);
			Method methodDet = Utils.getMethodbyName(currentMethod.getName_method(), methodDetectionlst);

			if (currentMethod != null && methodDet != null) {
				Map<String, Boolean> rulesMethodDetection = currentMethod.getCode_Smells();
				Map<String, Boolean> rulesMethodExcel = methodDet.getCode_Smells();

				Map<String, EvaluatorType> detection = DetectionRule(rulesMethodDetection, rulesMethodExcel);

				MethodEvaluator methodEvaluator = new MethodEvaluator(currentMethod);
				methodEvaluator.setCodesmelssEvaluator(detection);
				methodEvallst.add(methodEvaluator);
				totalMethods++;
			}

		}

		return methodEvallst;
	}

	private Map<String, EvaluatorType> DetectionRule(Map<String, Boolean> rulesDetection,
			Map<String, Boolean> rulesExcel) {
		Map<String, EvaluatorType> detection = new HashMap<String, EvaluatorType>();

		for (String rulename : rulesDetection.keySet()) {
			boolean ruleDetctionvalue = rulesDetection.get(rulename);
			boolean ruleExcelvalue = rulesExcel.get(rulename);

			if (ruleDetctionvalue == true && ruleExcelvalue == true) {
				detection.put(rulename, EvaluatorType.TP);
				truePositive++;
			} else if (ruleDetctionvalue == true && ruleExcelvalue == false) {
				detection.put(rulename, EvaluatorType.FP);
				falsePositive++;
			} else if (ruleDetctionvalue == false && ruleExcelvalue == true) {
				detection.put(rulename, EvaluatorType.FN);
				falseNegative++;
			} else if (ruleDetctionvalue == false && ruleExcelvalue == false) {
				detection.put(rulename, EvaluatorType.TN);
				trueNegative++;
			}

			total++;
		}

		return detection;

	}

	
	public Map<EvaluatorType, Integer> getClassificationTotal() {

		Map<EvaluatorType, Integer> mapa = new HashMap<EvaluatorType, Integer>();
		mapa.put(EvaluatorType.TP, 0);
		mapa.put(EvaluatorType.TN, 0);
		mapa.put(EvaluatorType.FP, 0);
		mapa.put(EvaluatorType.FN, 0);
		return Utils.getClassificationTotal(mapa, packagesEvaluatorlst);
	}
	
	
	public Map<EvaluatorType, Integer> getClassificationPackage(String packageName) {

		Map<EvaluatorType, Integer> mapa = new HashMap<EvaluatorType, Integer>();
		mapa.put(EvaluatorType.TP, 0);
		mapa.put(EvaluatorType.TN, 0);
		mapa.put(EvaluatorType.FP, 0);
		mapa.put(EvaluatorType.FN, 0);
		return Utils.getClassificationPackage(mapa, packagesEvaluatorlst, packageName);
	}

	public Map<EvaluatorType, Integer> getClassificationClass(String packageName, String className) {
		Map<EvaluatorType, Integer> mapa = new HashMap<EvaluatorType, Integer>();
		mapa.put(EvaluatorType.TP, 0);
		mapa.put(EvaluatorType.TN, 0);
		mapa.put(EvaluatorType.FP, 0);
		mapa.put(EvaluatorType.FN, 0);
		for (PackageEvaluator p : packagesEvaluatorlst) {
			if (p.getName().equals(packageName)) {
				for (ClassEvaluator c : p.getClasslst()) {
					if (c.getClasseval().getName_Class().equals(className)) {
						return Utils.getClassificationClass(mapa, c);
					}
				}
			}
		}
		return null;
	}

	public Map<EvaluatorType, Integer> getClassificationRule(String ruleName) {
		Map<EvaluatorType, Integer> mapa = new HashMap<EvaluatorType, Integer>();
		mapa.put(EvaluatorType.TP, 0);
		mapa.put(EvaluatorType.TN, 0);
		mapa.put(EvaluatorType.FP, 0);
		mapa.put(EvaluatorType.FN, 0);

		for (PackageEvaluator p : packagesEvaluatorlst)
			mapa = Utils.getClassificationRule(mapa, p, ruleName);

		return mapa;

	}

	public Map<EvaluatorType, Integer> getClassificationPackageRule(String packageName, String ruleName) {
		Map<EvaluatorType, Integer> mapa = new HashMap<EvaluatorType, Integer>();
		mapa.put(EvaluatorType.TP, 0);
		mapa.put(EvaluatorType.TN, 0);
		mapa.put(EvaluatorType.FP, 0);
		mapa.put(EvaluatorType.FN, 0);
		for (PackageEvaluator p : packagesEvaluatorlst) {
			if (p.getName().equals(packageName)) {
				mapa = Utils.getClassificationRule(mapa, p, ruleName);
			}
		}
		return mapa;
	}

	public Map<EvaluatorType, Integer> getClassificationClassRule(String packageName, String className,
			String ruleName) {
		Map<EvaluatorType, Integer> mapa = new HashMap<EvaluatorType, Integer>();
		mapa.put(EvaluatorType.TP, 0);
		mapa.put(EvaluatorType.TN, 0);
		mapa.put(EvaluatorType.FP, 0);
		mapa.put(EvaluatorType.FN, 0);
		for (PackageEvaluator p : packagesEvaluatorlst) {
			if (p.getName().equals(packageName)) {
				for (ClassEvaluator c : p.getClasslst())
					if(c.getClasseval().getName_Class().equals(className))
						mapa = Utils.getClassificationClassRule(mapa, c, ruleName);

			}
		}
		return mapa;
	}

}
