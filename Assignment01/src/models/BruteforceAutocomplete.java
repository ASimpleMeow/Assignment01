package models;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;


/**
 * BruteforceAutocomplete implements AutoComplete interface however the implementation
 * for its methods is don't in a less efficient manner than the QuickAutocomplete.
 * It will auto-complete a word from the prefix you give it and output the data in its
 * descending order of weight.
 * 
 * @author Oleksandr Kononov
 *
 */
public class BruteforceAutocomplete implements AutoComplete{

	//Declaring private variables
	private TreeSet<Term> termList;
	
	/**
	 * The constructor for this class will instantiate all the variables
	 * 
	 * @param url
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public BruteforceAutocomplete(String url) throws MalformedURLException, IOException
	{
		if(url == null)
			throw new NullPointerException();
		termList = new TermList(url,true).getTermList();
	}
	
	/**
	 * This method will take in a term string and if it exists in the
	 * termList then it will find it and return the terms weight
	 * 
	 * @param term
	 * @return double - the weight of the term
	 */
	public double weightOf(String term) {
		if(term == null)
			throw new NullPointerException();
		Iterator<Term> iterator = termList.iterator();
		
		while(iterator.hasNext())
		{
			Term newTerm = iterator.next();
			if(newTerm.getTerm().equals(term.trim().toLowerCase()))
				return newTerm.getWeight();
		}
		return 0.0;
	}

	/**
	 * This method will take in a prefix for a term and will use it 
	 * to find the full term in the termList
	 * 
	 * @param prefix
	 * @return String - the best matching String
	 */
	public String bestMatch(String prefix) {
		if(prefix == null)
			throw new NullPointerException();
		Iterator<Term> iterator = termList.iterator();
		
		while(iterator.hasNext())
		{
			Term newTerm = iterator.next();
			if(newTerm.getTerm().startsWith(prefix.trim().toLowerCase()))
			{
				return newTerm.getTerm();
			}
		}
		return null;
	}

	/**
	 * This method takes in a prefix for a term and will find k terms which have that
	 * prefix, returning an Iterable of String
	 * 
	 * @param prefix
	 * @param k
	 * @return Iterable<String>
	 */
	public Iterable<String> matches(String prefix, int k) {
		if(prefix == null)
			throw new NullPointerException();
		Iterator<Term> iterator = termList.iterator();
		ArrayList<String> result = new ArrayList<String>();
		if (k < 1)
		{
			throw new IllegalArgumentException();
		}
		while(iterator.hasNext())
		{
			Term newTerm = iterator.next();
			if(newTerm.getTerm().startsWith(prefix.toLowerCase()) && k>0)
			{
				result.add(newTerm.getTerm());	
				k--;
			}
		}
		return  result;
	}
	
}
