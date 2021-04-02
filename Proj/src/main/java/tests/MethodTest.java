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
		assertEquals(1, m1.get_name_code_Smells().size());
		
		Assertions.assertNotNull(m2);
		assertEquals("method_test", m2.getName_method());
		assertEquals(1, m2.get_name_code_Smells().size());
	}
	
	@Test
	public void getMethodIDTest() {
		assertEquals(0, m1.getMethodID());
		
	}
	
	@Test
	public void getLOC_methodTest() {   //testa o get e o set
		m1.setLOC_method(1);
		assertEquals(1, m1.getLOC_method());
		
	}
	
	@Test
	public void getCYCLO_methodTest() {   //testa o get e o set
		m1.setCYCLO_method(1);
		assertEquals(1, m1.getCYCLO_method());
	}
	
	@Test
	public void getIs_Long_methodTest() {   //no futuro, é preciso criar mais 2 metodos a testar o true e o false
		assertNull(m1.getIs_Long_method());
	}
	
	
}
