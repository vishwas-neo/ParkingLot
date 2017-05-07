package com.parkinglot.entities;

/**
 * This interface should be implemented by all the vehicles which can be parked
 * in a parking lot. Vehicle must have a registrationNumber and a color.
 * 
 * @author vishwas
 *
 */
public interface Vehicle {

	public String getRegistrationNumber();

	public String getColor();

}
