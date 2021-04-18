package CodeSmellDetectionEvaluator;

import java.util.ArrayList;
import java.util.List;

public class PackageEvaluator {

	private String name;
	private List<ClassEvaluator> classelst;
	
	public PackageEvaluator()
	{
		classelst = new ArrayList<ClassEvaluator>();
	}
	
	public PackageEvaluator(String name)
	{
		this.name = name;
		classelst = new ArrayList<ClassEvaluator>();
	}
	
	public PackageEvaluator(String name, List<ClassEvaluator> classelst)
	{
		this.name = name;
		classelst = classelst;
	}
	
	
	public void addClass(ClassEvaluator classToAdd)
	{
		classelst.add(classToAdd);
	}
	
}
