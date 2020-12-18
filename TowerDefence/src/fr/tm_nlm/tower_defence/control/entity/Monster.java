package fr.tm_nlm.tower_defence.control.entity;

import java.util.HashMap;
import java.util.LinkedList;

import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.Damageable;
import fr.tm_nlm.tower_defence.control.Entity;
import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;

public class Monster extends Entity implements Damageable {
	private int maxHealth;
	private int currentHealth;
	private double speed;
	private HashMap<Effect, Integer> currentEffects;
	private LinkedList<Vector> objectives;
	
	public Monster(Field field, Shape shape, double speed) {
		super(field, shape);
		this.speed = speed;
		objectives = new LinkedList<>();
	}

	@Override
	public void damage(int value) {
		currentHealth -= value;
		if(currentHealth < 0) {
			currentHealth = 0;
		}
	}

	@Override
	public void heal(int value) {
		currentHealth -= value;
		if(currentHealth > maxHealth) {
			currentHealth = maxHealth;
		}
	}

	@Override
	public int getHealth() {
		return currentHealth;
	}
	
	private enum Effect {
		Burning,
		Rage,
		Rooted,
		Slowed,
		Undying;
	}
}
