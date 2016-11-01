package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.TreeSet;

import util.TermComparator;
import util.WeightComparator;

public class TermList {
	
	private TreeSet<Term> termList;
	
	public TermList(String url,boolean bruteForce) throws NumberFormatException, IOException
	{
		if(url == null)
			throw new NullPointerException();
		BufferedReader in = null;
		try{
			if(bruteForce)
			{
				termList = new TreeSet<Term>(new WeightComparator());
			}
			else
				termList = new TreeSet<Term>(new TermComparator());
			in = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
			String inputLine;
			String delims = "\\s+";
			while((inputLine = in.readLine()) != null)
			{
				String[] lineTokens = inputLine.split(delims);
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

	public TreeSet<Term> getTermList() {
		return termList;
	}
}
