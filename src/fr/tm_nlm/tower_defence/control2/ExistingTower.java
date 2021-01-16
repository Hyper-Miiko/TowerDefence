package fr.tm_nlm.tower_defence.control2;

import java.util.HashMap;

public class ExistingTower {
	private static HashMap<String, Tower> towers = new HashMap<>();
	
	public static Tower get(String towerName) {
		return towers.get(towerName);
	}
	
	public static void add(Tower tower) {
		towers.put(tower.getName(), tower);
	}
}
