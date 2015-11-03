package memoryTest;

import static org.junit.Assert.*;

import org.junit.Test;

import application.Main;

public class MainTester {

	Main mainClass = new Main();
	
	@Test(timeout = 10)
	public void test() {
		mainClass.center_2();
	}

}
