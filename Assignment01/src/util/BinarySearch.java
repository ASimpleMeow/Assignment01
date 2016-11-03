package util;

import java.util.ArrayList;
import java.util.List;

import models.Term;

/**
 * BinarySearch will perform BinarySearch methods for the QuickAutocomplete
 * 
 * @author Oleksandr Kononov
 *
 */
public class BinarySearch {
	
	/**
	 * This will find a Term in the list by performing BinarySearch on the
	 * List<Term> given, using String term as the prefix. Returning the full
	 * Terms index in the list
	 * 
	 * @param list
	 * @param term
	 * @return int index
	 */
	public static int binaryTermSearch(List<Term> list,String prefix)
	{
		//Setting the initial low and high index
		int lowIndex = 0;
		int highIndex = list.size()-1;
		
		//Continuing while highIndex is higher that low index
		while(highIndex >= lowIndex)
		{
			int middleIndex = (lowIndex + highIndex)/2;//Getting the middle index
			//Using the compareTo to get an int value
			int compareResult = list.get(middleIndex).getTerm().compareToIgnoreCase(prefix);
			if(compareResult==0)//Term index found
			{
				return middleIndex;
			}
			if(compareResult < 0)//Term index is above the middle index
			{
				lowIndex = middleIndex + 1;
			}
			if(compareResult > 0)//Term index is below the middle index
			{
				highIndex = middleIndex -1;
			}
		}
		return -1; //Term index not found
	}
	
	/**
	 * Will perform a BinarySearch on the given list and using the
	 * given prefix to extract a block on Terms from the list and 
	 * return that
	 * 
	 * @param list
	 * @param prefix
	 * @return List<Term>
	 */
	public static List<Term> binaryPrefixSearch(List<Term> list,String prefix)
	{
		List<Term> result = new ArrayList<Term>();//Making a result List
		int lowIndex = 0;
		int highIndex = list.size()-1;
		int firstIndex;//first index from the block to go in the result
		int lastIndex;//last index from the block to go in the result
		
		//Similar operation to the binaryTermSearch
		while(highIndex >= lowIndex)
		{
			int middleIndex = (lowIndex + highIndex)/2;
			String s = list.get(middleIndex).getTerm();
			int compareResult;
			if(s.startsWith(prefix))
				compareResult = 0;
			else
				compareResult = prefix.compareTo(s);
			if(compareResult==0)
			{
				firstIndex = middleIndex;//This will go back from the middle
				lastIndex = middleIndex;//This will go forward from the middle
				
				//Finding the firstIndex
				do
				{
					if(firstIndex>0)
						firstIndex--;
					else
						break;
				}while (list.get(firstIndex).getTerm().startsWith(prefix));
				
				//Finding the lastIndex
				do
				{
					if(lastIndex<list.size()-1)
						lastIndex++;
					else
						break;
				}while (list.get(lastIndex).getTerm().startsWith(prefix));
				
				//Adding the Terms from the firstIndex to the lastIndex to the result
				for(int i = firstIndex;i<=lastIndex;i++)
				{
					if(list.get(i).getTerm().startsWith(prefix))
						result.add(list.get(i));
				}																
				
				//Note: I tried just using the list.subList(firstIndex,lastIndex)
				//But there would occasionally be error if I did it that way.
				
				return result;//Return the result List
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
		return null; //Not found
	}
}
