package com.teamtreehouse.utils;

import java.util.List;
import java.util.Map;

import com.teamtreehouse.model.Team;
import com.teamtreehouse.model.Teams;
import com.teamtreehouse.model.Player;

public class Executor {

	public static Teams execAction(
		String option, 
		Teams teams,
		Player[] players) throws SLOException {

		Action action = Action.findByKey(option);
		Map<Action, String> menu = Utility.generateMenu();

		System.out.printf("Action selected: %s.\n\n", menu.get(action));

		switch(action) {
			case Create:
				teams = createNewTeam(teams);
				System.out.printf("%d team(s) in total.%n%n", teams.getTeamList().size());
				return teams;
			case Add:
				return assignPlayerToTeam(players, teams);
			default:
				return null;
		}
	}

	private static Teams createNewTeam(Teams teams) throws SLOException {
		Team team = Prompter.promptUserInputForTeamCreation();
		teams.addTeam(team);
		return teams;
	}

	private static Teams assignPlayerToTeam(Player[] players, Teams teams) 
		throws SLOException {

		List<Team> teamList = teams.getTeamList();
		if(teamList.size() == 0) {
			throw new SLOException(SLCode.SL0006, Severity.Warning, MessageTemplate.teamListSizeEmpty);
		} 

		Integer playerIndex = Prompter.getPlayerIndexFromUser(players);
		Player playerToBeAdded = players[playerIndex];
		System.out.printf("%s selected.%n%n", playerToBeAdded.getName());

		Integer teamIndex = 0;
		
		if(teamList.size() > 1) {
			teamIndex = Prompter.getTeamIndexFromUser(teamList.toArray(new Team[teamList.size()]));
		}
			
		System.out.printf("%s selected.%n%n", teamList.get(teamIndex));

		teamList.get(teamIndex).addPlayer(playerToBeAdded);
		teams.setTeamList(teamList);
		return teams;

	}


}