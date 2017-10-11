package com.teamtreehouse.utils;

import java.util.Map;

public class Prompter {

	public static void displayMenu() {
		Map<String, String> menu = Utility.generateMenu();
		System.out.println("Menu");
    	for(Map.Entry entry: menu.entrySet()) {
    		System.out.printf("%s - %s%n", entry.getKey(), entry.getValue());
    	}
	}	

}