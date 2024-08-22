package osu.cse2123;

/* 
 * an class that implemnts the interface County
 * @Author Erika Thomas
 * @version 12/6/2023
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CountyInfo implements County {
	
	public String countyName;
	Map<Integer, Integer> countyMap;
	
	
	public CountyInfo(){
		this.countyName = new String();
		this.countyMap = new HashMap<>();
	}

	@Override
	public void setName(String name) {
		this.countyName = name;
		
	}

	@Override
	public String getName() {
		return this.countyName;
	}

	@Override
	public void addPopulation(int year, int pop) {
		this.countyMap.put(year, pop);
		
	}

	@Override
	public int getPopulation(int year) {
		Integer pop = this.countyMap.get(year);
		return pop;
	}
	
	@Override 
	public String toString() {
		String str = (this.countyName + " " +  this.countyMap);
		return str;
	}
	

}
