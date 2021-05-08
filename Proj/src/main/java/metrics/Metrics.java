package metrics;

//
/**
 * Contains metrics information
 */
public class Metrics {

	public int MethodID;
	public String Package;
	public String Class1;
	public String Method;
	public int NOM_class;
	public int LOC_class;
	public int WMC_class;
	public boolean is_God_Class;
	public int LOC_method;
	public int CYCLO_method;
	public boolean is_Long_Method;

	/**
	 * Creates a class Metrics and initializes all the parameters
	 * 
	 * @param MethodID       the method id
	 * @param Package        name of the package
	 * @param Class1         name of the class
	 * @param Method         name of the method
	 * @param NOM_class      number of methods of the class
	 * @param LOC_class      number of lines of code of the class
	 * @param WMC_class      sum of cycles in the class's methods
	 * @param is_God_Class   boolean isGodClass
	 * @param LOC_method     the number of lines of code of the method
	 * @param CYCLO_method   the number of cycles of the method
	 * @param is_Long_Method boolean isLong method
	 * 
	 */
	public Metrics(int MethodID, String Package, String Class1, String Method, int NOM_class, int LOC_class,
			int WMC_class, boolean is_God_Class, int LOC_method, int CYCLO_method, boolean is_Long_Method) {

		this.MethodID = MethodID;
		this.Package = Package;
		this.Class1 = Class1;
		this.Method = Method;
		this.NOM_class = NOM_class;
		this.LOC_class = LOC_class;
		this.WMC_class = WMC_class;
		this.is_God_Class = is_God_Class;
		this.LOC_method = LOC_method;
		this.CYCLO_method = CYCLO_method;
		this.is_Long_Method = is_Long_Method;
	}

	/**
	 * Gets the id of the method
	 * 
	 * @return the id of the method
	 */
	public int getMethodID() {
		return MethodID;
	}

	/**
	 * Gets the name of the package
	 * 
	 * @return the name of the package
	 */
	public String getPackage() {
		return Package;
	}

	/**
	 * Gets the name of the class
	 * 
	 * @return the name of the class
	 */
	public String getClass1() {
		return Class1;
	}

	/**
	 * Gets the name of the method
	 * 
	 * @return the name of the method
	 */
	public String getMethod() {
		return Method;
	}

	/**
	 * Gets the number of methods in the class
	 * 
	 * @return the number of methods in the class
	 */
	public int getNOM_class() {
		return NOM_class;
	}

	/**
	 * Gets the number of lines of code in the class
	 * 
	 * @return the number of methods in the class
	 */
	public int getLOC_class() {
		return LOC_class;
	}

	/**
	 * Gets the sum of cycles in the class's methods
	 * 
	 * @return the sum of cycles in the class's methods
	 */
	public int getWMC_class() {
		return WMC_class;
	}

	/**
	 * Gets a boolean isGodClass
	 * 
	 * @return true if isGodClass
	 */
	public boolean getIs_God_Class() {
		return is_God_Class;
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
	 * Gets the number of cycles in the method
	 * 
	 * @return the number of cycles in the method
	 */
	public int getCYCLO_method() {
		return CYCLO_method;
	}

	/**
	 * Gets a boolean isLongMethod
	 * 
	 * @return true if isLongMethod
	 */
	public boolean getIs_Long_Method() {
		return is_Long_Method;
	}

}
