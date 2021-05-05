package tests;

import org.junit.runner.RunWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;

@RunWith(JUnitPlatform.class)
@SelectClasses({ClassEvaluatorTest.class, 
	ClassParserTest.class, 
	ClassTest.class,  
	CodeSmellDetectionEvaluatorTest.class, 
	ConstructorParserTest.class, 
	DirectoryGetterTest.class, 
	ExcelReadTest.class,
	ExcelWriteTest.class,
	JavaParserTest.class,
	MethodEvaluatorTest.class,
	MethodParserTest.class,
	MethodTest.class,
	MetricsTest.class,
	PackageEvaluatorTest.class,
	PackageTest.class,  
	RuleEvaluatorTest.class, 
	RuleObjectTest.class, 
	RuleTest.class,
	SaveLoadRuleTest.class})
	  	
	
public class AllTests {
}


