package models;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

/**
 * A JUnit Test case to test the TermList
 * 
 * @author Oleksandr Kononov
 *
 */
public class TermListTest {
	
	TermList bruteTermList;
	TermList quickTermList;
	
	/**
	 * Setting up the brute and quick TermLists before every test
	 * 
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	@Before
	public void setup() throws MalformedURLException, IOException
	{
		bruteTermList = new TermList("https://wit-computing.github.io/algorithms-2016/topic04/book-2/data/wiktionary.txt",true);
		quickTermList = new TermList("https://wit-computing.github.io/algorithms-2016/topic04/book-2/data/wiktionary.txt",false);
	}
	
	/**
	 * Null the brute and quick TermLists after every test
	 */
	@After
	public void tearDown()
	{
		bruteTermList = null;
		quickTermList = null;
	}
	
	/**
	 * Test for the NullPointerException when the String url is null
	 * 
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	@Test(expected=NullPointerException.class)
	public void testConstructorException() throws NumberFormatException, IOException
	{
		new TermList(null,true);
	}
	
	/**
	 * Test that TermList will return same size List for both 
	 * brute and quick TermLists
	 */
	@Test
	public void testGetTermList() 
	{
		assertEquals(10000,bruteTermList.getTermList().size(),0.001);
		assertEquals(10000,quickTermList.getTermList().size(),0.001);
	}

}
