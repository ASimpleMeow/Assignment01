package models;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import util.BinarySearch;

import org.junit.After;
import org.junit.Before;

/**
 * A JUnit Test case to test the BinarySeach class
 * 
 * @author Oleksandr Kononov
 *
 */
public class BinarySearchTest {

	//Will act as a List of Term
	private List<Term> testList;
	
	/**
	 * Sets up the list before every test
	 */
	@Before
	public void setup()
	{
		testList = new ArrayList<Term>();
		testList.add(new Term("acat",100));
		testList.add(new Term("bkitten",150));
		testList.add(new Term("bkitten2",149));
		testList.add(new Term("bkitten3",151));
		testList.add(new Term("ctiger",99));
		testList.add(new Term("dlion",80));
		testList.add(new Term("ecoolcat",120));
		testList.add(new Term("fluffycat",110));
	}
	
	/**
	 * Makes the list after every test null
	 */
	@After
	public void tearDown()
	{
		testList = null;
	}
	
	/**
	 * Tests the NullPointerException when searching a term
	 * for testing with a null list
	 */
	@Test(expected=NullPointerException.class)
	public void testTermSearchListException()
	{
		BinarySearch.binaryTermSearch(null, "acat");
	}
	
	/**
	 * Tests the NullPointerException when searching a term
	 * for testing with a null prefix
	 */
	@Test(expected=NullPointerException.class)
	public void testTermsSearchTermException()
	{
		BinarySearch.binaryPrefixSearch(testList, null);
	}
	
	/**
	 * Tests the NullPointerException when searching a termList
	 * for testing with a null prefix
	 */
	@Test(expected=NullPointerException.class)
	public void testPrefixSearchPrefixException()
	{
		BinarySearch.binaryPrefixSearch(testList, null);
	}
	
	/**
	 * Tests the NullPointerException when searching a term
	 * for testing with a null list
	 */
	@Test(expected=NullPointerException.class)
	public void testPrefixSearchListException()
	{
		BinarySearch.binaryPrefixSearch(null, "a");
	}
	
	/**
	 * Tests the results of binaryTermSearch which is used by weightOf
	 */
	@Test
	public void testTermSearch()
	{
		assertEquals(0, BinarySearch.binaryTermSearch(testList, "acat"));
		assertEquals(-1,BinarySearch.binaryTermSearch(testList, "bcat"));
	}
	
	/**
	 * Tests the results of the binaryPrefixSearch which is used by
	 * bestMatch and matches
	 */
	@Test
	public void testPrefixSearch()
	{
		assertEquals(1,BinarySearch.binaryPrefixSearch(testList,"a").size());
		assertEquals("acat",BinarySearch.binaryPrefixSearch(testList,"a").get(0).getTerm());
		assertEquals("fluffycat",BinarySearch.binaryPrefixSearch(testList,"f").get(0).getTerm());
		assertEquals(3,BinarySearch.binaryPrefixSearch(testList,"b").size());
		assertEquals("bkitten",BinarySearch.binaryPrefixSearch(testList,"b").get(0).getTerm());
		assertEquals("bkitten2",BinarySearch.binaryPrefixSearch(testList,"b").get(1).getTerm());
		assertEquals("bkitten3",BinarySearch.binaryPrefixSearch(testList,"b").get(2).getTerm());
	}

}
