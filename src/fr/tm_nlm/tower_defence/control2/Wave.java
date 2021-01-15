package fr.tm_nlm.tower_defence.control2;

import java.util.LinkedList;

import fr.tm_nlm.tower_defence.Couple;

public class Wave {
	private Map map;
	/**
	 * 
	 */
	private LinkedList<Couple<Monster, Double>> monsters;
	
	{
		monsters = new LinkedList<>();
	}
	public Wave() {
	}
	
	public void run() {
		if(!monsters.isEmpty()) {
			if(Game.time() > monsters.peek()._2) {
				Monster monster = monsters.poll()._1;
				map.place(monster);
			}
		}
	}
	
	public void add(Monster monster, double wait) {
		if(monsters.isEmpty()) {
			monsters.add(new Couple<>(monster, wait));
		} else {
			double time = monsters.peekLast()._2 + wait;
			monsters.add(new Couple<>(monster, time));
		}
	}
	public void setMap(Map map) {
		this.map = map;
	}
}
