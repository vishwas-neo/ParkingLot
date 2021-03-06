package com.parkinglot.driver;

import java.util.List;

import com.parkinglot.entities.Car;
import com.parkinglot.entities.Instruction;
import com.parkinglot.entities.Ticket;
import com.parkinglot.service.ParkingLot;

/**
 * This class is responsible for formatting the output generated by ParkingLot.
 * 
 * @author vishwas
 *
 */
public class MainDriver {

	private ParkingLot parkingLot;

	/**
	 * 
	 * @param s
	 *            Instruction to be executed.
	 * @return Formatted output.
	 * 
	 */
	public String processLine(String s) {
		String[] instructionArray = s.split(" ");
		if (!validateInstruction(instructionArray)) {
			return "Invalid Instruction";
		}
		Instruction instruction = Instruction.getInstructionByName(instructionArray[0]);
		String result = null;
		switch (instruction) {
		case CREATE:
			parkingLot = ParkingLot.getInstance(Integer.parseInt(instructionArray[1]));
			result = "Created a parking lot with " + parkingLot.getCapacity() + " slots";
			break;
		case LEAVE:
			parkingLot.leave(Integer.parseInt(instructionArray[1]));
			result = "Slot number " + instructionArray[1] + " is free";
			break;
		case PARK:
			Ticket ticket = parkingLot.park(new Car(instructionArray[1], instructionArray[2]));
			if (ticket == null) {
				result = "Sorry, parking lot is full";
			} else {
				result = "Allocated slot number: " + ticket.getSlotNo();
			}
			break;
		case REGISTRATION_NUMS_WITH_COLOR:
			List<String> regNumbersByColor = parkingLot.getRegNumbersByColor(instructionArray[1]);
			if (regNumbersByColor == null || regNumbersByColor.isEmpty()) {
				result = "Not found";
			} else {
				result = getResultString(regNumbersByColor);
			}
			break;
		case SLOT_NUM_WITH_REG_NUM:
			Integer slotNumberByRegNumber = parkingLot.getSlotNumberByRegNumber(instructionArray[1]);
			if (slotNumberByRegNumber == null) {
				result = "Not found";
			} else {
				result = slotNumberByRegNumber + "";
			}
			break;
		case SLOT_NUMS_WITH_COLOR:
			List<Integer> slotNumbersByColor = parkingLot.getSlotNumbersByColor(instructionArray[1]);
			if (slotNumbersByColor == null || slotNumbersByColor.isEmpty()) {
				result = "Not found";
			} else {
				result = getResultStringFromInteger(slotNumbersByColor);
			}
			break;
		case STATUS:
			result = parkingLot.getStatus();
			break;
		}

		return result;
	}

	/**
	 * 
	 * @param instructionArray
	 *            : instructionArray[0] will have the Instruction,
	 *            instructionArray[1], instructionArray[2] will have the
	 *            arguments for that instruction.
	 * @return
	 *         <p>
	 *         true when instruction is valid <br>
	 *         false when: instructionArray[0] is not one of those defined in
	 *         Instruction enum. <br>
	 *         Create is not the first instruction. <br>
	 *         Two arguments are not provided for park instruction. <br>
	 *         More than 0 arguments are provided for status. <br>
	 *         1 arguments is not provided for rest of the instructions.
	 * 
	 */
	private boolean validateInstruction(String[] instructionArray) {
		if (Instruction.getInstructionByName(instructionArray[0]) == null) {
			return false;
		}
		if (!instructionArray[0].startsWith("create") && parkingLot == null)
			return false;

		if (instructionArray[0].startsWith("park") && instructionArray.length != 3)
			return false;

		if (instructionArray[0].startsWith("status") && instructionArray.length != 1)
			return false;

		if (!instructionArray[0].startsWith("park") && !instructionArray[0].startsWith("status")
				&& instructionArray.length != 2)
			return false;

		return true;
	}

	/**
	 * 
	 * @param list
	 *            List of String values
	 * @return String having all the values. (comma separated)
	 */
	private String getResultString(List<String> list) {
		StringBuilder sb = new StringBuilder();
		for (String obj : list) {
			sb.append(obj);
			sb.append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		return sb.toString();
	}

	/**
	 * 
	 * @param list
	 *            List of Integer values
	 * @return String having all the values. (comma separated)
	 */
	private static String getResultStringFromInteger(List<Integer> list) {
		StringBuilder sb = new StringBuilder();
		for (Integer obj : list) {
			sb.append(obj);
			sb.append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		return sb.toString();
	}

}
