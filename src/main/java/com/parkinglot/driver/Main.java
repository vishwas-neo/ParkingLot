package com.parkinglot.driver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Entry point for jar.
 * 
 * @author vishwas
 *
 */
public class Main {

	private static boolean isInteractive;
	private static BufferedReader bReader;
	private static Scanner sc;

	public static void main(String[] args) {

		MainDriver driver = new MainDriver();

		if (!init(args)) {
			System.out.println("File not found: " + args[0]);
			return;
		}

		String line;

		while ((line = getNextLine()) != null) {
			line.trim();
			if (!line.isEmpty()) {
				System.out.println(driver.processLine(line));
			}
		}

		close();
	}

	/**
	 * Reads next line from the input stream.
	 * 
	 * @return Next line <br>
	 *         Returns null if file is end of file reached or exception occured.
	 */
	public static String getNextLine() {
		if (isInteractive)
			return sc.nextLine();
		else {
			try {
				return bReader.readLine();
			} catch (IOException e) {
				return null;
			}
		}
	}

	/**
	 * closes the input streams.
	 */
	public static void close() {
		if (isInteractive) {
			sc.close();
		} else {
			try {
				bReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * It initializes the BufferedReader if input is taken from a file. Scanner
	 * object if input is taken from command line. Interactive way.
	 * 
	 * @param args
	 *            Command Line arguments.
	 * @return false if input file passed in command line arguments not found.
	 */
	public static boolean init(String[] args) {
		if (args.length > 0) {
			String inputFile = args[0];
			try {
				bReader = new BufferedReader(new FileReader(new File(inputFile)));
			} catch (FileNotFoundException e) {
				return false;
			}
		} else {
			isInteractive = true;
			sc = new Scanner(System.in);
		}
		return true;
	}
}
