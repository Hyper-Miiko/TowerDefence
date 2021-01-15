package fr.tm_nlm.tower_defence.control.entity;

import java.util.LinkedList;

import fr.tm_nlm.tower_defence.control.Field;

public class Wave {
	private Double nextMonsterTime;
	private final double interval;
	private LinkedList<Monster> monsters;
	private PathNode pathNode;
	private Field field;
	
	public Wave(PathNode pathNode, double interval) {
		nextMonsterTime = null;
		this.interval = interval;
		monsters = new LinkedList<>();
		this.pathNode = pathNode;
		this.field = pathNode.getField();
	}
	
	public boolean run() {
		double currentTime = ((double) System.nanoTime())/1000000000d;
		if(nextMonsterTime != null && currentTime > nextMonsterTime) {
			nextMonsterTime += interval;
			Monster monster = monsters.poll();
			if(monster != null) {
				field.placeMonster(monster, pathNode);
			} else {
				return false;
			}
		}
		return true;
	}
	
	public void setTimer(double timer) {
		nextMonsterTime = ((double) System.nanoTime())/1000000000d + timer;
	}
	
	public void addMonster(Monster monster) {
		monsters.add(monster);
	}
}
