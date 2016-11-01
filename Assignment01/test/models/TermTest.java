package models;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

public class TermTest {

	Term term1;
	
	@Before
	public void setup()
	{
		term1 = new Term("hello",2);
	}
	
	@After
	public void tearDown()
	{
		term1 = null;
	}
	
	@Test
	public void testConstructor() {
		Term term2 = new Term("",0);
		Term term3 = new Term("  HI  ", 1);
		
		assertEquals("",term2.getTerm());
		assertEquals(0,term2.getWeight(),0.001);
		assertEquals("hi",term3.getTerm());
		assertEquals(1,term3.getWeight(),0.001);
	}
	
	@Test (expected=NullPointerException.class)
	public void testIllegalArgumentException()
	{
		@SuppressWarnings("unused")
		Term term = new Term(null,-1);
	}
	
	@Test
	public void testGetters()
	{
		assertEquals("hello",term1.getTerm());
		assertEquals(2,term1.getWeight(),0.001);
	}
	
	@Test (expected=NullPointerException.class)
	public void testTermSettersException()
	{
		term1.setTerm(null);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testWeightSettersException()
	{
		term1.setWeight(-1);
	}
	
	@Test
	public void testSetters()
	{
		term1.setTerm("Hi");
		assertEquals("hi",term1.getTerm());
		term1.setWeight(100);
		assertEquals(100,term1.getWeight(),0.01);
	}
	
	@Test
	public void testToString()
	{
		assertEquals("Term: hello    Weight: 2.0", term1.toString());
	}

}
