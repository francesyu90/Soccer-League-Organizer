package com.teamtreehouse.model;

import java.util.ArrayList;
import java.util.List;

import com.teamtreehouse.utils.MessageTemplate;
import com.teamtreehouse.utils.SLCode;
import com.teamtreehouse.utils.SLOException;

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

	public void updateTeam(Integer teamIndex, Team team) throws SLOException {
		if(team == null) {
			return;
		}

		if(teamIndex < 0 || teamIndex >= this.mTeamList.size()) {
			throw new SLOException(SLCode.SL0012, MessageTemplate.invalidInputForIndex, teamIndex);
		}

		this.mTeamList.set(teamIndex, team);
	}
}