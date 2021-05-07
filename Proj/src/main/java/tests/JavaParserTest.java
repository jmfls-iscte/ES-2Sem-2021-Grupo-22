package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import metrics.*;

class JavaParserTest { 
	
	static JavaParser jp1;

	@BeforeAll
	static void setUpBeforeClass() throws Exception{		
		jp1 = new JavaParser();
		jp1.main(null);
	}
	
	@Test
	void javaParserTest() {
		assertNotNull(jp1);
	}
}
