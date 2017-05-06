package com.parkinglot.driver;

import java.util.List;

import com.parkinglot.entities.Car;
import com.parkinglot.entities.Instruction;
import com.parkinglot.entities.Ticket;
import com.parkinglot.service.ParkingLot;

public class MainDriver {

	private ParkingLot parkingLot;

	public String processLine(String s) {
		String[] instructionArray = s.split(" ");
		if (!validateInstruction(instructionArray)) {
			return "Invalid Instruction";
		}
		Instruction instruction = Instruction.getInstructionByName(instructionArray[0]);
		String result = null;
		switch (instruction) {
		case CREATE:
			parkingLot = new ParkingLot(Integer.parseInt(instructionArray[1]));
			result = "Created a parking lot with " + instructionArray[1] + " slots";
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
		default:
			return "Invalid Instruction";
		}

		return result;
	}

	private boolean validateInstruction(String[] instructionArray) {
		if (instructionArray[0].startsWith("create") && instructionArray.length != 3)
			return false;
		else if (instructionArray[0].startsWith("status") && instructionArray.length != 1)
			return false;
		else {
			if (instructionArray[0].length() != 2)
				return false;
		}
		if (!instructionArray[0].startsWith("create") && parkingLot == null)
			return false;
		return true;
	}

	private String getResultString(List<String> list) {
		StringBuilder sb = new StringBuilder();
		for (String obj : list) {
			sb.append(obj);
			sb.append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		return sb.toString();
	}

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