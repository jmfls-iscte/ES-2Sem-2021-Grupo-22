package tests;

import org.junit.runner.RunWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;

@RunWith(JUnitPlatform.class)
@SelectClasses({PackageTest.class,  MethodTest.class, ClassTest.class, DirectoryGetterTest.class, ParserTest.class,
	RuleTest.class, RuleObjectTest.class, ClassMetricTest.class})
public class AllTests {
}


