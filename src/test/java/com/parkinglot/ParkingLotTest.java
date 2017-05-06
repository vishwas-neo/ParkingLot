package com.parkinglot;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.parkinglot.entities.Car;
import com.parkinglot.entities.Ticket;
import com.parkinglot.entities.Vehicle;
import com.parkinglot.service.ParkingLot;

public class ParkingLotTest {

	ParkingLot parkingLot;
	
	@BeforeTest
	public void createParkingLot(){
		parkingLot = new ParkingLot(2);
	}
	
	@Test
	public void testGetSlotNumberByRegNumber(){
		Vehicle car = new Car("KA-01-HH-1234","White");
		Ticket ticket = parkingLot.park(car);
		Integer number = parkingLot.getSlotNumberByRegNumber("KA-01-HH-1234");
		Assert.assertEquals(ticket.getSlotNo(), number);
		parkingLot.leave(ticket.getSlotNo());
	}
	
	@Test
	public void testGetSlotNumberByRegNumberInValid(){
		Integer i = parkingLot.getSlotNumberByRegNumber("KA-01-HH-1234");
		Assert.assertEquals(i , null);
	}
	
	@Test
	public void testGetSlotNumbersByColor(){
		Vehicle car = new Car("MH-04-AY-1111","Brown");
		Ticket ticket = parkingLot.park(car);
		List<Integer> slotNumberByColor = parkingLot.getSlotNumbersByColor("Brown");
		Assert.assertEquals(true, slotNumberByColor.size()==1);
		Assert.assertEquals(ticket.getSlotNo(), slotNumberByColor.get(0));
		parkingLot.leave(ticket.getSlotNo());
	}
	
	@Test
	public void testGetSlotNumbersByColorInValid(){
		Vehicle car = new Car("MH-04-AY-1111","Brown");
		Ticket ticket = parkingLot.park(car);
		List<Integer> slotNumberByColor = parkingLot.getSlotNumbersByColor("White");
		Assert.assertEquals(null, slotNumberByColor);
		parkingLot.leave(ticket.getSlotNo());
	}
	
	@Test
	public void testGetRegNumbersByColor(){
		Vehicle car = new Car("MH-04-AY-1111","Brown");
		Ticket ticket = parkingLot.park(car);
		List<String> slotNumberByColor = parkingLot.getRegNumbersByColor("Brown");
		Assert.assertEquals(car.getRegistrationNumber(), slotNumberByColor.get(0));
		parkingLot.leave(ticket.getSlotNo());
	}
	
	@Test
	public void testGetRegNumbersByColorInvalid(){
		Vehicle car = new Car("MH-04-AY-1111","Brown");
		Ticket ticket = parkingLot.park(car);
		parkingLot.leave(ticket.getSlotNo());
		List<String> slotNumberByColor = parkingLot.getRegNumbersByColor("Brown");
		Assert.assertEquals(null, slotNumberByColor);
	}
	
	@Test
	public void testFullParking(){
		Vehicle car = new Car("MH-04-AY-1111","Brown");
		Ticket ticket = parkingLot.park(car);
		Vehicle car1 = new Car("KA-01-HH-1234","White");
		Ticket ticket1 = parkingLot.park(car1);
		
		Vehicle car2 = new Car("DL-12-AA-9999","White");
		Ticket ticket2 = parkingLot.park(car2);
		Assert.assertEquals(null, ticket2);
		parkingLot.leave(ticket.getSlotNo());
		parkingLot.leave(ticket1.getSlotNo());
	}
	
	@Test
	public void leaveInValidSlotNumber(){
		parkingLot.leave(-1);
		parkingLot.leave(3);
	}
	
	@Test
	public void testParkOneCarAgain(){
		Vehicle car = new Car("MH-04-AY-1111","Brown");
		Ticket ticket = parkingLot.park(car);
		Ticket ticket1 = parkingLot.park(car);
		Assert.assertEquals(ticket, ticket1);
		parkingLot.leave(ticket.getSlotNo());
		parkingLot.leave(ticket1.getSlotNo());
	}
	
	@AfterTest
	public void destroyParkingLot(){
		parkingLot = null;
	}
}
