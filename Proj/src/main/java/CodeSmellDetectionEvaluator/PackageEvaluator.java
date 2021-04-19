package CodeSmellDetectionEvaluator;

import java.util.ArrayList;
import java.util.List;

public class PackageEvaluator {

	private String name;
	private List<ClassEvaluator> classlst;
	
	public PackageEvaluator()
	{
		classlst = new ArrayList<ClassEvaluator>();
	}
	
	public PackageEvaluator(String name)
	{
		this.name = name;
		classlst = new ArrayList<ClassEvaluator>();
	}
	
	public PackageEvaluator(String name, List<ClassEvaluator> classlst)
	{
		this.name = name;
		classlst = classlst;
	}
	
	
	public void addClass(ClassEvaluator classToAdd)
	{
		classlst.add(classToAdd);
	}
	
	public List<ClassEvaluator> getClasslst()
	{
		return classlst;
	}
	
	
	
}
