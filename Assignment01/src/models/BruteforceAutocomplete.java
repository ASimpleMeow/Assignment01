package models;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;


import edu.princeton.cs.introcs.Stopwatch;

public class BruteforceAutocomplete implements AutoComplete{

	private static String url = "https://wit-computing.github.io/algorithms-2016/topic04/book-2/data/wiktionary.txt";
	//private TermList termList;
	private TreeSet<Term> termList;
	private Scanner input;
	
	public static void main(String[] args)
	{
		try 
		{
			new BruteforceAutocomplete(url);
		}
		catch (MalformedURLException e) {
			System.err.println("Error Navigating To The URL");
		} catch (IOException e) {
			System.err.println("Error While Taking In Data From URL Please Check You Internet Connection");
		}
	}
	
	public BruteforceAutocomplete(String url) throws MalformedURLException, IOException
	{
		if(url == null)
			throw new NullPointerException();
		Stopwatch stopwatch = new Stopwatch();
		termList = new TermList(url,true).getTermList();
		System.out.println("Time to load terms : "+stopwatch.elapsedTime());
		input = new Scanner(System.in);
		System.out.println(termList.size());
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
					term = input.next();
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
		Iterator<Term> iterator = termList.iterator();
		
		while(iterator.hasNext())
		{
			Term newTerm = iterator.next();
			if(newTerm.getTerm().equals(term.trim().toLowerCase()))
				return newTerm.getWeight();
		}
		return 0.0;
	}

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
