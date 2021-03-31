package metrics;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Class {
	
	private String name;
	private ArrayList<Method> arrayMethods;
	private int NOM_class;
	private int LOC_class;
	private int WMC_class;
	HashMap<String, Boolean> code_Smells;
	
	
	public Class()
	{
		arrayMethods = new ArrayList<Method>();
		code_Smells = new HashMap<String, Boolean>();
		code_Smells.put("is_God_Class", null);
		
	}
	
	public Class(String name)
	{
		this.name = name;
		arrayMethods = new ArrayList<Method>();
		code_Smells = new HashMap<String, Boolean>();
		code_Smells.put("is_God_Class", null);
		
	}
	
	public String getName_Class() {
		return name;
	}
	
	
	
	public List<Method> getMethod_list(){
		return arrayMethods;
	} 
	
	public Method get_MethodByName(String methodname)
	{
		for(Method m:arrayMethods)
		{
			if(m.getName_method()==methodname)
				return m;
		}
		
		return null;
	}
	
	public ArrayList<String> get_name_code_Smells()
	{
		ArrayList<String> keys =  new ArrayList<String>();
		keys.addAll(code_Smells.keySet());
		return keys;
	}
	
	
	
	public int getNOM_class() {
		return this.NOM_class;
	}
	
	public int getLOC_class() {
		return  this.LOC_class;
	}
	
	public int getWMC_class() {
		 return this.WMC_class;
	}
	
	
	public void setNOM_class(int NOM_class) {
		this.NOM_class =NOM_class;
	}
	
	public void setLOC_class(int LOC_class) {
		this.LOC_class =LOC_class;
	}
	public void setWMC_class(int WMC_class) {
		this.WMC_class =WMC_class;
	}
	
	
	public void set_Name(String name)
	{
		this.name = name;
	}
	
	public void addMethod(Method methodToAdd)
	{
		if(!verifyExistsMethod(methodToAdd))
			arrayMethods.add(methodToAdd);
	}
	
	private boolean verifyExistsMethod(Method methodToVerify)
	{
	
		for(Method m:arrayMethods)
		{
			if(m.getName_method()==methodToVerify.getName_method())
				return true;
		}
		return false;
	}
	

	
	

}
