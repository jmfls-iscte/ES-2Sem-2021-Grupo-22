package CodeSmellDetectionEvaluator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

			packagesEvaluatorlst.add(DetectionClasses(classDetectionlst, classExcellst, currentPackage));

		}

	}

	private PackageEvaluator DetectionClasses(List<Class> classDetectionlst, List<Class> classExcellst,
			Package currentPackage) {

		PackageEvaluator packageEval = new PackageEvaluator(currentPackage.getName_Package());

		int classSize = classDetectionlst.size();
		for (int classindex = 0; classindex < classSize; classindex++) {

			Class currentClass = classDetectionlst.get(classindex);

			List<Method> methodDetectionlst = classDetectionlst.get(classindex).getMethod_list();
			List<Method> methodExcellst = classExcellst.get(classindex).getMethod_list();

			packageEval.addClass(DetectionMethod(methodDetectionlst, methodExcellst, currentClass));
		}

		return packageEval;
	}

	private ClassEvaluator DetectionMethod(List<Method> methodDetectionlst, List<Method> methodExcellst,
			Class currentClass) {
		
		
		ClassEvaluator classEval = new ClassEvaluator(currentClass);
		
		int methodsize = methodDetectionlst.size();
		for (int methodindex = 0; methodindex < methodsize; methodindex++) {

			Method currentMethod = methodDetectionlst.get(methodindex);
			Map<String, Boolean> rulesMethodDetection = methodDetectionlst.get(methodindex).getCode_Smells();
			Map<String, Boolean> rulesMethodExcel = methodExcellst.get(methodindex).getCode_Smells();
			
			MethodEvaluator methodEvaluator = new MethodEvaluator(currentMethod);
			methodEvaluator.setCodesmelssEvaluator(DetectionRule(rulesMethodDetection,rulesMethodExcel));
			classEval.addMethodList(methodEvaluator);
		}

		return classEval;
	}
	
	private Map<String, EvaluatorType> DetectionRule(Map<String, Boolean> rulesDetection,Map<String, Boolean> rulesExcel)
	{
		Map<String, EvaluatorType> detection = new HashMap<String, EvaluatorType>();
		
		int rulesSize = rulesDetection.size();
		for(String rulename : rulesDetection.keySet())
		{
			boolean ruleDetctionvalue = rulesDetection.get(rulename);
			boolean ruleExcelvalue = rulesExcel.get(rulename);
			
			if(ruleDetctionvalue == true && ruleExcelvalue == true)
				detection.put(rulename, EvaluatorType.TP);
			else if(ruleDetctionvalue == true && ruleExcelvalue == false)
				detection.put(rulename, EvaluatorType.FP);
			else if(ruleDetctionvalue == false && ruleExcelvalue == true)
				detection.put(rulename, EvaluatorType.FN);
			else if(ruleDetctionvalue == false && ruleExcelvalue == false)
				detection.put(rulename, EvaluatorType.TN);
		}
		
		return detection;
			
	}

}
