package com.teamtreehouse.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.teamtreehouse.model.Team;
import com.teamtreehouse.model.Teams;
import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;

public class Executor {

	private static Players mPlayers;

	public static Teams execAction(
		String option, 
		Teams teams,
		Players players) throws SLOException {

		mPlayers = players;

		Action action = Action.findByKey(option);
		Map<Action, String> menu = Utility.generateMenu();

		System.out.printf("Action selected: %s.\n\n", menu.get(action));

		switch(action) {
			case Create:
				teams = createNewTeam(teams, mPlayers.getAvailablePlayersAsSet().size());
				System.out.printf("%d team(s) in total.%n%n", teams.getTeamSet().size());
				return teams;
			case Add:
				return assignPlayerToTeam(teams);
			case Remove:
				return removePlayerFromTeam(teams);
			case Report:
				viewReportByHeight(teams);
			case Balance:
				viewLeagueBalanceReport(teams);
			default:
				return teams;
		}
	}

	public static Players getUpdatedPlayers() {
		return mPlayers;
	}

	private static Teams createNewTeam(Teams teams, Integer playerSize) throws SLOException {
		Team team = Prompter.promptUserInputForTeamCreation();
		teams.addTeam(team, playerSize);
		return teams;
	}

	private static Teams assignPlayerToTeam(Teams teams) 
		throws SLOException {

		List<Team> teamList = new ArrayList<>(teams.getTeamSet());
		if(teamList.size() == 0) {
			throw new SLOException(
				SLCode.SL0006, 
				Severity.Warning, 
				MessageTemplate.teamListSizeEmpty);
		} 

		Player[] playerArr = mPlayers.getPlayers();
		Integer playerIndex = Prompter.getPlayerIndexFromUser(playerArr);
		Player playerToBeAdded = playerArr[playerIndex];
		System.out.printf("%s selected.%n%n", playerToBeAdded.getName());

		Integer teamIndex = 0;
		
		if(teamList.size() > 1) {
			teamIndex = Prompter.getTeamIndexFromUser(
				teamList.toArray(new Team[teamList.size()]));
		}

		Team team = teamList.get(teamIndex);
			
		System.out.printf("%s selected.%n%n", team);

		team.addPlayer(playerToBeAdded);
		teams.updateTeam(teamIndex, team);
		mPlayers.removePlayer(playerToBeAdded);

		return teams;

	}

	private static Teams removePlayerFromTeam(Teams teams) throws SLOException {

		List<Team> teamList = new ArrayList<>(teams.getTeamSet());
		if(teamList.size() == 0) {
			throw new SLOException(
				SLCode.SL0011, 
				Severity.Warning, 
				MessageTemplate.teamListSizeEmpty);
		} 

		Integer teamIndex = 0;
		
		if(teamList.size() > 1) {
			teamIndex = Prompter.getTeamIndexFromUser(
				teamList.toArray(new Team[teamList.size()]));
		}
		
		Team team = teamList.get(teamIndex);
		System.out.printf("%s selected.%n%n", team);

		team.checkForPlayerListSize();

		Player[] players = team.getPlayers();
		Integer playerIndex = Prompter.getPlayerIndexFromUser(players);
		Player playerToBeDeleted = players[playerIndex];
		System.out.printf("%s selected.%n%n", playerToBeDeleted.getName());

		team.removePlayer(playerToBeDeleted);
		teams.updateTeam(teamIndex, team);

		mPlayers.addPlayer(playerToBeDeleted);

		return teams;

	}

	private static void viewReportByHeight(Teams teams) throws SLOException {

		List<Team> teamList = new ArrayList<>(teams.getTeamSet());
		if(teamList.size() == 0) {
			throw new SLOException(
				SLCode.SL0019, 
				Severity.Warning, 
				MessageTemplate.teamListSizeEmpty);
		} 

		Integer teamIndex = 0;
		
		if(teamList.size() > 1) {
			teamIndex = Prompter.getTeamIndexFromUser(
				teamList.toArray(new Team[teamList.size()]));
		}

		Team team = teamList.get(teamIndex);
		System.out.printf("%s selected.%n%n", team);

		team.checkForPlayerListSize();

		Map<Integer, TreeSet<Player>> heightPlayerMap = Utility.generateHeightPlayerMap(team.getPlayersAsSet());

		Integer min = 35;
		Integer max = 40;
		displayPlayersByHeightGroupByRange(heightPlayerMap, min, max);

		min = 41;
		max = 46;
		displayPlayersByHeightGroupByRange(heightPlayerMap, min, max);

		min = 47;
		max = 50;
		displayPlayersByHeightGroupByRange(heightPlayerMap, min, max);

		System.out.println("*******************");
		Map<Integer, Integer> countByHeightMap = Utility.generateCountByHeightReport(heightPlayerMap);
		Prompter.showCountByHeightReport(countByHeightMap);
	}

	private static void displayPlayersByHeightGroupByRange(
		Map<Integer, TreeSet<Player>> heightPlayerMap, 
		Integer min, 
		Integer max) throws SLOException{

		Player[] players = Utility.generatePlayersByHeight(heightPlayerMap, min, max);
		System.out.printf("Height range (%d - %d):%n", min, max);
		Prompter.showPlayers(players);
	}

	private static void viewLeagueBalanceReport(Teams teams) throws SLOException {

		List<Team> teamList = new ArrayList<>(teams.getTeamSet());
		if(teamList.size() == 0) {
			throw new SLOException(
				SLCode.SL0023, 
				Severity.Warning, 
				MessageTemplate.teamListSizeEmpty);
		} 

		for(Team team: teamList) {

			System.out.println("*******************");
			System.out.printf("%s%n", team);
			Map<ExperienceLevel, TreeSet<Player>> experienceLevelPlayerMap = 
				Utility.generateExperienceLevelPlayerMap(team.getPlayersAsSet());
			Prompter.showPlayersByExperienceLevel(experienceLevelPlayerMap);
			Map<ExperienceLevel, Integer> report = 
				Utility.generateCountByExperienceLevelReport(experienceLevelPlayerMap);
			Prompter.showAverageExperienceLevel(report, team.getPlayerCount());
			System.out.println();

		}
		System.out.println("\n");

	}


}