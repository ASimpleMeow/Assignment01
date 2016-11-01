package util;

import java.util.Comparator;

import models.Term;

public class TermComparator implements Comparator<Term> {

	@Override
	public int compare(Term t1, Term t2) {
		return t1.getTerm().compareToIgnoreCase(t2.getTerm());
	}

}
