package com.teamtreehouse.utils;

public class MessageTemplate {

	/*
		Error
	*/
	public static final String invalidInputForNumericValue = "Cannot convert a non-numeric value (%s) to numeric";
	public static final String invalidInputForIndex = "Numeric value (%d) is an invalid index";

	public static final String nullActionByDescritpion = "Cannot get enum value of type Action given %s";
	public static final String playerRemoveFromTeam = "Cannot remove player %s from %s";
	public static final String playerAddToTeam = "Cannot add player %s to %s";
	public static final String teamRemoveFromTeam = "Cannot remove %s";
	public static final String teamAddToTeam = "Cannot add %s";

	/*
		Warning
	*/
	public static final String teamListSizeEmpty = "Currently, there is no team available. Please return to main menu to create one.";
	public static final String playerListSizeEmpty = "Currently, there is no team member assigned to %s";
	public static final String playerListSizeFull = "Currently, %s is full. Please try again later.";
	public static final String teamListSizeExceeded = "No more team is allowed. [ #. of teams: %d, #. of players: %d]";

}