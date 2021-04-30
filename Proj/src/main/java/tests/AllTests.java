package tests;

import org.junit.runner.RunWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;

@RunWith(JUnitPlatform.class)
@SelectClasses({PackageTest.class,  MethodTest.class, ClassTest.class, DirectoryGetterTest.class, JavaParserTest.class,
	MethodParserTest.class, ClassParserTest.class, ConstructorParserTest.class, RuleTest.class, RuleObjectTest.class,
	MetricsTest.class, ClassEvaluatorTest.class, CodeSmellDetectionEvaluatorTest.class, MethodEvaluatorTest.class})
public class AllTests {
}


