package osu.cse2123;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class StateInfoTest {
	
	static State[] test;
	
	@BeforeAll
	static void initialize() {
		test = new State[5];
		County county = new CountyInfo();
		test[0] = new StateInfo();
		test[0].setName("A Musical Track");
		test[0].addCounty(county);
	}
		

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
