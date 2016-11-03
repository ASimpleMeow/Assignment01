package models;

/**
 * Term class creates a Term object which contains the String and it's 
 * weight.
 * 
 * @author Oleksandr Kononov
 *
 */
public class Term implements Comparable<Term>{
	
	private String term;
	private double weight;
	
	/**
	 * The constructor for the Term class
	 * @param term
	 * @param weight
	 */
	public Term (String term, double weight)
	{
		setTerm(term);
		setWeight(weight);
	}

	/**
	 * Getter for the Term
	 * 
	 * @return String term
	 */
	public String getTerm() {
		return term;
	}

	/**
	 * Setter for the Term which will throw a NullPointerException
	 * if the term is null
	 * 
	 * @param term
	 */
	public void setTerm(String term) {
		if(term != null)
		{
			this.term = term.toLowerCase().trim();
		}
		else
		{
			throw new NullPointerException();
		}
	}

	/**
	 * Getter for the Term's weight
	 * 
	 * @return double
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Sets the Term's weight and throws an IllegalArgumentException
	 * if it's a negative
	 * 
	 * @param weight
	 */
	public void setWeight(double weight) {
		if (weight>=0)
		{
			this.weight = weight;
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Returns Term's data in String format
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "Term: "+term + "    Weight: "+weight;
	}

	/**
	 * Compares the weight of current Term to the weight of another Term
	 * 
	 * @return int
	 */
	@Override
	public int compareTo(Term other) {
		return new Double(this.weight).compareTo(new Double(other.getWeight()));
	}
}
