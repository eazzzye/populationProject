package osu.cse2123;
/**
 * A PROGRAM THAT ASKS A USER TO ENTER A FILE NAME, PUT IN A START AND END YEAR AND OUTPUTS THE STATES AND COUNTIES IN THE FILE
 * WITH THEIR RESPECTED POPULATION IN THE GIVEN YEARS THE USER CHOOSE 
 * 
 * @author ERIKA THOMAS
 * @version 12/3/2023
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Reporting {
	
	/**
	 * 
	 */
	public static List<State> loadStatesFromFile(String fileName) throws FileNotFoundException {
		List<State> list = new ArrayList<>();//making new array list
		File textFile = new File(fileName);
		Scanner textScanner = new Scanner(textFile);
		while (textScanner.hasNext()) {//while file reads next line do the follwoing: 
			String str = textScanner.nextLine();
			String[] split = str.split(",");//splitting str to get state name and file name 
			State state = loadCountiesFromFile(split[0], split[1]);//using loadCounties from file to get the counties
			list.add(state);//adding the state to the list 
		}
		textScanner.close();
		return list;
	}
	
	/*
	 * 
	 */
	public static State loadCountiesFromFile(String name, String fileName) throws FileNotFoundException {
		StateInfo state = new StateInfo();//creating new state object 
		state.setName(name);//setting the state names to parametr name 
		File textFile = new File(fileName);
		Scanner fileScanner = new Scanner(textFile);
		fileScanner.nextLine();
		while (fileScanner.hasNext()) {//while scanner reads the next line do the following: 
			County counties = new CountyInfo();
			String county = fileScanner.nextLine();
			String[] split = county.split(",");//spliting string to get each population and name
			counties.setName(split[0]);//setting county name at index 0 
			int year = 2010;
			int pop = 1;
			while (year < 2020 && pop < 11) {//while year is less than 202 and pop is less than 11 add the year and pop into the county
				counties.addPopulation(year, Integer.parseInt(split[pop]));
				year++;
				pop++;
			}
			state.addCounty(counties);//adding the county to the state 
		}
		fileScanner.close();
		return state;
	}
	
	/**
	 * 
	 */
	public static String growthByState(State state, int year1, int year2) {
		int growth = 0;
		if (state.getPopulation(year1) > state.getPopulation(year2)) {//if the pop of year 1 is greater than pop of year 2 then add the population of both year 1 and year 2 into growth 
			growth = state.getPopulation(year1) + state.getPopulation(year2);
		}
		else if (state.getPopulation(year1) < state.getPopulation(year2)) {//if the pop of year 1 is less than pop of year 2 then subtract the population of both year 1 and year 2 into growth 
			growth = state.getPopulation(year1) - state.getPopulation(year2);
		}
		String answer = Integer.toString(growth);
		return answer;
	}
	
	/*
	 * 
	 */
	public static String growthByCounty(State state, int year1, int year2) {
		int growth = 0;
		List<County> counties = new ArrayList<>();//making new array list
		counties = state.getCountiesByPopulation(year1);
		for (int i = 0; i < counties.size(); i++) {//if the pop of year 1 is greater than pop of year 2 then add the population of both year 1 and year 2 into growth 
			if (counties.get(i).getPopulation(year1) > counties.get(i).getPopulation(year2)) {
				growth = counties.get(i).getPopulation(year1) + counties.get(i).getPopulation(year2);
			}
			if (counties.get(i).getPopulation(year1) < counties.get(i).getPopulation(year2)) {//if the pop of year 1 is less than pop of year 2 then subtract the population of both year 1 and year 2 into growth 
				growth = counties.get(i).getPopulation(year1) - counties.get(i).getPopulation(year2);

			}
		}
		String answer = Integer.toString(growth);
		return answer;
	}
	
	/*
	 * 
	 */
	public static String displayPop(State state, int year) {
		StringBuilder pop = new StringBuilder();
		List<County> county = new ArrayList();
		county = state.getCountiesByPopulation(year);//setting the list to the list of counties in desceding order
		for (int i = 0; i < county.size(); i++) {
			pop.append(county.get(i).getPopulation(year) + System.lineSeparator());//adding the population to stringbuilder
		}
		return pop.toString();
	}
	
	/*
	 * 
	 */
	public static String displayCounty(State state, int year) {
		StringBuilder counties = new StringBuilder();
		List<County> county = new ArrayList();
		county = state.getCountiesByPopulation(year);//setting the list to the list of counties in desceding order
		for (int i = 0; i < county.size(); i++) {
			counties.append(county.get(i).getName() + System.lineSeparator());//adding the name to stringbuilder
		}
		return counties.toString();
	}
	
	/*
	 * 
	 */
	public static String average(State state, int year) {
		int average = 0;
		average = state.getPopulation(year)/2;//dividing the poputlion of the state by 2
		String answer = Integer.toString(average);
		return answer;
	}
	
	/*
	 * 
	 */
	public static String median(State state, int year) {
		int median = 0;
		List<County> counties = new ArrayList<>();
		counties = state.getCountiesByPopulation(year);//setting the list to the list of counties in desceding order
		int test = counties.size();
		if (test % 2 == 0) {//if test is even then then add the results of num1 and num2 to median 
			int num1  = test/2-1;
			int num2 = test/2;
			median = num1 + num2;
		}
		else {// if test is odd return the actual median
			median = test/2;
		}
		String answer = Integer.toString(median);
		return answer;
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter a list of population files: ");
		String file = scan.nextLine();
		System.out.print("Enter a start year: ");
		Integer startYear = scan.nextInt();
		System.out.print("Enter a end year: ");
		Integer endYear = scan.nextInt();
		System.out.println();
		System.out.println("State/County" + "            " + startYear + "         " + endYear + "       " + "Growth");
		System.out.println("--------------- ------------ ------------ ------------");
		System.out.println();
		System.out.println();
		System.out.println("--------------- ------------ ------------ ------------");                  
		List<State> state = loadStatesFromFile(file);
		String cali = displayCounty(state.get(0), startYear);
		String caliPop1 = displayPop(state.get(0), startYear);
		String caliPop2 = displayPop(state.get(0), endYear);
		String caliAvg1 = average(state.get(0), startYear);
		String caliAvg2 = average(state.get(0), endYear);
		String caliMed1 = median(state.get(0), startYear);
		String caliMed2 = median(state.get(0), endYear);
		String caliGrowth = growthByState(state.get(0), startYear, endYear);
		String caliCountyGrowth = growthByCounty(state.get(0), startYear, endYear);
		String indy = displayCounty(state.get(1), startYear);
		String indyPop1 = displayPop(state.get(1), startYear);
		String indyPop2 = displayPop(state.get(1), endYear);
		String indyAvg1 = average(state.get(1), startYear);
		String indyAvg2 = average(state.get(1), endYear);
		String indyMed1 = median(state.get(1), startYear);
		String indyMed2 = median(state.get(1), endYear);
		String indyGrowth = growthByState(state.get(1), startYear, endYear);
		String indyCountyGrowth = growthByCounty(state.get(1), startYear, endYear);
		String wyon = displayCounty(state.get(2), startYear);
		String wyonPop1 = displayPop(state.get(2), startYear);
		String wyonPop2 = displayPop(state.get(2), endYear);
		String wyonAvg1 = average(state.get(2), startYear);
		String wyonAvg2 = average(state.get(2), endYear);
		String wyonMed1 = median(state.get(2), startYear);
		String wyonMed2 = median(state.get(2), endYear);
		String wyonGrowth = growthByState(state.get(3), startYear, endYear);
		String wyonCountyGrowth = growthByCounty(state.get(3), startYear, endYear);
		String ohio = displayCounty(state.get(3), startYear);
		String ohioPop1 = displayPop(state.get(3), startYear);
		String ohioPop2 = displayPop(state.get(3), endYear);
		String ohioAvg1 = average(state.get(3), startYear);
		String ohioAvg2 = average(state.get(3), endYear);
		String ohioMed1 = median(state.get(3), startYear);
		String ohioMed2 = median(state.get(3), endYear);
		String ohioGrowth = growthByState(state.get(3), startYear, endYear);
		String ohioCountyGrowth = growthByCounty(state.get(3), startYear, endYear);
		System.out.println(state.get(0).getName() + "         " + state.get(0).getPopulation(startYear) + "     " + state.get(0).getPopulation(endYear)  + "     " + caliGrowth);
		System.out.println("--------------- ------------ ------------ ------------");  
		System.out.printf("%5s %3s %3s %3s %n", cali, caliPop1, caliPop2, caliCountyGrowth);
		System.out.println("--------------- ------------ ------------ ------------");   
		System.out.printf("Average pop: %5s %3s %n", caliAvg1, caliAvg2);
		System.out.printf("Median Pop: %5s %3s %n", caliMed1, caliMed2);
		System.out.println();
		System.out.println("--------------- ------------ ------------ ------------"); 
		System.out.println(state.get(0).getName() + "         " + state.get(0).getPopulation(startYear) + "     " + state.get(0).getPopulation(endYear)  + "     " + indyGrowth);
		System.out.println("--------------- ------------ ------------ ------------");  
		System.out.printf("%5s %3s %3s %3s %n", indy, indyPop1, indyPop2, indyCountyGrowth);
		System.out.println("--------------- ------------ ------------ ------------");   
		System.out.printf("Average pop: %5s %3s %n", indyAvg1, indyAvg2);
		System.out.printf("Median pop: %5s %3s %n", caliMed1, caliMed2);
		System.out.println();
		System.out.println("--------------- ------------ ------------ ------------");   
		System.out.println(state.get(0).getName() + "         " + state.get(0).getPopulation(startYear) + "     " + state.get(0).getPopulation(endYear)  + "     " + ohioGrowth);
		System.out.println("--------------- ------------ ------------ ------------");  
		System.out.printf("%5s %3s %3s %3s %n", ohio, ohioPop1, ohioPop2, ohioCountyGrowth);
		System.out.println("--------------- ------------ ------------ ------------");   
		System.out.printf("Average pop: %5s %3s %n", ohioAvg1, ohioAvg2);
		System.out.printf("Median pop: %5s %3s %n", ohioMed1, ohioMed2);
		System.out.println();
		System.out.println("--------------- ------------ ------------ ------------"); 
		System.out.println(state.get(0).getName() + "         " + state.get(0).getPopulation(startYear) + "     " + state.get(0).getPopulation(endYear)  + "     " + wyonGrowth);
		System.out.println("--------------- ------------ ------------ ------------");  
		System.out.printf("%5s %3s %3s %3s %n", wyon, wyonPop1, wyonPop2, wyonCountyGrowth);
		System.out.println("--------------- ------------ ------------ ------------");   
		System.out.printf("Average pop: %5s %3s %n", wyonAvg1, wyonAvg2);
		System.out.printf("Median pop: %5s %3s %n", wyonMed1, wyonMed2);
		System.out.println();
		System.out.println("--------------- ------------ ------------ ------------");
		

	
	}

}
