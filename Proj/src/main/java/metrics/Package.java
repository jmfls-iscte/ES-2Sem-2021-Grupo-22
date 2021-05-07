package metrics;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;

/**
 * This class represents an instance of a package in a java file
 */
public class Package {

	private String name;
	private List<Class> arrayClasses;

	/**
	 * Creates a package without parameters
	 */
	public Package() {
		arrayClasses = new ArrayList<Class>();
	}

	/**
	 * Creates a package with a specified name and an ArrayList of classes
	 * 
	 * @param name of package
	 * @param file to add its classes to this package
	 */
	public Package(String name, File file) {
		this.name = name;
		// System.out.println(name);
		arrayClasses = new ArrayList<Class>();

		List<File> files = Arrays.asList(file.listFiles()).stream()
				.filter(x -> FilenameUtils.getExtension(x.getAbsolutePath()).equals("java"))
				.collect(Collectors.toList());
		for (File x : files) {
			try {
				arrayClasses.addAll(JavaParser.ParseFile(x));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Creates a package with a specified name
	 * 
	 * @param name of package
	 */
	public Package(String name) {
		this.name = name;
		arrayClasses = new ArrayList<Class>();
	}

	/**
	 * Gets the name of the package
	 * 
	 * @return the name of the package
	 */
	public String getName_Package() {
		return name;
	}

	/**
	 * Gets a list of classes in the package
	 * 
	 * @return list of classes in the package
	 */
	public List<Class> getClass_list() {
		return arrayClasses;
	}

	/**
	 * Gets a class given its name
	 * 
	 * @param Classname name of the class
	 * @return a class given its name
	 */
	public Class get_ClassByName(String Classname) {
		for (Class c : arrayClasses) {
			if (c.getName_Class().equals(Classname))
				return c;
		}

		return null;
	}

	/**
	 * Sets a class given a name
	 * 
	 * @param name of the class
	 */
	public void setName_Package(String name) {
		this.name = name;
	}

	/**
	 * Adds a class to this package
	 * 
	 * @param classToAdd class to add
	 */
	public void addClass(Class classToAdd) {
		if (!verifyExistsClass(classToAdd))
			arrayClasses.add(classToAdd);
	}

	/**
	 * Verifies if the class given is associated to this package
	 * 
	 * @param classToVerify class to verify
	 */
	private boolean verifyExistsClass(Class classToVerify) {
		return arrayClasses.contains(classToVerify);
	}

}
