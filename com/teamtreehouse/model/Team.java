package com.teamtreehouse.model;

import java.util.List;

import com.teamtreehouse.utils.Severity;
import com.teamtreehouse.utils.SLCode;
import com.teamtreehouse.utils.SLOException;
import com.teamtreehouse.utils.MessageTemplate;

public class Team {

	private String mTeamName;
	private String mCoach;
	public static final Integer MAX_NUMBER_OF_PLAYER = 11;
	private List<Player> mPlayers;

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

	public void setTeams(List<Player> players) {
		this.mPlayers = players;
	}

	public String getTeamName() {
		return this.mTeamName;
	}

	public String getCoach() {
		return this.mCoach;
	}

	public Player[] getPlayers() {
		Player[] players = new Player[this.mPlayers.size()];
		players = this.mPlayers.toArray(players);
		return players;
	}

	public List<Player> getPlayersAsList() {
		return this.mPlayers;
	}

	public Boolean removePlayer(Player playerToBeDeleted) {
		if(this.mPlayers.size() == 0) {
			throw new SLOException(
				SLCode.SL0007, 
				Severity.Warning, 
				MessageTemplate.playerListSizeEmpty, 
				this);
		}
		Boolean isDeleted = this.mPlayers.remove(playerToBeDeleted);
		if(!isDeleted) {
			throw new SLOException(
				SLCode.SL0008, 
				MessageTemplate.playerRemoveFromTeam, 
				playerToBeDeleted.getName(), 
				this);
		}
		return isDeleted;
	}

	public Boolean addPlayer(Player playerToAdded) {
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
		return isAdded;
	}

	@Override
	public String toString() {
		return String.format("Team %s coached by %s", mTeamName, mCoach);
	}
}