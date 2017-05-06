package com.parkinglot.service;

import java.util.List;

import com.parkinglot.entities.Ticket;
import com.parkinglot.entities.Vehicle;

public class ParkingLot {

	private int capacity;

	private Ticket[] ticketArray;
	
	private TicketService ticketService;
	
	public ParkingLot(int capacity) {
		super();
		this.capacity = capacity;
		this.ticketArray = new Ticket[capacity+1]; 
		this.ticketService = new TicketService();
	}
	
	public Ticket park(Vehicle vehicle){
		Ticket ticket = ticketService.getTicketByRegNumber(vehicle.getRegistrationNumber());
		if(ticket!=null){
			return ticket;
		}
		int i = getNextEmptySlot();
		if(i==-1){
			return null;
		}
		ticketArray[i] = ticketService.generateNewTicket(i, vehicle);
		return ticketArray[i];
	}
	
	public void leave(int slotNo){
		if(slotNo<0 || slotNo > capacity){
			return;
		}
		ticketService.destroyTicket(ticketArray[slotNo]);
		ticketArray[slotNo] = null;
	}
	
	public Integer getSlotNumberByRegNumber(String regNumber){
		Ticket ticket = ticketService.getTicketByRegNumber(regNumber);
		if(ticket == null){
			return null;
		}
		return ticket.getSlotNo();
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
	
	private int getNextEmptySlot(){
		for(int i=1;i<=capacity;i++){
			if(ticketArray[i]==null){
				return i;
			}
		}
		return -1;
	}
}
