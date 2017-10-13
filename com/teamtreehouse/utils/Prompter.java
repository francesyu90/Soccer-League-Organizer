package com.teamtreehouse.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Map;

import com.teamtreehouse.model.Team;
import com.teamtreehouse.model.Player;

public class Prompter {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static String promptUserInputForOption() throws SLOException {

		displayMenu();

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

	public static void promptUserInputForMemberAssignment(
		Player[] players, Team[] teams) throws SLOException {
		
		String input = null;
		Integer playerIndex = -1;

		try {

			do {

				System.out.println("Please select one of the following players.");
				showPlayers(players);
				System.out.printf("Select a player [between 1 and %d]: ", players.length);
				input = reader.readLine();
				playerIndex = Integer.parseInt(input) - 1;

			} while(playerIndex < 0 || playerIndex >= players.length);

			System.out.printf("%s selected.%n%n", players[playerIndex].getName());

			showTeams(teams);


		} catch(NumberFormatException nfe) {
			
			nfe.printStackTrace();
			throw new SLOException(
				SLErrorCode.SL0004, 
				MessageTemplate.invalidInputForNumericValue, input);

		} catch(IOException ioe) {

			ioe.printStackTrace();
			throw new SLOException(SLErrorCode.SL0005, ioe.getMessage());

		} 

	}

	/* Private methods */

	private static void displayMenu() {
		Map<Action, String> menu = Utility.generateMenu();
		System.out.println("Menu");
    	for(Map.Entry entry: menu.entrySet()) {
    		System.out.printf("%s - %s%n", entry.getKey(), entry.getValue());
    	}
	}

	private static void showPlayers(Player[] players) {
		for(int i = 0; i < players.length; i++) {
			Player player = players[i];
			System.out.printf("%d) %s%n", i+1, player);
		}
		System.out.println("\n");
	}

	private static void showTeams(Team[] teams) throws SLOException {
		if(teams.length == 0) {
			throw new SLOException(SLErrorCode.SL0006, MessageTemplate.teamSizeEmpty);
		}
		for(int i = 0; i < teams.length; i++) {
			Team team = teams[i];
			System.out.printf("%d) %s%n", i+1, team) ;
		}
		System.out.println("\n");
	}


}