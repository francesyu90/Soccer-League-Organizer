package com.teamtreehouse.utils;

import java.util.Map;

public class Executor {

	public static void execAction(String option) throws SLOException {
		Action action = Action.findByKey(option);
		Map<Action, String> menu = Utility.generateMenu();

		System.out.printf("Action selected: %s.\n\n", menu.get(action));

		switch(action) {
			case Create:
				createNewTeam();
				break;
			default:
		}


	}

	private static void createNewTeam() {
		Prompter.promptUserInputForTeamCreation();
	}


}