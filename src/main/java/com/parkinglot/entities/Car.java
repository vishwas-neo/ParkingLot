package com.parkinglot.entities;
/*
 * Immutable class of Type Car.
 * regNumber: Registration Number of a car.
 * color: Color of car.
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
