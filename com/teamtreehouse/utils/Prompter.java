package com.teamtreehouse.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Map;

import com.teamtreehouse.model.Team;
import com.teamtreehouse.model.Player;

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

	public static Team promptUserInputForTeamCreation() throws SLOException {

		try {

			System.out.printf("What is the team name? ");
			String teamName = reader.readLine();
			System.out.printf("What is the coach name? ");
			String coachName = reader.readLine();

			return new Team(teamName, coachName);


		} catch(IOException ioe) {
			ioe.printStackTrace();
			throw new SLOException(SLErrorCode.SL0003, ioe.getMessage());
		}

	}

	public static void showPlayers(Player[] players) {
		for(int i = 0; i < players.length; i++) {
			Player player = players[i];
			System.out.printf("%d) %s%n", i+1, player);
		}
		System.out.println("\n");
	}

}