package com.teamtreehouse.model;

import java.util.TreeSet;
import java.util.Set;

import com.teamtreehouse.utils.Severity;
import com.teamtreehouse.utils.SLCode;
import com.teamtreehouse.utils.SLOException;
import com.teamtreehouse.utils.MessageTemplate;

public class Team {

	private String mTeamName;
	private String mCoach;
	public static final Integer MAX_NUMBER_OF_PLAYER = 11;
	private Set<Player> mPlayers;

	public Team(String teamName, String coach) {
		this.mTeamName = teamName;
		this.mCoach = coach;
		this.mPlayers = new TreeSet<>();
	}

	public void setTeamName(String teamName) {
		this.mTeamName = teamName;
	}

	public void setCoach(String coach) {
		this.mCoach = coach;
	}

	public void setTeams(Set<Player> players) {
		this.mPlayers = players;
	}

	public String getTeamName() {
		return this.mTeamName;
	}

	public String getCoach() {
		return this.mCoach;
	}

	public Player[] getPlayers() throws SLOException {
		this.checkForPlayerListSize();
		Player[] players = new Player[this.mPlayers.size()];
		players = this.mPlayers.toArray(players);
		return players;
	}

	public Set<Player> getPlayersAsSet() throws SLOException {
		this.checkForPlayerListSize();
		return this.mPlayers;
	}

	public void removePlayer(Player playerToBeDeleted) throws SLOException {
		this.checkForPlayerListSize();
		Boolean isDeleted = this.mPlayers.remove(playerToBeDeleted);
		if(!isDeleted) {
			throw new SLOException(
				SLCode.SL0008, 
				MessageTemplate.playerRemoveFromTeam, 
				playerToBeDeleted.getName(), 
				this);
		}
	}

	public void addPlayer(Player playerToBeAdded) throws SLOException {
		if(this.mPlayers.size() == MAX_NUMBER_OF_PLAYER) {
			throw new SLOException(
				SLCode.SL0009, 
				Severity.Warning,
				MessageTemplate.playerListSizeFull, 
				this);
		}
		Boolean isAdded = this.mPlayers.add(playerToBeAdded);
		if(!isAdded) {
			throw new SLOException(
				SLCode.SL0010,
				MessageTemplate.playerAddToTeam,
				playerToBeAdded,
				this);
		}
		System.out.println(this.toDetailedStringWithTeamMembers());
	}

	@Override
	public String toString() {
		return String.format("team %s coached by %s", mTeamName, mCoach);
	}

	public String toDetailedStringWithTeamMembers() {
		return String.format("%s [%s]", this, this.mPlayers);
	}

	public void checkForPlayerListSize() throws SLOException {
		if(this.mPlayers.size() == 0) {
			throw new SLOException(
				SLCode.SL0007, 
				Severity.Warning, 
				MessageTemplate.playerListSizeEmpty, 
				this);
		}
	}
}