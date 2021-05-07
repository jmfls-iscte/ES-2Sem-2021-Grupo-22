package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import CodeSmellDetectionEvaluator.ClassEvaluator;
import CodeSmellDetectionEvaluator.PackageEvaluator;

class PackageEvaluatorTest {
	
	
	static PackageEvaluator pe1;
	static PackageEvaluator pe2;
	static PackageEvaluator pe3;
	
	static ClassEvaluator ce1;
	
	static List<ClassEvaluator> classlst = new ArrayList<ClassEvaluator>();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		pe1 = new PackageEvaluator();
		pe2 = new PackageEvaluator("pe2");
		classlst.add(ce1);
	}

	@Test
	void testConstructors() {
		Assertions.assertNotNull(pe1);
		Assertions.assertNotNull(pe2);
	}
	
	@Test
	void testAddClass() {
		pe1.addClass(ce1);
		assertEquals(classlst, pe1.getClasslst());
	}

}
