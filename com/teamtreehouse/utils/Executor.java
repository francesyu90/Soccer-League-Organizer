package com.teamtreehouse.utils;

import java.util.Map;

import com.teamtreehouse.model.Team;
import com.teamtreehouse.model.Teams;

public class Executor {

	public static void execAction(String option, Teams teams) throws SLOException {
		Action action = Action.findByKey(option);
		Map<Action, String> menu = Utility.generateMenu();

		System.out.printf("Action selected: %s.\n\n", menu.get(action));

		switch(action) {
			case Create:
				teams = createNewTeam(teams);
				System.out.printf("%d team(s) in total.%n%n", teams.getTeams().size());
				break;
			default:
		}
	}

	private static Teams createNewTeam(Teams teams) throws SLOException {
		Team team = Prompter.promptUserInputForTeamCreation();
		teams.addTeam(team);
		return teams;
	}


}