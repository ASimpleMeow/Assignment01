package models;

public interface AutoComplete {
	/**
	 * Returns the weight of the term, or 0.0 if no such term
	 * exists.
	 */
	public double weightOf(String term);
	
	/**
	 * Returns the highest weighted matching term, or null 
	 * if there are no matching terms.
	 */
	public String bestMatch(String prefix);
	
	/**
	 * Returns the highest weighted k matching terms 
	 * (in descending order of weight), as an iterable.
	 * If fewer there are fewer than k matches, return all matching terms
	 * (in descending order of weight).
	 */
	public Iterable<String> matches(String prefix, int k);
}
