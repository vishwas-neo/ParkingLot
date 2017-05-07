package com.parkinglot;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.parkinglot.driver.MainDriver;

public class MainDriverTest {
	
	MainDriver driver;
	
	@BeforeTest
	public void createDriver() {
		driver = new MainDriver();
		String line = driver.processLine("create_parking_lot 2");		
		Assert.assertEquals("Created a parking lot with 2 slots", line);
	}
	
	@Test
	public void parkLeaveTest(){
		String line = driver.processLine("park KA-01-HH-1234 White");
		Assert.assertEquals("Allocated slot number: 1", line);
		line = driver.processLine("leave 1");
		Assert.assertEquals("Slot number 1 is free", line);
	}
	
	@Test
	public void parkFullTest(){
		String line = driver.processLine("park KA-01-HH-9999 White");
		Assert.assertEquals("Allocated slot number: 1", line);
		
		line = driver.processLine("park KA-01-HH-1234 White");
		Assert.assertEquals("Allocated slot number: 2", line);
		
		line = driver.processLine("park KA-01-BB-0001 Black");
		Assert.assertEquals("Sorry, parking lot is full", line);
		
		driver.processLine("leave 1");
		driver.processLine("leave 2");
	}
	
	@Test
	public void regAndSlotNumbersWithColorTest(){
		String line = driver.processLine("park KA-01-HH-9999 White");
		
		line = driver.processLine("registration_numbers_for_cars_with_colour White");
		Assert.assertEquals("KA-01-HH-9999", line);
		
		line = driver.processLine("slot_numbers_for_cars_with_colour White");
		Assert.assertEquals("1", line);
		
		line = driver.processLine("slot_number_for_registration_number KA-01-HH-9999");
		Assert.assertEquals("1", line);

		line = driver.processLine("status");
		line = driver.processLine("leave 1");
	}
	
	@Test
	public void regAnddSlotNumbersWithColorTestInValid(){
		String line = driver.processLine("slot_number_for_registration_number MH-04-AY-1111");
		Assert.assertEquals("Not found", line);
		
		line = driver.processLine("registration_numbers_for_cars_with_colour Blue");
		Assert.assertEquals("Not found", line);	
		
		line = driver.processLine("slot_numbers_for_cars_with_colour Blue");
		Assert.assertEquals("Not found", line);
	}
	
	@Test
	public void inValidInstructionsTest(){
		String line = driver.processLine("slot_number_for_registration_number MH-04-AY-1111 white");
		Assert.assertEquals("Invalid Instruction", line);
		
		line = driver.processLine("status 1");
		Assert.assertEquals("Invalid Instruction", line);
		
		line = driver.processLine("park 1");
		Assert.assertEquals("Invalid Instruction", line);
		
		line = driver.processLine("Invalid 1");
		Assert.assertEquals("Invalid Instruction", line);
		
	}
}
