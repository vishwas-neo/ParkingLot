package com.parkinglot.entities;

/**
 * Implementation of Interface Vehicle. Immutable class of Type Car. <br>
 * regNumber: Registration Number of the car. <br>
 * Color: Color of car.
 * 
 * @author vishwas
 *
 */
public final class Car implements Vehicle {

	private String regNumber;
	private String color;

	public Car(String regNumber, String color) {
		super();
		this.regNumber = regNumber;
		this.color = color;
	}

	public String getRegistrationNumber() {
		return regNumber;
	}

	public String getColor() {
		return color;
	}

}
