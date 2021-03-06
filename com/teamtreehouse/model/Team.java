package com.teamtreehouse.model;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import com.teamtreehouse.utils.Severity;
import com.teamtreehouse.utils.SLCode;
import com.teamtreehouse.utils.SLOException;
import com.teamtreehouse.utils.MessageTemplate;

public class Team implements Comparable<Team>, Serializable {
	private static final long serialVersionUID = 2L;

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

	public Integer getPlayerCount() {
		return this.mPlayers.size();
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
	}

	@Override
	public String toString() {
		return String.format("team %s coached by %s", mTeamName, mCoach);
	}

	public String toDetailedStringWithTeamMembers() {
		return String.format("%s %s", this, this.mPlayers);
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

	@Override
	public int compareTo(Team other) {
		if(this.equals(other)) return 0;
		return this.mTeamName.compareTo(other.getTeamName());
  	}

  	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Team)) return false;

	    Team team = (Team) o;

	    if (mTeamName != team.getTeamName()) return false;
	    if (mCoach != team.getCoach()) return false;
	    return mPlayers.equals(team.mPlayers);

  }
}