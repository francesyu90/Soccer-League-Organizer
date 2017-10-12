package com.teamtreehouse.model;

import java.util.ArrayList;
import java.util.List;

public class Teams {

	private List<Team> teams;

	public Teams() {
		this.teams = new ArrayList<>();
	}

	public void addTeam(Team team) {
		this.teams.add(team);
		System.out.printf("%s added.%n%n", team);
	}

	public List<Team> getTeams() {
		return this.teams;
	}
}