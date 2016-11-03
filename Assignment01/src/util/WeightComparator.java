package util;

import java.util.Comparator;

import models.Term;

/**
 * WeightComparator will implement the compare method which will
 * compare the Terms by their weight 
 * 
 * @author Oleksandr Kononov
 *
 */
public class WeightComparator implements Comparator<Term>{

	/**
	 * Compare the weight of the two Terms
	 * 
	 * @return int
	 */
	@Override
	public int compare(Term t1, Term t2) {
		if(t1.getWeight() > t2.getWeight())
			return -1;
		else
			return 1;
	}

}
