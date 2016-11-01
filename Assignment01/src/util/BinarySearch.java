package util;

import java.util.ArrayList;
import java.util.List;

import models.Term;

public class BinarySearch {
	
	public static int binaryTermSearch(List<Term> list,String term)
	{
		int lowIndex = 0;
		int highIndex = list.size()-1;
		
		while(highIndex >= lowIndex)
		{
			int middleIndex = (lowIndex + highIndex)/2;
			int compareResult = list.get(middleIndex).getTerm().compareToIgnoreCase(term);
			if(compareResult==0)
			{
				return middleIndex;
			}
			if(compareResult < 0)
			{
				lowIndex = middleIndex + 1;
			}
			if(compareResult > 0)
			{
				highIndex = middleIndex -1;
			}
		}
		return -1;
	}
	
	public static List<Term> binaryPrefixSearch(List<Term> list,String prefix)
	{
		List<Term> result = new ArrayList<Term>();
		int lowIndex = 0;
		int highIndex = list.size()-1;
		int firstIndex;
		int lastIndex;
		
		while(highIndex >= lowIndex)
		{
			int middleIndex = (lowIndex + highIndex)/2;
			String s = list.get(middleIndex).getTerm();
			//int compareResult = s.startsWith(prefix) ? 0 : prefix.compareTo(s);
			int compareResult;
			if(s.startsWith(prefix))
				compareResult = 0;
			else
				compareResult = prefix.compareTo(s);
			if(compareResult==0)
			{
				//for(Term term:list)
					//System.out.println(term);
				firstIndex = middleIndex;
				lastIndex = middleIndex;
				
				do
				{
					if(firstIndex>0)
						firstIndex--;
					else
						break;
				}while (list.get(firstIndex).getTerm().startsWith(prefix));
				do
				{
					if(lastIndex<list.size()-1)
						lastIndex++;
					else
						break;
				}while (list.get(lastIndex).getTerm().startsWith(prefix));
				//System.out.println("First :"+firstIndex + " LastIndex:"+lastIndex);
				for(int i = firstIndex;i<=lastIndex;i++)
				{
					//System.out.println(list.get(i).getTerm().startsWith(prefix));
					if(list.get(i).getTerm().startsWith(prefix))
						result.add(list.get(i));
				}																
				//result = list.subList(firstIndex, lastIndex);
				//for(Term t : result)
					//System.out.println(t);
				return result;
			}
			if(compareResult > 0)
			{
				lowIndex = middleIndex + 1;
			}
			if(compareResult < 0)
			{
				highIndex = middleIndex -1;
			}
		}
		return null;
	}
}
