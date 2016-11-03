package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.TreeSet;

import util.TermComparator;
import util.WeightComparator;

/**
 * TermList class takes in, parses and stores the data from an online url
 * 
 * @author Oleksandr Kononov
 *
 */
public class TermList {
	
	private TreeSet<Term> termList;
	
	/**
	 * Constructor for the class which will perform all the necessary tasks of
	 * preparing the data once it is called
	 * 
	 * @param url
	 * @param bruteForce
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public TermList(String url,boolean bruteForce) throws NumberFormatException, IOException
	{
		//If url is null throw an exception
		if(url == null)
			throw new NullPointerException();
		BufferedReader in = null;
		
		//Deciding on which TreeSet Comparator to use based on the boolean bruteForce
		try{
			if(bruteForce)
			{
				termList = new TreeSet<Term>(new WeightComparator());
			}
			else
				termList = new TreeSet<Term>(new TermComparator());
			
			in = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
			String inputLine;
			String delims = "\\s+"; //Will split at any whitespace (Tabs and spaces included)
			while((inputLine = in.readLine()) != null)
			{
				String[] lineTokens = inputLine.split(delims);//At index 0, gives "" which can be ignored
				if(lineTokens.length == 3)
				{
					Term newTerm = new Term(lineTokens[2],Double.parseDouble(lineTokens[1]));
					termList.add(newTerm);
				}
			}
		}
		finally{
			if(in!=null)
				in.close();
		}
	}

	/**
	 * Returns the processed data from the constructor in a TreeSet
	 * 
	 * @return TreeSet<Term>
	 */
	public TreeSet<Term> getTermList() {
		return termList;
	}
}
