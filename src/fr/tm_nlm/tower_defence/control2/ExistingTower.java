package fr.tm_nlm.tower_defence.control2;

import java.util.HashMap;

public class ExistingTower {
	private static HashMap<Long, Tower> towers = new HashMap<>();
	
	public static Tower get(long id) {
		Tower tower = towers.get(id);
		if(tower == null) {
			throw new IllegalArgumentException("No tower have id " + id + ".");
		}
		return tower;
	}
	
	public static long add(Tower tower) {
		towers.put(tower.getId(), tower);
		return tower.getId();
	}
	
	public static void remove(long id) {
		towers.remove(id);
	}
}
