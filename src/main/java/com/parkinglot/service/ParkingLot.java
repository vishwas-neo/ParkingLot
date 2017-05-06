package com.parkinglot.service;

import java.util.List;

import com.parkinglot.entities.Ticket;
import com.parkinglot.entities.Vehicle;

public class ParkingLot {

	private int capacity;

	public ParkingLot(int capacity) {
		super();
		this.capacity = capacity;
	}
	
	public Ticket park(Vehicle vehicle){
		return null;
	}
	
	public void leave(int slotNo){
		
	}
	
	public Integer getSlotNumberByRegNumber(String regNumber){
		return null;
	}
	
	public List<Integer> getSlotNumbersByColor(String color){
		return null;
	}
	
	public List<String> getRegNumbersByColor(String color){
		return null;
	}
	
	public String getStatus(){
		
		return null;
	}
}
