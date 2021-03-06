package com.teamtreehouse.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.teamtreehouse.utils.MessageTemplate;
import com.teamtreehouse.utils.SLCode;
import com.teamtreehouse.utils.SLOException;

public class Teams {

	private Set<Team> mTeamSet;

	public Teams() {
		this.mTeamSet = new TreeSet<>();
	}

	public void addTeam(Team team, Integer playerSize) throws SLOException {
		
		Integer expectedNumOfTeams =  playerSize / Team.MAX_NUMBER_OF_PLAYER; 

		if((mTeamSet.size() + 1) > expectedNumOfTeams) {
			throw new SLOException(
				SLCode.SL0014, 
				MessageTemplate.teamListSizeExceeded, 
				mTeamSet.size(),
				playerSize);
		}
		Boolean isAdded = this.mTeamSet.add(team);
		if(!isAdded) {
			throw new SLOException(SLCode.SL0013, MessageTemplate.teamAddToTeam, team);
		}
		System.out.printf("%s added.%n%n", team);
	}

	public void setTeamSet(Set<Team> teamList) {
		if(teamList == null) {
			return;
		}
		this.mTeamSet = teamList;
	}

	public Set<Team> getTeamSet() {
		return this.mTeamSet;
	}

	public void updateTeam(Integer teamIndex, Team team) throws SLOException {
		if(team == null) {
			return;
		}

		if(teamIndex < 0 || teamIndex >= this.mTeamSet.size()) {
			throw new SLOException(SLCode.SL0012, MessageTemplate.invalidInputForIndex, teamIndex);
		}

		List<Team> teamList = new ArrayList<>(this.mTeamSet);
		teamList.set(teamIndex, team);
		this.mTeamSet = new TreeSet<>(teamList);
	}
}