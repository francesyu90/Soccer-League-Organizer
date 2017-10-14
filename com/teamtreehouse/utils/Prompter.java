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
			throw new SLOException(SLCode.SL0002, ioe.getMessage());
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
			throw new SLOException(SLCode.SL0003, ioe.getMessage());
		}

	}

	public static Integer getPlayerIndexFromUser(Player[] players) throws SLOException {

		if(players == null) {
			throw new SLOException(SLCode.SL0021, MessageTemplate.nullArrayForProcessing);
		}

		Integer playerIndex = -1;

		do {

			System.out.println("Please select one of the following players.");
			showPlayers(players);
			System.out.printf("Select a player [between 1 and %d]: ", players.length);
			playerIndex = getNumericInputFromUser() - 1;

		} while(playerIndex < 0 || playerIndex >= players.length);

		return playerIndex;

	}

	public static Integer getTeamIndexFromUser(Team[] teams) throws SLOException {

		if(teams == null) {
			throw new SLOException(SLCode.SL0022, MessageTemplate.nullArrayForProcessing);
		}

		Integer teamIndex = -1;

		do {

			System.out.println("Please select one of the following teams.");
			showTeams(teams);
			System.out.printf("Select a team [between 1 and %d]: ", teams.length);
			teamIndex = getNumericInputFromUser() - 1;

		} while(teamIndex < 0 || teamIndex >= teams.length);

		return teamIndex;

	}

	public static void showPlayers(Player[] players) throws SLOException {
		if(players == null) {
			throw new SLOException(SLCode.SL0020, MessageTemplate.nullArrayForProcessing);
		}
		for(int i = 0; i < players.length; i++) {
			Player player = players[i];
			System.out.printf("%d.) %s%n", i+1, player);
		}
		System.out.println("\n");
	}

	public static void showCountByHeightReport(Map<Integer, Integer> countByHeightMap) {
		for (Map.Entry<Integer, Integer> entry : countByHeightMap.entrySet()) {
			System.out.printf("%d inches: %d player(s)%n", entry.getKey(), entry.getValue());
		}
		System.out.println("\n");
	}

	/* Private methods */


	private static Integer getNumericInputFromUser() throws SLOException {

		String input = null;
		Integer numericInput = -1;

		try {
			input = reader.readLine();
			numericInput = Integer.parseInt(input);

		} catch(NumberFormatException nfe) {

			nfe.printStackTrace();
			throw new SLOException(
				SLCode.SL0004, 
				MessageTemplate.invalidInputForNumericValue, input);

		} catch(IOException ioe) {

			ioe.printStackTrace();
			throw new SLOException(SLCode.SL0005, ioe.getMessage());

		} 

		return numericInput;

	}

	private static void displayMenu() {
		Map<Action, String> menu = Utility.generateMenu();
		System.out.println("Menu");
    	for(Map.Entry entry: menu.entrySet()) {
    		System.out.printf("%s - %s%n", entry.getKey(), entry.getValue());
    	}
	}

	private static void showTeams(Team[] teams) {
		
		for(int i = 0; i < teams.length; i++) {
			Team team = teams[i];
			System.out.printf("%d.) %s%n", i+1, team) ;
		}
		System.out.println("\n");
	}


}