package com.teamtreehouse.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.teamtreehouse.model.Player;

public class Utility {

	private static final Action[] actions = {
  		Action.Create, Action.Add, Action.Remove, Action.Report, Action.Balance, Action.Roster, Action.Quit };
  	private static final String[] values = {
  		"Create a new team", 
  		"Add a player to team",
  		"Remove a player from a team",
  		"View a report of a team by height",
  		"View the League Balance Report",
  		"View roster",
  		"Exit the program"};

	public static final Map<Action, String> generateMenu() {
		Map<Action, String> menu = new HashMap<>();
		int size = actions.length;
	  	for(int i = 0; i < actions.length; i++) {
	  		menu.put(actions[i], values[i]);
	  	}
	  	return menu;
	}

	public static Player[] generatePlayersByHeight(Set<Player> playerSet) {

		Map<Integer, TreeSet<Player>> heightPlayerMap = generateHeightPlayerMap(playerSet);
		
		List<Player> resultList = new ArrayList<>();

		for (Map.Entry<Integer, TreeSet<Player>> entry: heightPlayerMap.entrySet()) {
			resultList.addAll(entry.getValue());
		}

		return resultList.toArray(new Player[resultList.size()]);
	}

	private static Map<Integer, TreeSet<Player>> generateHeightPlayerMap(Set<Player> playerSet) {

		Map<Integer, TreeSet<Player>> heightPlayerMap = new TreeMap<>();

		for(Player player: playerSet) {
			Integer height = player.getHeightInInches();
			TreeSet<Player> playerSetByHeight = heightPlayerMap.remove(height);
			if(playerSetByHeight == null) {
				playerSetByHeight = new TreeSet<>();
			}
			playerSetByHeight.add(player);
			heightPlayerMap.put(height, playerSetByHeight);
		}

		return heightPlayerMap;	
	}

}