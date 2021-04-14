package tests;

import org.junit.runner.RunWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;

@RunWith(JUnitPlatform.class)
@SelectClasses({PackageTest.class, RuleObjectTest.class, RuleTest.class, MethodTest.class, ClassTest.class, DirectoryGetterTest.class})
public class AllTests {
}


