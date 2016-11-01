package models;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class QuickAutocompleteTest {

	private QuickAutocomplete autoComplete;
	private String url = "https://wit-computing.github.io/algorithms-2016/topic04/book-2/data/wiktionary.txt";
	@Before
	public void setup() throws MalformedURLException, IOException
	{
		autoComplete = new QuickAutocomplete(url);
	}
	
	@After
	public void tearDown()
	{
		autoComplete = null;
	}
	
	@Test(expected=NullPointerException.class)
	public void testConstructorException() throws MalformedURLException, IOException
	{
		new QuickAutocomplete(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testWeigthOfException()
	{
		autoComplete.weightOf(null);
	}
	
	@Test
	public void testWeightOf() {
		assertEquals(1007824500, autoComplete.weightOf("WAS"),0.01);
		assertEquals(0.0,autoComplete.weightOf("xyz"),0.01);
	}
	
	@Test(expected=NullPointerException.class)
	public void testBestMatchException()
	{
		autoComplete.bestMatch(null);
	}
	
	@Test
	public void testBestMatch()
	{
		assertEquals("the", autoComplete.bestMatch("t"));
		assertEquals("with", autoComplete.bestMatch("WI"));
		assertEquals("for", autoComplete.bestMatch("F"));
		assertEquals(null, autoComplete.bestMatch("xyz"));
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testMatchesException()
	{
		autoComplete.matches("th",-1).iterator();
	}
	
	@Test(expected=NullPointerException.class)
	public void testBestMatchNullException()
	{
		autoComplete.matches(null,5);
	}
	
	@Test
	public void testMatches()
	{
		Iterator<String> test = autoComplete.matches("th",5).iterator();
		String result[] = new String[5];
		int i=0;
		while(test.hasNext())
		{
			result[i]=test.next();
			i++;
		}
		
		assertEquals("the",result[0]);
		assertEquals("that",result[1]);
		assertEquals("this",result[2]);
		assertEquals("they",result[3]);
		assertEquals("their",result[4]);
	}
}
