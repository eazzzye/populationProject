package osu.cse2123;

/**
 * A Comparator for County objects in descending order of population
 * 
 * @author ERIKA THOMAS
 * @version 12/3/2023
 */

import java.util.Comparator;

public class CountyComparatorByPopulation implements Comparator<County> {
	
	private int year;
	
	public CountyComparatorByPopulation(int year) {
		this.year = year;
	}

	@Override
	public int compare(County o1, County o2) {
		Integer o1Pop = o1.getPopulation(year);
		Integer o2Pop = o2.getPopulation(year);
		Integer compare = Integer.compare(o2Pop, o1Pop);
		return compare;
	}
}