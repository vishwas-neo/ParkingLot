package com.parkinglot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.parkinglot.entities.Ticket;
import com.parkinglot.entities.Vehicle;

/**
 * This class is responsible for generating tickets, searching tickets by color
 * and registration numbers
 * 
 * More functionality can be added here
 * 
 * @author vishwas
 *
 */
class TicketService {

	private Map<String, Ticket> regNumberTicketMap;

	private Map<String, List<Ticket>> colorTicketsMap;

	public TicketService() {
		regNumberTicketMap = new HashMap<>();
		colorTicketsMap = new HashMap<>();
	}

	/**
	 * 
	 * @param slot
	 *            A slot number where vehicle will be parked
	 * @param vehicle
	 *            Vehicle to be parked.
	 * @return Parking Ticket containing the slot Number and Vehicle object.
	 */
	public Ticket generateNewTicket(Integer slot, Vehicle vehicle) {

		Ticket ticket = new Ticket(slot, vehicle);
		regNumberTicketMap.put(vehicle.getRegistrationNumber(), ticket);
		updateColorTicketMap(vehicle, ticket);
		return ticket;
	}

	/**
	 * @param regNumber
	 *            Registration number of vehicle
	 * @return Parking ticket of the vehicle. <br>
	 *         Returns null if vehicle with this registration number is not
	 *         parked.
	 * 
	 */
	public Ticket getTicketByRegNumber(String regNumber) {
		return regNumberTicketMap.get(regNumber);
	}

	/**
	 * 
	 * @param color
	 *            Color of vehicle
	 * @return Parking tickets of vehicles with that color. <br>
	 *         Returns null if no vehicle with this color is parked.
	 */
	public List<Ticket> getTicketsByColor(String color) {
		return colorTicketsMap.get(color.toLowerCase());
	}

	/**
	 * @param ticket
	 *            Ticket of car which is leaving the parking.
	 */
	public void destroyTicket(Ticket ticket) {
		regNumberTicketMap.remove(ticket.getVehicle().getRegistrationNumber());
		colorTicketsMap.get(ticket.getVehicle().getColor().toLowerCase()).remove(ticket);
	}

	/**
	 * 
	 * @param vehicle
	 *            Vehicle which is parked.
	 * @param ticket
	 *            Ticked generated
	 */
	private void updateColorTicketMap(Vehicle vehicle, Ticket ticket) {
		List<Ticket> colorTickets = colorTicketsMap.get(vehicle.getColor().toLowerCase());
		if (colorTickets != null) {
			colorTickets.add(ticket);
		} else {
			colorTickets = new ArrayList<>();
			colorTickets.add(ticket);
			colorTicketsMap.put(vehicle.getColor().toLowerCase(), colorTickets);
		}
	}
}
