package metrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents an instance of a Class in a java file
 */
public class Class {

	private String name;
	private ArrayList<Method> arrayMethods;
	private int NOM_class;
	private int LOC_class;
	private int WMC_class;
	private Map<String, Boolean> code_Smells = new HashMap<String, Boolean>();
	private int begin;
	private int end;

	/**
	 * Creates a Class without parameters
	 */
	public Class() {
		arrayMethods = new ArrayList<Method>();
		code_Smells = new HashMap<String, Boolean>();
//		getCode_Smells().put("is_God_Class", null);

	}

	/**
	 * Creates a Class with a specified name
	 * 
	 * @param name The Class's first name
	 */
	public Class(String name) {
		this.name = name;
		arrayMethods = new ArrayList<Method>();
		code_Smells = new HashMap<String, Boolean>();
//		getCode_Smells().put("is_God_Class", null);

	}

	/**
	 * Gets the class name
	 * 
	 * @return a string representing the Class's name
	 */
	public String getName_Class() {
		return name;
	}

	/**
	 * Gets a list of the Class's methods
	 * 
	 * @return a list of the Class's methods
	 */
	public List<Method> getMethod_list() {
		return arrayMethods;
	}

	/**
	 * Gets a method given its name
	 * 
	 * @param methodname name of the method
	 * @return a method given its name
	 */
	public Method get_MethodByName(String methodname) {
		for (Method m : arrayMethods) {
			if (m.getName_method().equals(methodname))
				return m;
		}

		return null;
	}

	/**
	 * Gets an arrayList of the names of the code smells
	 * 
	 * @return an arrayList of the names of the code smells
	 */
	public ArrayList<String> get_name_code_Smells() {
		ArrayList<String> keys = new ArrayList<String>();
		keys.addAll(getCode_Smells().keySet());
		return keys;
	}

	/**
	 * Gets the number of methods of the class
	 */
	public int getNOM_class() {
		return this.NOM_class;
	}

	/**
	 * Gets the number of lines of code of the class
	 */
	public int getLOC_class() {
		return this.LOC_class;
	}

	/**
	 * Gets the sum of cycles of every method associated with the class
	 */
	public int getWMC_class() {
		return this.WMC_class;
	}

	/**
	 * Associates a number to represent the number of methods of the class
	 * 
	 * @param NOM_class a number to represent the number of methods of the class
	 */
	public void setNOM_class(int NOM_class) {
		this.NOM_class = NOM_class;
	}

	/**
	 * Associates a number to represent the lines of code the class
	 * 
	 * @param LOC_class a number to represent the lines of code the class
	 */
	public void setLOC_class(int LOC_class) {
		this.LOC_class = LOC_class;
	}

	/**
	 * Associates a number to represent the sum of cycles of every method associated
	 * with the class
	 * 
	 * @param WMC_class a number to represent the sum of cycles of every method
	 *                  associated with the class
	 */
	public void setWMC_class(int WMC_class) {
		this.WMC_class = WMC_class;
	}

	/**
	 * Associates the given to the class
	 * 
	 * @param name of the new name of the class
	 */
	public void set_Name(String name) {
		this.name = name;
	}

	/**
	 * Associates the method given to the class
	 * 
	 * @param methodToAdd method to add
	 */
	public void addMethod(Method methodToAdd) {
		if (!verifyExistsMethod(methodToAdd))
			arrayMethods.add(methodToAdd);
	}

	/**
	 * Verifies if the method given is associated to the class
	 * 
	 * @param methodToVerify method to verify
	 * @return true if the method given is associated to the class
	 */
	public boolean verifyExistsMethod(Method methodToVerify) { // ANA: mudei para public

		for (Method m : arrayMethods) {
			if (m.getName_method() == methodToVerify.getName_method())
				return true;
		}
		return false;
	}

	/**
	 * Gets the number of the line where the code of the class ends
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * Associates a number representing the line where the code of the class ends
	 * @param end of class line
	 */
	public void setEnd(int end) {
		this.end = end;
	}

	/**
	 * Gets the number of the first line where the code of the class starts
	 * 
	 * @return the number of the first line where the code of the class starts
	 */
	public int getBegin() {
		return begin;
	}

	/**
	 * Associates a number representing the first line where the code of the class
	 * starts with the class
	 * @param begin of class line
	 */
	public void setBegin(int begin) {
		this.begin = begin;
	}

	/**
	 * Gets an ArrayList of the Class's methods
	 * 
	 * @return An ArrayList of the Class's methods
	 */
	public ArrayList<Method> getMethods() {
		return arrayMethods;
	}

	
	/**
	 * Adds a code smell to the class
	 * @param smell name
	 * @param value
	 */
	public void addSmell(String smell, boolean value) {
		getCode_Smells().put(smell, value);
	}

	/**
	 * Gets code smells
	 * @return a map of the code smells
	 */
	public Map<String, Boolean> getCode_Smells() {
		return code_Smells;
	}

	/**
	 * Gets a Code smell given its name
	 * 
	 * @param string name of the code smell
	 * @return a method given its name
	 */
	public Boolean getCsByName(String string) {
		return code_Smells.get(string);
	}

}