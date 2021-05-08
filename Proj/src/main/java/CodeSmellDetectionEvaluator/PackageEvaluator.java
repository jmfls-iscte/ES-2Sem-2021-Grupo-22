package CodeSmellDetectionEvaluator;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Represents a package to evaluate
 *
 */
public class PackageEvaluator {

	private String name;
	private List<ClassEvaluator> classlst;

	/**
	 * Initializes PackageEvaluator
	 */
	public PackageEvaluator() {
		classlst = new ArrayList<ClassEvaluator>();
	}

	/**
	 * Initializes PackageEvaluator
	 * 
	 * @param name of package
	 */
	public PackageEvaluator(String name) {
		this.name = name;
		classlst = new ArrayList<ClassEvaluator>();
	}

	/**
	 * Initializes PackageEvaluator
	 * 
	 * @param name     of package
	 * @param classlst list of classes
	 */
	public PackageEvaluator(String name, List<ClassEvaluator> classlst) {
		this.name = name;
		this.classlst = classlst;
	}

	/**
	 * Adds a class to the package
	 * 
	 * @param classToAdd class to add
	 */
	public void addClass(ClassEvaluator classToAdd) {
		classlst.add(classToAdd);
	}

	/**
	 * Gets the list of classes
	 * 
	 * @return the list of classes
	 */
	public List<ClassEvaluator> getClasslst() {
		return classlst;
	}

	/**
	 * Gets the name of the package
	 * 
	 * @return the name of the package
	 */
	public String getName() {
		return name;
	}

}
