package com.parkinglot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.parkinglot.entities.Ticket;
import com.parkinglot.entities.Vehicle;
/*
 * This class is responsible for generating tickets, searching tickets by color and registration numbers
 */
class TicketService {

	private Map<String,Ticket> regNumberTicketMap;
	
	private Map<String,List<Ticket>> colorTicketsMap;
	
	public TicketService() {
		regNumberTicketMap = new HashMap<>();
		colorTicketsMap = new HashMap<>();
	}
/*
 * This method takes a slot number and a vehicle object.
 * It generates a ticket for that slot number.
 */
	public Ticket generateNewTicket(Integer slot,Vehicle vehicle){
		
		Ticket ticket = new Ticket(slot, vehicle);
		regNumberTicketMap.put(vehicle.getRegistrationNumber(),ticket);
		updateColorTicketMap(vehicle, ticket);
		return ticket;
	}
/*
 * It returns the ticket by registration number.	
 */
	public Ticket getTicketByRegNumber(String regNumber){
		return regNumberTicketMap.get(regNumber);
	}
/*
 * It returns list of tickets for a particular vehicle color.	
 */
	public List<Ticket> getTicketsByColor(String color){
		return colorTicketsMap.get(color.toLowerCase());
	}
/*
 * It updates all the internal maps when a ticket is destroyed.	
 */
	public void destroyTicket(Ticket ticket){
		regNumberTicketMap.remove(ticket.getVehicle().getRegistrationNumber());
		colorTicketsMap.get(ticket.getVehicle().getColor().toLowerCase()).remove(ticket);
	}
	
	private void updateColorTicketMap(Vehicle vehicle, Ticket ticket){
		List<Ticket> colorTickets = colorTicketsMap.get(vehicle.getColor().toLowerCase());
		if(colorTickets!=null){
			colorTickets.add(ticket);
		}
		else{
			colorTickets = new ArrayList<>();
			colorTickets.add(ticket);
			colorTicketsMap.put(vehicle.getColor().toLowerCase(), colorTickets);
		}
	}
}
