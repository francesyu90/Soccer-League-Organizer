package com.teamtreehouse.utils;

import java.util.HashMap;
import java.util.Map;

public enum Action {
	Create("create"), 
	Add("add"), 
	Remove("remove"), 
	Report("report"), 
	Balance("balance"), 
	Roster("roster"), 
	Quit("quit");

	private String mDescription;

	private static final Map<String, Action> map;
	static {
		map = new HashMap<>();
		for(Action action: Action.values()) {
			map.put(action.mDescription, action);
		}
	}

	Action(String description) {
		mDescription = description;
	}

	public String getDescription() {
		return mDescription;
	}

	public static Action findByKey(String description) throws SLOException {
		Action action = map.get(description);
		if(action == null) {
			throw new SLOException(
				SLErrorCode.SL0001, 
				MessageTemplate.nullActionByDescritpion, 
				description);
		}
		return map.get(description);
	}
}



