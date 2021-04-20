package CodeSmellDetectionEvaluator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		//PRECISO DE TESTAR
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
