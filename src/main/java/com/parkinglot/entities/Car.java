package com.parkinglot.entities;

public class Car implements Vehicle {

	private String regNumber;
	public String color;

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
