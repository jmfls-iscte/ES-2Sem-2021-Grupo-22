package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import metrics.Class;
import metrics.ClassParser;

class ClassParserTest { 
	
	static ClassParser class_parser = new ClassParser();
	
	static ArrayList<Class> classes = new ArrayList<Class>();
	
	static Class c1 = new Class();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		classes.add(c1);
	}
	
	@Test
	void setCuTest() { 
		class_parser.setClasses(classes);
		assertEquals(classes, class_parser.getClasses());
	}
}
