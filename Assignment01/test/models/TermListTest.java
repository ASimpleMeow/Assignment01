package models;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;


public class TermListTest {
	
	TermList bruteTermList;
	TermList quickTermList;
	
	@Before
	public void setup() throws MalformedURLException, IOException
	{
		bruteTermList = new TermList("https://wit-computing.github.io/algorithms-2016/topic04/book-2/data/wiktionary.txt",true);
		quickTermList = new TermList("https://wit-computing.github.io/algorithms-2016/topic04/book-2/data/wiktionary.txt",false);
	}
	
	@After
	public void tearDown()
	{
		bruteTermList = null;
		quickTermList = null;
	}
	
	@Test(expected=NullPointerException.class)
	public void testConstructorException() throws NumberFormatException, IOException
	{
		new TermList(null,true);
	}
	
	@Test
	public void testGetTermList() 
	{
		assertEquals(10000,bruteTermList.getTermList().size(),0.001);
		assertEquals(10000,quickTermList.getTermList().size(),0.001);
	}

}
