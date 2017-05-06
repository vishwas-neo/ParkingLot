package com.parkinglot;

import org.testng.annotations.BeforeTest;

import com.parkinglot.driver.MainDriver;

public class MainDriverTest {
	
	MainDriver driver;
	
	@BeforeTest
	public void createDriver() {
		driver = new MainDriver();
	}
	
}
