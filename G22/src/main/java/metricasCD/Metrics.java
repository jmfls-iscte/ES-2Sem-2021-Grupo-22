package metricasCD;

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
	
	public int getMethodID() {
		return MethodID;
	}

	public String getPackage() {
		return Package;
	}

	public String getClass1() {
		return Class1;
	}

	public String getMethod() {
		return Method;
	}

	public int getNOM_class() {
		return NOM_class;
	}

	public int getLOC_class() {
		return LOC_class;
	}

	public int getWMC_class() {
		return WMC_class;
	}

	public boolean getIs_God_Class() {
		return is_God_Class;
	}

	public int getLOC_method() {
		return LOC_method;
	}

	public int getCYCLO_method() {
		return CYCLO_method;
	}

	public boolean getIs_Long_Method() {
		return is_Long_Method;
	}
	
	
	
	
}
