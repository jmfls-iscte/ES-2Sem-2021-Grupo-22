package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import metrics.Package;
import metrics.Class;


class PackageTest {
	
	static Package pg1;
	static Package pg2;
	static Package pg3;
	static File file;
	static Class c1;
	static Class c2;


	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		file = new File("file");
		pg1 = new Package();
		pg2 = new Package("test_package_2");
		c1 = new Class("class_test");
		c2 = new Class("class_test_fail");

		pg2.addClass(c1);
		pg2.addClass(c2);


		//pg3 = new Package("test_package_3", file); //falta testar o List e o for 
	}
	
	@Test
	public void PackageConstructorTest() {
		Assertions.assertNotNull(pg1);
		Assertions.assertNotNull(pg1.getClass_list());
		assertEquals(0, pg1.getClass_list().size());

		Assertions.assertNotNull(pg2);
		assertEquals("test_package_2", pg2.getName_Package());
		Assertions.assertNotNull(pg2.getClass_list());
		assertEquals(2, pg2.getClass_list().size());
		

		/*Assertions.assertNotNull(pg3);
		assertEquals("test_package_3", pg3.getName_Package());
		Assertions.assertNotNull(pg3.getClass_list());
		assertEquals(0, pg3.getClass_list().size());*/

	}
	
	@Test
	public void getName_PackageTest() { //esta
		assertEquals("test_package_2", pg2.getName_Package());
	}
	
	@Test
	public void getClass_listTest(){ //esta
		assertNotNull(pg1.getClass_list()); //tavez mudar para assertEquals
	}
	
	@Test
	public void ClassByNameTestNull() {
		assertNull(pg1.get_ClassByName("null"));
	}

	@Test
	public void ClassByNameTestFound() {
		assertEquals("class_test", pg2.get_ClassByName("class_test").getName_Class());
	}
		
	@Test
	public void ClassByNameTestFail() {
		assertNotEquals("class_test", pg2.get_ClassByName("class_test_fail").getName_Class());
	}

	@Test
	public void setName_PackageTest() {
		pg1.setName_Package("test_package_1");
		assertEquals("test_package_1", pg1.getName_Package());
	}
	@Test
	public void addClassTest() {
		pg2.addClass(c1);
		assertEquals(2, pg2.getClass_list().size()); //dá erro porque o VerifyExistsClass ainda nao está implementado 
	}

}
