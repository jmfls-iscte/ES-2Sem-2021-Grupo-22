package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import CodeSmellDetectionEvaluator.CodeSmellDetectionEvaluator;
import metrics.Package;

class CodeSmellDetectionEvaluatorTest {

	static CodeSmellDetectionEvaluator csde;
	static List<Package> packagesDetectionlst;
	static List<Package> packagesExcellst;
	static Package pg1 = new Package(); 
	static Package pg2 = new Package(); 


	
	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		//packagesDetectionlst.add(pg1);
		//packagesDetectionlst.add(pg2);
	}
	
	
	@Test
	void test() {
		csde = new CodeSmellDetectionEvaluator(packagesDetectionlst, packagesExcellst);
		csde.main(null);
	}

}
