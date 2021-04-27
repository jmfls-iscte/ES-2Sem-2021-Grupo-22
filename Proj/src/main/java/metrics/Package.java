package metrics;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;

public class Package {
	
	private String name;
	private List<Class> arrayClasses;
	
  
  public Package()
	{
		arrayClasses = new ArrayList<Class>();
	}
  
	public Package(String name,File file) {
		this.name=name;
		//System.out.println(name);
		arrayClasses = new ArrayList<Class>();
	
		List<File> files= Arrays.asList(file.listFiles()).stream().filter(x->FilenameUtils.getExtension(x.getAbsolutePath()).equals("java")).collect(Collectors.toList());
		for(File x: files) {
			try {
				arrayClasses.addAll(JavaParser.ParseFile(x));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public Package(String name)
	{
		this.name = name;
		arrayClasses = new ArrayList<Class>();
	}
	
	public String getName_Package() {
		return name;
	}
	
	public List<Class> getClass_list(){
		return arrayClasses;
	}
	
	public Class get_ClassByName(String Classname)
	{
		for(Class c:arrayClasses)
		{
			if(c.getName_Class()==Classname)
				return c;
		}
		
		return null;
	}
	
	public void setName_Package(String name)
	{
		this.name=name;
	}
	
	public void addClass(Class classToAdd)
	{
		if(!verifyExistsClass(classToAdd))
			arrayClasses.add(classToAdd);
	}
	
	private boolean verifyExistsClass(Class classToVerify)
	{
		//TO DO
		return false;
	}

}
