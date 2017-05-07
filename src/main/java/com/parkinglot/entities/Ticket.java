package com.parkinglot.entities;

/**
 * Immutable class of Type Ticket. A Ticket will have <br>
 * slotNo: Slot Number assigned to a car. <br>
 * vehicle: Object of Vehicle for which this ticket will be generated.
 * 
 * @author vishwas
 *
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
