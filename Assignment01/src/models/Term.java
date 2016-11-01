package models;


public class Term implements Comparable<Term>{
	
	private String term;
	private double weight;
	
	public Term (String term, double weight)
	{
		setTerm(term);
		setWeight(weight);
	}

	public String getTerm() {
		return term;
	}

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

	public double getWeight() {
		return weight;
	}

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
	
	@Override
	public String toString()
	{
		return "Term: "+term + "    Weight: "+weight;
	}

	@Override
	public int compareTo(Term other) {
		return new Double(this.weight).compareTo(new Double(other.getWeight()));
	}
}
