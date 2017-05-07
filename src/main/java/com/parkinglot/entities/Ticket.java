package com.parkinglot.entities;
/*
 * 
 * Immutable class of Type Ticket.
 * slotNo: Slot Number assigned to a car.
 * vehicle: Object of Vehicle for which this ticket will be generated.
 */
public final class Ticket {

	private Integer slotNo;
	private Vehicle vehicle;

	public Ticket(Integer slotNo, Vehicle vehicle) {
		super();
		this.slotNo = slotNo;
		this.vehicle = vehicle;
	}

	public Integer getSlotNo() {
		return slotNo;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

}
