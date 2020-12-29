package fr.tm_nlm.tower_defence.control.entity;

import fr.tm_nlm.tower_defence.control.Entity;
import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;
import fr.tm_nlm.tower_defence.control.entity.fieldTile.PathBridge;
import fr.tm_nlm.tower_defence.control.entity.fieldTile.PathNode;
import fr.tm_nlm.tower_defence.control.entity.monster.Deplacement;
import fr.tm_nlm.tower_defence.control.entity.monster.Flying;
import fr.tm_nlm.tower_defence.control.entity.monster.Walking;

public class Monster extends Entity {
	public Deplacement deplacement;
	private final double speed;
	private double slow; //Pourcentage
	private PathNode nextNode;
	private PathBridge bridge;
	
	public Monster(Field field, Shape shape, double speed) {
		super(field, shape);
		this.speed = speed;
		slow = 0;
		deplacement = new Walking();
	}
	
	public void Fly() {
		deplacement = new Flying();
	}
	
	public void move() {
		setPosition(deplacement.move(getSpeed(), getAppareances().getShape(), nextNode, bridge));
	}
	
	public PathNode getNextNode() {
		return nextNode;
	}
	public Monster getMonster() {
		return this;
	}
	public double getSpeed() {
		return speed*(100d - slow)/100d;
	}
}
