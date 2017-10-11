package com.teamtreehouse.utils;

import java.util.HashMap;
import java.util.Map;

public class Utility {

	private static final String[] actions = {
  		"Create", "Add", "Remove", "Report", "Balance", "Roster", "Quit" };
  	private static final String[] values = {
  		"Create a new team", 
  		"Add a player to team",
  		"Remove a player from a team",
  		"View a report of a team by height",
  		"View the League Balance Report",
  		"View roster",
  		"Exit the program"};

	public static final Map<String, String> generateMenu() {
		Map<String, String> menu = new HashMap<>();
		int size = actions.length;
	  	for(int i = 0; i < actions.length; i++) {
	  		menu.put(actions[i], values[i]);
	  	}
	  	return menu;
	}


}