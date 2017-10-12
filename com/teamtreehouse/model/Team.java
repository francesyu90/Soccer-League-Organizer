package com.teamtreehouse.model;

public class Team {

	private String mTeamName;
	private String mCoach;

	public Team(String teamName, String coach) {
		this.mTeamName = teamName;
		this.mCoach = coach;
	}

	public void setTeamName(String teamName) {
		this.mTeamName = teamName;
	}

	public void setCoach(String coach) {
		this.mCoach = coach;
	}

	public String getTeamName() {
		return this.mTeamName;
	}

	public String getCoach() {
		return this.mCoach;
	}

	@Override
	public String toString() {
		return String.format("Team %s coached by %s", mTeamName, mCoach);
	}
}