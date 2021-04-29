package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import metrics.Metrics;

class NunoMetricsTest {

	static Metrics metric;
	
	@BeforeAll
	static void setUpBeforeClass() {
		metric = new Metrics (0,"package","class","method",1,2,3,false,4,5,true);
	}
	
	
	@Test
	void testMetrics() {
		assertNotNull(metric);
	}

	@Test
	void testGetMethodID() {
		assertEquals(0, metric.getMethodID());
	}

	@Test
	void testGetPackage() {
		assertEquals("package", metric.getPackage());
	}

	@Test
	void testGetClass1() {
		assertEquals("class", metric.getClass1());
	}

	@Test
	void testGetMethod() {
		assertEquals("method", metric.getMethod());
	}

	@Test
	void testGetNOM_class() {
		assertEquals(1, metric.getNOM_class());
	}

	@Test
	void testGetLOC_class() {
		assertEquals(2, metric.getLOC_class());
	}

	@Test
	void testGetWMC_class() {
		assertEquals(3, metric.getWMC_class());
	}

	@Test
	void testGetIs_God_Class() {
		assertEquals(false, metric.is_God_Class);
	}

	@Test
	void testGetLOC_method() {
		assertEquals(4, metric.getLOC_method());
	}

	@Test
	void testGetCYCLO_method() {
		assertEquals(5, metric.getCYCLO_method());
	}

	@Test
	void testGetIs_Long_Method() {
		assertEquals(true, metric.is_Long_Method);
	}

}
