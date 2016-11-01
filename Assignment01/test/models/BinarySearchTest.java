package models;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import util.BinarySearch;

import org.junit.Before;

public class BinarySearchTest {

	private List<Term> testList = new ArrayList<Term>();
	
	@Before
	public void setup()
	{
		testList.add(new Term("acat",100));
		testList.add(new Term("bkitten",150));
		testList.add(new Term("bkitten2",149));
		testList.add(new Term("bkitten3",151));
		testList.add(new Term("ctiger",99));
		testList.add(new Term("dlion",80));
		testList.add(new Term("ecoolcat",120));
		testList.add(new Term("fluffycat",110));
	}
	
	@Test(expected=NullPointerException.class)
	public void testTermSearchListException()
	{
		BinarySearch.binaryTermSearch(null, "acat");
	}
	
	@Test(expected=NullPointerException.class)
	public void testTermsSearchTermException()
	{
		BinarySearch.binaryPrefixSearch(testList, null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testPrefixSearchPrefixException()
	{
		BinarySearch.binaryPrefixSearch(testList, null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testPrefixSearchListException()
	{
		BinarySearch.binaryPrefixSearch(null, "a");
	}
	
	@Test
	public void testTermSearch()
	{
		assertEquals(0, BinarySearch.binaryTermSearch(testList, "acat"));
		assertEquals(-1,BinarySearch.binaryTermSearch(testList, "bcat"));
	}
	
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
