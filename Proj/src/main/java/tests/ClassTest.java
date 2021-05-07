package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import metrics.Method;
import metrics.Class;


class ClassTest {
		static Class c1;
		static Class c2;
		static Method m1;
		static Method m2;
				
		@BeforeAll
		static void setUpBeforeClass() throws Exception{
			c1 = new Class();
			c2 = new Class("test");
			m1 = new Method("method_test");
			m2 = new Method("method_test_fail");
			
			c1.addMethod(m1);
		}
		
		@Test
		public void ClassConstructorTest() {
			
			Assertions.assertNotNull(c1);
			assertEquals(1, c1.getMethod_list().size());
			assertEquals(1, c1.get_name_code_Smells().size());
			
			Assertions.assertNotNull(c2);
			assertEquals("test", c2.getName_Class());
			assertEquals(0, c2.getMethod_list().size());
			assertEquals(1, c2.get_name_code_Smells().size());
			
		}
		
		@Test
		public void getName_ClassTest() {
			assertEquals("test", c2.getName_Class()); 
		}
		
		
		@Test
		public void getMethod_listTest() {
			assertNotNull(c1.getMethod_list());
		}
		
		@Test
		public void get_MethodByNameTest() { 
			assertEquals(m1, c1.get_MethodByName("method_test"));
		}
		
		@Test
		public void get_MethodByNameTestFail() { 
			assertNotEquals(m1, c1.get_MethodByName("method_test_fail"));
		}
		
		@Test
		public void get_MethodByNameTestFail_2() { 
			assertEquals(null, c1.get_MethodByName("method_test_fail"));
		}
		
		@Test
		public void getNOM_classTest() {  
			c1.setNOM_class(0);
			assertEquals(0, c1.getNOM_class());
		}	
		
		@Test
		public void getLOC_classTest() {  
			c1.setLOC_class(0);
			assertEquals(0, c1.getLOC_class());
		}	
		
		@Test
		public void getWMC_classTest() {  
			c1.setWMC_class(0);
			assertEquals(0, c1.getWMC_class());
		}
		
		@Test
		public void set_NameTest() {  
			c1.set_Name("test_new");
			assertEquals("test_new", c1.getName_Class());
		}
		

		@Test
		public void addMethodTestFail() { 
			c1.addMethod(m1);
			assertEquals(1, c1.getMethod_list().size());
		}
		
		@Test
		public void verifyExistsMethodTestFail() {  
			assertTrue(c1.verifyExistsMethod(m1));
		}
		
		@Test
		public void verifyExistsMethodTest() { 
			assertFalse(c1.verifyExistsMethod(m2));
		}
		
		@Test
		public void getEndTest() { 
			c1.setEnd(0);
			assertEquals(0, c1.getEnd());
		}
		
		@Test
		public void BeginTest() { 
			c1.setBegin(0);
			assertEquals(0, c1.getBegin());
		}
		
		@Test
		public void getMethodsTest() {
			assertEquals(1, c1.getMethods().size());
		}
		
		@Test
		public void addSmellTest() {
			c1.addSmell("smell_test", true);
			assertEquals(2, c1.get_name_code_Smells().size());
		}
		@Test
		public void getCsByNameTest() {
			assertNull(c1.getCsByName("test"));
		}

}
