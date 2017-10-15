package com.teamtreehouse.utils;

import java.util.HashMap;
import java.util.Map;

public enum ExperienceLevel {
	INEXPERICED(false), 
	EXPERIENCED(true);

	private Boolean mValue;

	ExperienceLevel(Boolean value) {
		mValue = value;
	}

	private static final Map<Boolean, ExperienceLevel> map;
	static {
		map = new HashMap<>();
		for(ExperienceLevel experienceLevel: ExperienceLevel.values()) {
			map.put(experienceLevel.mValue, experienceLevel);
		}
	}

	public static ExperienceLevel findByKey(Boolean value) throws SLOException {
		ExperienceLevel experienceLevel = map.get(value);
		if(experienceLevel == null) {
			throw new SLOException(
				SLCode.SL0024, 
				MessageTemplate.nullExperienceLevelByValue, 
				value);
		}
		return experienceLevel;
	}

}