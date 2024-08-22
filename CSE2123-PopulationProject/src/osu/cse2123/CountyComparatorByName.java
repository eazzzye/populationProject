package osu.cse2123;

/**
 * A Comparator for County objects in ascending order of name
 * 
 * @author ERIKA THOMAS
 * @version 12/3/2023
 */

import java.util.Comparator;


public class CountyComparatorByName implements Comparator<County> {
	

	@Override
	public int compare(County o1, County o2) {
		Integer compare = o1.getName().compareTo(o2.getName());
		return compare;
	}
	
	
}