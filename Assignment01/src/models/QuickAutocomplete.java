package models;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import edu.princeton.cs.introcs.Stopwatch;
import util.BinarySearch;

public class QuickAutocomplete implements AutoComplete{

	private static String url = "https://wit-computing.github.io/algorithms-2016/topic04/book-2/data/wiktionary.txt";
	private List<Term> termList;
	private Scanner input;
	
	public static void main(String[] args)
	{
		try 
		{
			new QuickAutocomplete(url);
		}
		catch (MalformedURLException e) {
			System.err.println("Error Navigating To The URL");
		} catch (IOException e) {
			System.err.println("Error While Taking In Data From URL Please Check You Internet Connection");
		}
	}
	
	public QuickAutocomplete(String url) throws MalformedURLException, IOException
	{
		if(url == null)
			throw new NullPointerException();
		Stopwatch stopwatch = new Stopwatch();
		termList = new ArrayList<Term>(new TermList(url,false).getTermList());
		
		System.out.println("Time to load terms : "+stopwatch.elapsedTime());
		input = new Scanner(System.in);
		
		//mainMenu();
	}
	
	public int menuDisplay()
	{
		System.out.println("1) Weight Of ");
		System.out.println("2) Best Match");
		System.out.println("3) k Matches");
		System.out.println("0) Exit");
		System.out.print(">> ");
		
		try{
			return input.nextInt();
		}catch (Exception e)
		{
			return -1;
		}
	}
	
	public void mainMenu()
	{
		int choice = menuDisplay();
		Stopwatch stopwatch;
		String term;
		
		while(choice != 0)
		{
			input.nextLine();
			System.out.print(">>");
			switch (choice)
			{
				case 1:
					term = input.next();
					stopwatch = new Stopwatch();
					System.out.println(weightOf(term));
					System.out.println("Time elapsed: "+stopwatch.elapsedTime());
					stopwatch = null;
				break;
				case 2:
					term = input.next().trim().toLowerCase();
					stopwatch = new Stopwatch();
					System.out.println(bestMatch(term));
					System.out.println("Time elapsed: "+stopwatch.elapsedTime());
					stopwatch = null;
				break;
				case 3:
					String prefix = input.next();
					System.out.print("(k value)>>");
					int k;
					try{
						k=input.nextInt();
					}catch (Exception e)
					{
						k=1;
					}
					stopwatch = new Stopwatch();
					System.out.println(matches(prefix,k));
					System.out.println("Time elapsed: "+stopwatch.elapsedTime());
					stopwatch = null;
				break;
				default:
					System.err.println("Invalid Input");
				break;
			}
			input.nextLine();
			choice = menuDisplay();
		}
		
		System.out.println("Shutting Down");
		System.exit(0);
	}
	
	public double weightOf(String term) {
		if(term == null)
			throw new NullPointerException();
		//termsStrings = termList.stream().map(Term::getTerm).collect(Collectors.toList());
		int index = BinarySearch.binaryTermSearch(termList, term);
		if (index != -1)
		{
			return termList.get(index).getWeight();
		}
		else
		{
			return 0.0;
		}
	}

	public String bestMatch(String prefix) {
		if(prefix == null)
			throw new NullPointerException();
		//termsStrings = termList.stream().map(Term::getTerm).collect(Collectors.toList());
		//System.out.println(termsStrings.size());
		List<Term> terms = BinarySearch.binaryPrefixSearch(termList, prefix);
		if(terms != null && terms.size() > 0)
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
		//termsStrings = termList.stream().map(Term::getTerm).collect(Collectors.toList());
		List<Term> terms = BinarySearch.binaryPrefixSearch(termList, prefix.trim().toLowerCase());
		List<String> result = new ArrayList<String>();
		ListIterator<Term> iterator = null;
		if(k < 1)
		{
			throw new IllegalArgumentException();
		}
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
