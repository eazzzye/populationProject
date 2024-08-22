package osu.cse2123;
/* 
 * an class that implemnts the interface State
 * @Author Erika Thomas
 * @version 12/6/2023
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateInfo implements State {
	
	public String stateName;
	public List<County> counties;
	
	public StateInfo() {
		this.stateName = new String();
		this.counties = new ArrayList();
	}

	@Override
	public void setName(String name) {
		this.stateName = name;
	}

	@Override
	public String getName() {
		return this.stateName;
	}

	@Override
	public void addCounty(County county) {
		this.counties.add(county);
	}

	@Override
	public County getCounty(String name) {
		for (County county : counties) {
			if (county.getName().equals(name)) {
				return county;
			}
		}
		return null;
	}

	@Override
	public int getPopulation(int year) {
		int pop = 0;
		for (County county : counties) {
			pop = pop + county.getPopulation(year);
		}
		return pop;
	}

	@Override
	public List<County> getCounties() {
		CountyComparatorByName cmn = new CountyComparatorByName();
		Collections.sort(counties, cmn);
		return counties;
	}

	@Override
	public List<County> getCountiesByPopulation(int year) {
		CountyComparatorByPopulation cmp = new CountyComparatorByPopulation(year);
		Collections.sort(counties, cmp);
		return counties;
	}
	
	@Override
	public String toString() {
		String str = (this.stateName + " " +  this.counties);
		return str;
	}

}
