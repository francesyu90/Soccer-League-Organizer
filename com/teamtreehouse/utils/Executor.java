package com.teamtreehouse.utils;

import java.util.List;
import java.util.Map;

import com.teamtreehouse.model.Team;
import com.teamtreehouse.model.Teams;
import com.teamtreehouse.model.Player;

public class Executor {

	public static void execAction(
		String option, 
		Teams teams,
		Player[] players) throws SLOException {

		Action action = Action.findByKey(option);
		Map<Action, String> menu = Utility.generateMenu();

		System.out.printf("Action selected: %s.\n\n", menu.get(action));

		switch(action) {
			case Create:
				teams = createNewTeam(teams);
				System.out.printf("%d team(s) in total.%n%n", teams.getTeams().size());
				break;
			case Add:
				List<Team> teamList = teams.getTeams();
				assignPlayerToTeam(players, teamList.toArray(new Team[teamList.size()]));
				break;
			default:
		}
	}

	private static Teams createNewTeam(Teams teams) throws SLOException {
		Team team = Prompter.promptUserInputForTeamCreation();
		teams.addTeam(team);
		return teams;
	}

	private static void assignPlayerToTeam(Player[] players, Team[] teams) 
		throws SLOException {
			
		Integer playerIndex = Prompter.getPlayerIndexFromUser(players);
		System.out.printf("%s selected.%n%n", players[playerIndex].getName());

		if(teams.length == 0) {
			throw new SLOException(SLCode.SL0006, Severity.Warning, MessageTemplate.teamListSizeEmpty);
		} else if (teams.length == 1) {
			// TODO: automatically assign team if there is only one team available
			return;
		}

		Integer teamIndex = Prompter.getTeamIndexFromUser(teams);
		System.out.printf("%s selected.%n%n", teams[teamIndex]);


	}


}