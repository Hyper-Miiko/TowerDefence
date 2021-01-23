package fr.tm_nlm.tower_defence.control2;

import java.util.LinkedList;

import fr.tm_nlm.tower_defence.Couple;

public class Wave {
	private double lastPlace;
	private Map map;
	/**
	 * 
	 */
	private LinkedList<Couple<Monster, Double>> monsters;
	private String name;
	private String music;
	private Wave nextWave;
	
	{
		monsters = new LinkedList<>();
	}
	public Wave(String name) {
		this.name = name;
	}
	
	public void run() {
		if(!monsters.isEmpty()) {
			if(Game.time() - lastPlace > monsters.peek()._2) {
				lastPlace = Game.time();
				Monster monster = monsters.poll()._1;
				map.place(monster);
			}
		}
	}
	
	public void add(Monster monster, double wait) {
		monsters.add(new Couple<>(monster, wait));
	}
	public void setMap(Map map) {
		this.map = map;
		if(nextWave != null) {
			nextWave.setMap(map);
		}
	}
	public String getName() {
		return name;
	}
	public void setNextWave(Wave wave) {
		nextWave = wave;
	}
	public Wave getNextWave() {
		nextWave.lastPlace = lastPlace;
		return nextWave;
	}
	public boolean isOver() {
		return monsters.isEmpty();
	}
	public void setMusic(String music) {
		this.music = music;
	}
	public String getMusic() {
		return music;
	}
	public boolean isLast() {
		return nextWave == null;
	}
}
