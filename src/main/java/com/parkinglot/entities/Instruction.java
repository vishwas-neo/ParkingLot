package com.parkinglot.entities;

import java.util.HashMap;
import java.util.Map;

/**
 * An enum constant for all the instructions.
 * 
 * @author vishwas
 *
 */
public enum Instruction {

	CREATE("create_parking_lot"), PARK("park"), LEAVE("leave"), STATUS("status"), REGISTRATION_NUMS_WITH_COLOR(
			"registration_numbers_for_cars_with_colour"), SLOT_NUMS_WITH_COLOR(
					"slot_numbers_for_cars_with_colour"), SLOT_NUM_WITH_REG_NUM("slot_number_for_registration_number");

	private final String key;

	private static final Map<String, Instruction> ourNameToInstructionMapping = new HashMap<String, Instruction>();
	static {
		for (Instruction param : values()) {
			ourNameToInstructionMapping.put(param.key, param);
		}
	}

	private Instruction(String s) {
		this.key = s;
	}

	public static Instruction getInstructionByName(String s) {
		return ourNameToInstructionMapping.get(s);
	}

}
