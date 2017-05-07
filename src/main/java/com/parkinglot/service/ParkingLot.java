package com.parkinglot.service;

import java.util.ArrayList;
import java.util.List;

import com.parkinglot.entities.Ticket;
import com.parkinglot.entities.Vehicle;
/*
 * This class stores the tickets generated by TicketService.
 * 
 */
public class ParkingLot {

	private int capacity;

	private Ticket[] ticketArray;
	
	private TicketService ticketService;
	
	private static final char NEW_LINE= '\n';
	private static final char TAB = '\t';
	private static final String SLOT_NUM = "No";
	private static final String REGISTERED_SLOT_NUM = "Registration Slot No.";
	private static final String COLOUR = "Colour";

	
	public ParkingLot(int capacity) {
		super();
		this.capacity = capacity;
		this.ticketArray = new Ticket[capacity+1]; 
		this.ticketService = new TicketService();
	}
/*
 * This function receives a vehicle object and generate a ticket.
 * If parking lot is full. It returns null
 */
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
/*
 * This function deletes the ticket for the slot number.	
 */
	public void leave(int slotNo){
		if(slotNo<0 || slotNo > capacity || ticketArray[slotNo]==null){
			return;
		}
		ticketService.destroyTicket(ticketArray[slotNo]);
		ticketArray[slotNo] = null;
		return;
	}
/*
 * It returns slot number for a registration number.
 * Returns null if registration number not found;
 */
	public Integer getSlotNumberByRegNumber(String regNumber){
		Ticket ticket = ticketService.getTicketByRegNumber(regNumber);
		if(ticket == null){
			return null;
		}
		return ticket.getSlotNo();
	}
/*
 * It returns list of slot numbers for a color.
 * Returns null if no vehicle is parked with that color.
 */	
	public List<Integer> getSlotNumbersByColor(String color){
		List<Ticket> ticketList = ticketService.getTicketsByColor(color);
		if(ticketList == null || ticketList.isEmpty()){
			return null;
		}
		List<Integer> slotNumbers = new ArrayList<>();
		for(Ticket ticket:ticketList){
			slotNumbers.add(ticket.getSlotNo());
		}
		return slotNumbers;
	}
/*
 * It return list of registration number of vehicle parked with a color
 * Returns null if no vehicle is parked with that color. 	
 */
	public List<String> getRegNumbersByColor(String color){
		List<Ticket> ticketList = ticketService.getTicketsByColor(color);
		if(ticketList == null || ticketList.isEmpty()){
			return null;
		}
		List<String> colorNumbers = new ArrayList<>();
		for(Ticket ticket:ticketList){
			colorNumbers.add(ticket.getVehicle().getRegistrationNumber());
		}
		return colorNumbers;
	}
/*
 * It returns the String. String will be having tab delimited details of all parked slots. 	
 */
	public String getStatus(){
		StringBuffer sb = new StringBuffer();
		sb.append(NEW_LINE);
		sb.append(NEW_LINE);
		sb.append(SLOT_NUM);
		sb.append(TAB);
		sb.append(REGISTERED_SLOT_NUM);
		sb.append(TAB);
		sb.append(COLOUR);
		sb.append(NEW_LINE);
		for(int i = 1;i<capacity;i++){
			if(ticketArray[i]!=null){
				sb.append(ticketArray[i].getSlotNo());
				sb.append(TAB);
				sb.append(ticketArray[i].getVehicle().getRegistrationNumber());
				sb.append(TAB);
				sb.append(ticketArray[i].getVehicle().getColor());
				sb.append(NEW_LINE);
			}
		}
		return sb.toString();
	}
/*
 * A utility function that will return next empty slot.
 * Returns -1 if parking lot is full.	
 */
	private int getNextEmptySlot(){
		for(int i=1;i<=capacity;i++){
			if(ticketArray[i]==null){
				return i;
			}
		}
		return -1;
	}
}
