package memoryTest;

import static org.junit.Assert.*;

import org.junit.Test;

import application.Main;

public class MainTester {

	Main mainClass = new Main();
	
	@Test(timeout = 200)
	public void test() {
		mainClass.center_2();
	}

}
