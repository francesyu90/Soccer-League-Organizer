package com.teamtreehouse.utils;

public enum Action {
	Create("create"), 
	Add("add"), 
	Remove("remove"), 
	Report("report"), 
	Balance("balance"), 
	Roster("roster"), 
	Quit("quit");

	private String mDescription;

	Action(String description) {
		mDescription = description;
	}

	public String getDescription() {
		return mDescription;
	}
}