package CodeSmellDetectionEvaluator;

import java.util.List;
import metrics.*;
import metrics.Class;
import metrics.Package;

public class CodeSmellDetectionEvaluator {

	private int truePositive;
	private int falsePositive;
	private int trueNegative;
	private int falseNegative;
	private int totalDetetions;

	private List<Package> packagesDetection;
	private List<Package> packagesExcel;

	private List<PackageEvaluator> packagesEvaluator;

	public CodeSmellDetectionEvaluator(List<Package> packagesDetection, List<Package> packagesExcel) {
		this.packagesDetection = packagesDetection;
		this.packagesExcel = packagesExcel;

		evaluateCodeSmellsDetection();
	}

	private boolean verifyPossibleEvaluate() {
		return verifyPackages() && verifyClassesAndMethods();

	}

	private boolean verifyPackages() {
		if (packagesDetection.size() != packagesExcel.size()) {
			return false;
		}
		return true;
	}

	private boolean verifyClassesAndMethods() {
		for(int i=0; i<packagesDetection.size();i++)
		{
			if(packagesDetection.get(i).getClass_list().size() != packagesExcel.get(i).getClass_list().size() )
				return false;
			else
			{
				for(int j=0; j<packagesDetection.get(i).getClass_list().size(); j++)
				{
					if(!VerifyMethods(packagesDetection.get(i).getClass_list().get(j),packagesExcel.get(i).getClass_list().get(j)))
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
		if(!verifyPossibleEvaluate())
			throw new IllegalArgumentException();
		
		

	}

}
