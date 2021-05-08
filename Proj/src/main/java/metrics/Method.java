package metrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Method {
	
	private String name;
	private Map<String, Boolean> code_Smells= new HashMap<String, Boolean>();
	private int LOC_method;
	private int CYCLO_method;
	private int begin;
	private int end;
	private int method_id;
	
	
	public Method() {
		code_Smells = new HashMap<String, Boolean>();
	//	getCode_Smells().put("is_Long_Method", null);
	}
	
	public Method(String method_name) {
		this.name = method_name;
		code_Smells = new HashMap<String, Boolean>();
	//	getCode_Smells().put("is_Long_Method", null);
	}

	public String getName_method() {
		return name;
	}
	
	
	public ArrayList<String> get_name_code_Smells()
	{
		ArrayList<String> keys =  new ArrayList<String>();
		keys.addAll(getCode_Smells().keySet());
		return keys;
	}
	
	public int getLOC_method() {
		return LOC_method;
	}
	
	public int getCYCLO_method() {
		return CYCLO_method;
	}
	
	
	public Boolean getIs_Long_method() {
		return code_Smells.get("Is_Long_Method");
	}

	public void setLOC_method(int LOC_method) {
		this.LOC_method = LOC_method;
	}

	public void setCYCLO_method(int CYCLO_method) {
		this.CYCLO_method = CYCLO_method;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public void addSmell(String smell, boolean value) {
		getCode_Smells().put(smell, value);
	}

	public int getMethod_id() {
		return method_id;
	}

	public void setMethod_id(int method_id) {
		this.method_id = method_id;
	}


	public Map<String, Boolean> getCode_Smells() {
		return code_Smells;
}
	public Boolean getCsByName(String string) {
		return code_Smells.get(string);
	}
}