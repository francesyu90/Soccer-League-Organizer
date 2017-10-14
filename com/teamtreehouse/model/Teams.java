package com.teamtreehouse.model;

import java.util.ArrayList;
import java.util.List;

public class Teams {

	private List<Team> mTeamList;

	public Teams() {
		this.mTeamList = new ArrayList<>();
	}

	public void addTeam(Team team) {
		this.mTeamList.add(team);
		System.out.printf("%s added.%n%n", team);
	}

	public void setTeamList(List<Team> teamList) {
		if(teamList == null) {
			return;
		}
		this.mTeamList = teamList;
	}

	public List<Team> getTeamList() {
		return this.mTeamList;
	}
}