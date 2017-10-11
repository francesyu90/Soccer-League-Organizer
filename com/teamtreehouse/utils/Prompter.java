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

	public static String promptUserInputForOption() {

		String option = null;
		System.out.print("\nSelect an option: ");
		try(
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		) {
			option = reader.readLine();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}

		return option;
		
	}	

}