package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import metrics.Method;

class MethodTest {
	static Method m1;
	static Method m2;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		m1 = new Method();
		m2 = new Method("method_test");
	}

	@Test
	public void MethodConstructorTest() {
		Assertions.assertNotNull(m1);
		assertEquals(0, m1.get_name_code_Smells().size());
		
		Assertions.assertNotNull(m2);
		assertEquals("method_test", m2.getName_method());
		assertEquals(0, m2.get_name_code_Smells().size());
	}
	
	@Test
	public void getMethodIDTest() {
		assertEquals(0, m1.getMethod_id());
		
	}
	
	@Test
	public void getLOC_methodTest() {   
		m1.setLOC_method(1);
		assertEquals(1, m1.getLOC_method());
		
	}
	
	@Test
	public void getCYCLO_methodTest() {   
		m1.setCYCLO_method(1);
		assertEquals(1, m1.getCYCLO_method());
	}
	
	@Test
	public void getIs_Long_methodTest() {   
		assertNull(m1.getIs_Long_method());
	}
	
	@Test
	public void getBeginTest() {   
		m1.setBegin(0);
		assertEquals(0, m1.getBegin());
	}
	
	@Test
	public void getEndTest() {   
		m1.setEnd(1);
		assertEquals(1, m1.getEnd());
	}
	
	@Test
	public void addSmellTest() {
		m1.addSmell("smell_test", false);
		assertEquals(1, m1.get_name_code_Smells().size());
	}
	
	@Test
	public void setMethod_idTest() {
		m1.setMethod_id(1);
		assertEquals(1, m1.getMethod_id());
	}
	
	@Test
	public void getCsByNameTest() {
		assertNull(m1.getCsByName("test"));
	}
	
}
