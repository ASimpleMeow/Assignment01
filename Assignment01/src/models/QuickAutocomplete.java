package models;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import util.BinarySearch;

/**
 * QuickAutocomplete implements the AutoComplete interface and is meant to be
 * more efficient than BruteforceAutocomplete.
 * It will auto-complete a word from the prefix you give it and output the data in its
 * descending order of weight.
 * 
 * @author Oleksandr Kononov
 *
 */
public class QuickAutocomplete implements AutoComplete{

	//Declaring Variables
	private List<Term> termList;
	
	/**
	 * The constructor for this class will instantiate all the variables
	 * 
	 * @param url
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public QuickAutocomplete(String url) throws MalformedURLException, IOException
	{
		if(url == null)
			throw new NullPointerException();
		termList = new ArrayList<Term>(new TermList(url,false).getTermList());
	}
	
	/**
	 * This method will take in a term string and if it exists in the
	 * termList then it will find it using BinarySearch,
	 * sort the terms by their weight and return the terms weight
	 * 
	 * @param term
	 * @return double - the weight of the term
	 */
	public double weightOf(String term) {
		if(term == null)
			throw new NullPointerException();
		//Getting the index of the Term by performing BinarySearch
		int index = BinarySearch.binaryTermSearch(termList, term);
		if (index != -1)//-1 signifies that it couldn't find the term
		{
			return termList.get(index).getWeight();
		}
		else
		{
			return 0.0;
		}
	}

	/**
	 * This method will take in a prefix for a term and will use BinarySearch  
	 * to find the full term in the termList
	 * 
	 * @param prefix
	 * @return String - the best matching String
	 */
	public String bestMatch(String prefix) {
		if(prefix == null)
			throw new NullPointerException();
		//Returns a block of terms with the prefix
		List<Term> terms = BinarySearch.binaryPrefixSearch(termList, prefix.trim().toLowerCase());
		if(terms != null)//Making sure there are Terms in terms
		{
			terms = sortByWeight(terms);
			return terms.get(0).getTerm();
		}
		else
		{
			return null;
		}
	}

	public Iterable<String> matches(String prefix, int k) {
		if(prefix == null)
			throw new NullPointerException();
		if(k < 1)
		{
			throw new IllegalArgumentException();
		}
		//Returning a block of Terms with the same prefix
		List<Term> terms = BinarySearch.binaryPrefixSearch(termList, prefix.trim().toLowerCase());
		List<String> result = new ArrayList<String>();
		ListIterator<Term> iterator = null;
		if(terms == null)
		{
			throw new NullPointerException();
		}
		if (terms.size() > 0)
		{
			terms = sortByWeight(terms);
			iterator = terms.listIterator();
		}
		while(iterator.hasNext())
		{
			Term newTerm = iterator.next();
			if(k>0)
			{
				result.add(newTerm.getTerm());
				k--;
			}
		}
		return result;
	}
	
	/**
	 * This method will take in a list of terms and sort them 
	 * by their descending order of weight
	 * 
	 * @param terms
	 * @return List<Term>
	 */
	private List<Term> sortByWeight(List<Term> terms)
	{
		Collections.sort(terms, new Comparator<Term>()
		{
			@Override
			public int compare(Term term1, Term term2)
			{
				return new Double(term2.getWeight()).compareTo(new Double(term1.getWeight()));
			}
		});
		return terms;
	}
}
