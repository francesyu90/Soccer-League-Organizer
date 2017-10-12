package com.teamtreehouse.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Map;

public class Prompter {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static void displayMenu() {
		Map<Action, String> menu = Utility.generateMenu();
		System.out.println("Menu");
    	for(Map.Entry entry: menu.entrySet()) {
    		System.out.printf("%s - %s%n", entry.getKey(), entry.getValue());
    	}
	}

	public static String promptUserInputForOption() throws SLOException {

		String option = null;
		System.out.print("\nSelect an option: ");
		try {
			option = reader.readLine();
		} catch(IOException ioe) {
			ioe.printStackTrace();
			throw new SLOException(SLErrorCode.SL0002, ioe.getMessage());
		}

		return option;
	}	

	public static void promptUserInputForTeamCreation() {
		System.out.println("What is the team name? \n");
	}

}