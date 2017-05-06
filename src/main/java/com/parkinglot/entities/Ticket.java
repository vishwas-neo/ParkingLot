package com.parkinglot.entities;

public class Ticket {

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
