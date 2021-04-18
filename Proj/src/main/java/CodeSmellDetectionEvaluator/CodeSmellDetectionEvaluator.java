package CodeSmellDetectionEvaluator;

import java.util.List;
import metrics.*;
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
	
	public CodeSmellDetectionEvaluator(List<Package> packagesDetection,List<Package> packagesExcel)
	{
		this.packagesDetection=packagesDetection;
		this.packagesExcel = packagesExcel;
		
		evaluateCodeSmellsDetection();
	}
	
	private  void evaluateCodeSmellsDetection()
	{
		/*
		 * Percorre todos os packages, e verifica as regras da classes e dos metodos, tanto da deteção como do excel 
		 * e compara os resultados para verificar qual dos 4 tipos é TP, TN, FP, FN.
		 * 
		 */
		
	}
	
	
	

}
