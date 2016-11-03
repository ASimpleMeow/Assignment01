package util;

import java.util.Comparator;

import models.Term;

/**
 * TermComparator will implement the compare method which will
 * compare the Terms by String
 * 
 * @author Oleksandr Kononov
 *
 */
public class TermComparator implements Comparator<Term> {

	/**
	 * Compares the String term of the two Terms
	 * 
	 * @return int
	 */
	@Override
	public int compare(Term t1, Term t2) {
		return t1.getTerm().compareToIgnoreCase(t2.getTerm());
	}

}
