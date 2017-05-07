package com.parkinglot.driver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	private static boolean isInteractive;
	private static BufferedReader bReader;
	private static Scanner sc;

	public static void main(String[] args) {

		MainDriver driver = new MainDriver();

		if(!init(args)){
			System.out.println("File not found: "+args[0]);
			return;
		}

		String line;

		while ((line = getNextLine()) != null) {
			line.trim();
			if(!line.isEmpty()){
				System.out.println(driver.processLine(line));
			}
		}

		close();
	}

	public static String getNextLine() {
		if (isInteractive)
			return sc.nextLine();
		else {
			try {
				return bReader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				return null;
			}
		}
	}

	public static void close() {
		if (isInteractive) {
			sc.close();
		} else {
			try {
				bReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

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
