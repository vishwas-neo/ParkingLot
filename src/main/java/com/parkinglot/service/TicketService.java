package com.parkinglot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.parkinglot.entities.Ticket;
import com.parkinglot.entities.Vehicle;

class TicketService {

	private Map<String,Ticket> regNumberTicketMap;
	
	private Map<String,List<Ticket>> colorTicketsMap;
	
	public TicketService() {
		regNumberTicketMap = new HashMap<>();
		colorTicketsMap = new HashMap<>();
	}
	
	public Ticket generateNewTicket(Integer slot,Vehicle vehicle){
		
		Ticket ticket = new Ticket(slot, vehicle);
		regNumberTicketMap.put(vehicle.getRegistrationNumber(),ticket);
		updateColorTicketMap(vehicle, ticket);
		return ticket;
	}
	
	public Ticket getTicketByRegNumber(String regNumber){
		return regNumberTicketMap.get(regNumber);
	}
	
	public List<Ticket> getTicketsByColor(String color){
		return colorTicketsMap.get(color);
	}
	
	public void destroyTicket(Ticket ticket){
		regNumberTicketMap.remove(ticket.getVehicle().getRegistrationNumber());
		colorTicketsMap.get(ticket.getVehicle().getColor()).remove(ticket);
	}
	
	private void updateColorTicketMap(Vehicle vehicle, Ticket ticket){
		List<Ticket> colorTickets = colorTicketsMap.get(vehicle.getColor());
		if(colorTickets!=null){
			colorTickets.add(ticket);
		}
		else{
			colorTickets = new ArrayList<>();
			colorTickets.add(ticket);
			colorTicketsMap.put(vehicle.getColor(), colorTickets);
		}
	}
}
