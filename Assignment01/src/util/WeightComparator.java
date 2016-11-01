package util;

import java.util.Comparator;

import models.Term;

public class WeightComparator implements Comparator<Term>{

	@Override
	public int compare(Term t1, Term t2) {
		if(t1.getWeight() > t2.getWeight())
			return -1;
		else
			return 1;
	}

}
