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

	public static Player[] generatePlayersByHeight(
		Map<Integer, TreeSet<Player>> heightPlayerMap, 
		Integer min, 
		Integer max) {
		
		List<Player> resultList = new ArrayList<>();

		for(Integer key = min; key <= max; key++) {
			if(heightPlayerMap.containsKey(key)) {
				resultList.addAll(heightPlayerMap.get(key));
			}
		}

		return resultList.toArray(new Player[resultList.size()]);
	}

	public static Map<Integer, TreeSet<Player>> generateHeightPlayerMap(Set<Player> playerSet) {

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

	public static Map<Integer, Integer> generateCountByHeightReport(
		Map<Integer, TreeSet<Player>> heightPlayerMap) {

		Map<Integer, Integer> resultMap = new TreeMap<>();
		for (Map.Entry<Integer, TreeSet<Player>> entry: heightPlayerMap.entrySet()) {
    		resultMap.put(entry.getKey(), entry.getValue().size());
		}

		return resultMap;

	}

	public static Map<ExperienceLevel, TreeSet<Player>> generateExperienceLevelPlayerMap(
		Set<Player> playerSet) throws SLOException {

		Map<ExperienceLevel, TreeSet<Player>> resultMap = new TreeMap<>();
		for(Player player: playerSet) {
			ExperienceLevel experienceLevel = 
				ExperienceLevel.findByKey(player.isPreviousExperience());
			TreeSet<Player> playerSetByExperienceLevel = resultMap.remove(experienceLevel);
			if(playerSetByExperienceLevel == null) {
				playerSetByExperienceLevel = new TreeSet<>();
			}
			playerSetByExperienceLevel.add(player);
			resultMap.put(experienceLevel, playerSetByExperienceLevel);
		}

		return resultMap;

	}

	public static Map<ExperienceLevel, Integer> generateCountByExperienceLevelReport(
		Map<ExperienceLevel, TreeSet<Player>> experienceLevelPlayerMap) {

		Map<ExperienceLevel, Integer> resultMap = new TreeMap<>();
		for (Map.Entry<ExperienceLevel, TreeSet<Player>> entry: experienceLevelPlayerMap.entrySet()) {
    		resultMap.put(entry.getKey(), entry.getValue().size());
		}

		return resultMap;

	}

}