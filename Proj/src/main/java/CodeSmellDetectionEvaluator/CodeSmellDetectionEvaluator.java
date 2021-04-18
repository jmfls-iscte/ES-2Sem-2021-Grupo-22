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
	
	public CodeSmellDetectionEvaluator(List<Package> packagesDetection,List<Package> packagesExcel)
	{
		this.packagesDetection=packagesDetection;
		this.packagesExcel = packagesExcel;
	}
	
	public List<PackageEvaluator> evaluateCodeSmellsDetection()
	{
		return null;
		
	}
	
	
	

}
