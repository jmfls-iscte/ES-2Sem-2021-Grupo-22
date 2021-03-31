package metrics;

import java.util.ArrayList;
import java.util.HashMap;

public class Method {
	
	
	
	private String name;
	private HashMap<String, Boolean> code_Smells;
	private int LOC_method;
	private int CYCLO_method;
	
	
	public Method() {
		code_Smells = new HashMap<String, Boolean>();
		code_Smells.put("is_Long_Method", null);
	}
	
	public Method(String method_name) {
		this.name = method_name;
		code_Smells = new HashMap<String, Boolean>();
		code_Smells.put("is_Long_Method", null);
	}

	public int getMethodID() {
		return 0;
	}
	
	public String getName_method() {
		return name;
	}
	
	
	public ArrayList<String> get_name_code_Smells()
	{
		ArrayList<String> keys =  new ArrayList<String>();
		keys.addAll(code_Smells.keySet());
		return keys;
	}
	
	public int getLOC_method() {
		return LOC_method;
	}
	
	public int getCYCLO_method() {
		return CYCLO_method;
	}
	
	
	public Boolean getIs_Long_method() {
		return code_Smells.get("is_Long_Method");
	}

	public void setLOC_method(int LOC_method) {
		this.LOC_method = LOC_method;
	}

	public void setCYCLO_method(int CYCLO_method) {
		this.CYCLO_method = CYCLO_method;
	}

}
