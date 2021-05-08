package metrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Represents an instance of a Method in a java file
 * 
 */
public class Method {

	private String name;
	private Map<String, Boolean> code_Smells = new HashMap<String, Boolean>();
	private int LOC_method;
	private int CYCLO_method;
	private int begin;
	private int end;
	private int method_id;

	/**
	 * Creates a Method without parameters the specified name
	 */
	public Method() {
		code_Smells = new HashMap<String, Boolean>();
	}

	/**
	 * Creates a Method with a specified name
	 * 
	 * @param method_name the Method's first name
	 */
	public Method(String method_name) {
		this.name = method_name;
		code_Smells = new HashMap<String, Boolean>();
	}

	/**
	 * Gets the Method's name
	 * 
	 * @return A string representing theMethod's name
	 */
	public String getName_method() {
		return name;
	}

	/**
	 * Creates an ArrayList with the name of all code smells associated
	 * 
	 * @return an ArrayList with the name of all code smells associated
	 */
	public ArrayList<String> get_name_code_Smells() {
		ArrayList<String> keys = new ArrayList<String>();
		keys.addAll(getCode_Smells().keySet());
		return keys;
	}

	/**
	 * Gets the number of lines of code in the method
	 * 
	 * @return the number of lines of code in the method
	 */
	public int getLOC_method() {
		return LOC_method;
	}

	/**
	 * Gets the number of cycles(if,case,for,,while...) in the method
	 * 
	 * @return the number of cycles in the method
	 */
	public int getCYCLO_method() {
		return CYCLO_method;
	}

	/**
	 * Gets boolean if method Is_Long_Method
	 * 
	 * @return true if method Is_Long_Method
	 */
	public Boolean getIs_Long_method() {
		return code_Smells.get("Is_Long_Method");
	}

	/**
	 * Gets the number of lines of code in the method
	 * 
	 * @param LOC_method number of lines of code
	 */
	public void setLOC_method(int LOC_method) {
		this.LOC_method = LOC_method;
	}

	/**
	 * Sets the number of cycles(if,case,for,,while...) in the method
	 * 
	 * @param CYCLO_method number of cycles in the method
	 */
	public void setCYCLO_method(int CYCLO_method) {
		this.CYCLO_method = CYCLO_method;
	}

	/**
	 * Gets the number of the first line where the code of the method starts
	 * 
	 * @return the number of the first line where the code of the method starts
	 */
	public int getBegin() {
		return begin;
	}

	/**
	 * Associates a number representing the first line where the code of the class
	 * starts with the class.
	 * 
	 * @param begin of the lines of code of the method
	 */
	public void setBegin(int begin) {
		this.begin = begin;
	}

	/**
	 * Gets the number of the line where the code of the method ends
	 * 
	 * @return the number of the line where the code of the method ends
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * Associates a number representing the last line where the code of the class
	 * 
	 * @param end of the line of code of the method
	 */
	public void setEnd(int end) {
		this.end = end;
	}

	/**
	 * Adds a code smell to the method
	 * 
	 * @param smell name
	 * @param value
	 */
	public void addSmell(String smell, boolean value) {
		getCode_Smells().put(smell, value);
	}

	/**
	 * Gets the methods id
	 * 
	 * @return id of the method
	 */
	public int getMethod_id() {
		return method_id;
	}

	/**
	 * Sets the method id
	 * 
	 * @param method_id id of the method
	 */
	public void setMethod_id(int method_id) {
		this.method_id = method_id;
	}

	/**
	 * Gets a map of code smells with a boolean that represents if the code smell is
	 * present in the method
	 * 
	 * @return the number of the first line where the code of the method starts
	 */
	public Map<String, Boolean> getCode_Smells() {
		return code_Smells;
	}

	/**
	 * Gets a map of code smells with a boolean that represents if the code smell is
	 * present in the method
	 * 
	 * @param string name of the code smell
	 * @return true if the code smell is present in the method
	 */
	public Boolean getCsByName(String string) {
		return code_Smells.get(string);
	}
}