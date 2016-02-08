package cscie599.asn1.tickexample;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TickCreatorTest extends TestCase{
	
	private double min; 
	private double max ;
	private int maxTicks ;
	private NiceScale niceScale;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		min = 100;
		max = 200;
		maxTicks = 11;
		niceScale = new NiceScale(min, max);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNiceRange() {
		assertEquals(100.0, niceScale.getRange());
	}
	
	@Test
	public void testTickInterval() {
		assertEquals(10.0, niceScale.getTickInterval(niceScale.getRange(), maxTicks));
	}
	
	@Test
	public void testNiceMin() {
		double interval = niceScale.getTickInterval(niceScale.getRange(), maxTicks);
		assertEquals(100.0, niceScale.getNiceMin(interval));
	}
	
	@Test
	public void testNiceMax() {
		double interval = niceScale.getTickInterval(niceScale.getRange(), maxTicks);
		assertEquals(200.0, niceScale.getNiceMax(interval));
	}

}
