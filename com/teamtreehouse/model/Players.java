package com.teamtreehouse.model;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import com.teamtreehouse.utils.MessageTemplate;
import com.teamtreehouse.utils.Severity;
import com.teamtreehouse.utils.SLCode;
import com.teamtreehouse.utils.SLOException;

public class Players {

    private Set<Player> mPlayerSet;

    public Players() {
        loadAllPlayers();
    }

    public Player[] getPlayers() {
        Player[] players = new Player[this.mPlayerSet.size()];
        players = this.mPlayerSet.toArray(players);
        return players;
    }

    public Set<Player> getPlayersAsSet() {
        return this.mPlayerSet;
    }

    public void addPlayer(Player player) throws SLOException {
        if(!Arrays.asList(load()).contains(player)) {
            throw new SLOException(
                SLCode.SL0015, 
                Severity.Warning,
                MessageTemplate.unexpectedPlayerFound,
                player.getName());
        }
        Boolean isAdded = this.mPlayerSet.add(player);
        if(!isAdded) {
            throw new SLOException(
                SLCode.SL0016, 
                MessageTemplate.playerAddToSet, 
                player.getName());
        }
    }

    public void removePlayer(Player player) throws SLOException {
        if(!Arrays.asList(load()).contains(player)) {
            throw new SLOException(
                SLCode.SL0017, 
                Severity.Warning,
                MessageTemplate.unexpectedPlayerFound,
                player.getName());
        }
        Boolean isRemoved = this.mPlayerSet.remove(player);
        if(!isRemoved) {
            throw new SLOException(
                SLCode.SL0018, 
                MessageTemplate.playerRemoveFromSet, 
                player.getName());
        }
    }

    private void loadAllPlayers() {
      this.mPlayerSet = this.getAvailablePlayersAsSet();
    }

    public Set<Player> getAvailablePlayersAsSet() {
        return new TreeSet<>(Arrays.asList(load()));
    }

    private static Player[] load() {
        return new Player[] {
            new Player("Joe", "Smith", 42, true),
            new Player("Jill", "Tanner", 36, true),
            new Player("Bill", "Bon", 43, true),
            new Player("Eva", "Gordon", 45, false),
            new Player("Matt", "Gill", 40, false),
            new Player("Kimmy", "Stein", 41, false),
            new Player("Sammy", "Adams", 45, false),
            new Player("Karl", "Saygan", 42, true),
            new Player("Suzane", "Greenberg", 44, true),
            new Player("Sal", "Dali", 41, false),
            new Player("Joe", "Kavalier", 39, false),
            new Player("Ben", "Finkelstein", 44, false),
            new Player("Diego", "Soto", 41, true),
            new Player("Chloe", "Alaska", 47, false),
            new Player("Arfalseld", "Willis", 43, false),
            new Player("Phillip", "Helm", 44, true),
            new Player("Les", "Clay", 42, true),
            new Player("Herschel", "Krustofski", 45, true),
            new Player("Andrew", "Chalklerz", 42, true),
            new Player("Pasan", "Membrane", 36, true),
            new Player("Kenny", "Lovins", 35, true),
            new Player("Alena", "Sketchings", 45, false),
            new Player("Carling", "Seacharpet", 40, false),
            new Player("Joseph", "Freely", 41, false),
            new Player("Gabe", "Listmaker", 45, false),
            new Player("Jeremy", "Smith", 42, true),
            new Player("Ben", "Droid", 44, true),
            new Player("James", "Dothnette", 41, false),
            new Player("Nick", "Grande", 39, false),
            new Player("Will", "Guyam", 44, false),
            new Player("Jason", "Seaver", 41, true),
            new Player("Johnny", "Thunder", 47, false),
            new Player("Ryan", "Creedson", 43, false)
        };

}

}