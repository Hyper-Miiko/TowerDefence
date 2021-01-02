package fr.tm_nlm.tower_defence.control.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import fr.tm_nlm.tower_defence.Couple;
import fr.tm_nlm.tower_defence.control.Entity;
import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;

public abstract class Tower extends Entity {
	private static HashMap<Field, HashMap<Tower, Boolean>> addables = new HashMap<>();
	
	public static HashMap<Tower, Boolean> getAddables(Field field) {
		return addables.get(field);
	}
	
	private boolean obstacle;
	private int cost;
	private int evolveCost;
	private double cooldown;
	private double dammage;
	private double health;
	private double maxHealth;
	private double range;
	private String name;
	private Tower evolution;

	public Tower(Field field, Shape shape, String name) {
		super(field, shape);
		obstacle = false;
		cost = 5;
		evolveCost = -1;
		cooldown = 1d;
		dammage = 2d;
		maxHealth = 20d;
		health = maxHealth;
		range = 100d;
		this.name = name;
		evolution = null;
		
		HashMap<Tower, Boolean> addables = getAddables(field);
		if(addables == null) {
			addables = new HashMap<>();
			Tower.addables.put(field, addables);
		}
		addables.put(this, false);
	}
	
	public Tower evolve() {
		if(evolution == null) {
			throw new IllegalStateException("L'entité n'a pas d'évolution disponible.");
		}
		HashMap<Tower, Boolean> addable = getAddables(field);
		addable.put(this, false);
		addable.put(evolution, true);
		return evolution;
	}
	
	public void setObstacle(boolean obstacle) {
		this.obstacle = obstacle;
	}
	
	public boolean isObstacle() {
		return obstacle;
	}
}
