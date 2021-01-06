package fr.tm_nlm.tower_defence.control.entity;

import java.util.LinkedList;

import fr.tm_nlm.tower_defence.control.Entity;
import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;
import fr.tm_nlm.tower_defence.control.entity.fieldTile.PathBridge;
import fr.tm_nlm.tower_defence.control.entity.fieldTile.PathNode;
import fr.tm_nlm.tower_defence.control.entity.monster.Deplacement;
import fr.tm_nlm.tower_defence.control.entity.monster.Flying;
import fr.tm_nlm.tower_defence.control.entity.monster.Option;
import fr.tm_nlm.tower_defence.control.entity.monster.Walking;

public class Monster extends Entity {
	public static Monster dummy() {
		return new Monster();
	}
	private Deplacement deplacement;
	private int strenght;
	private final double speed;
	private double slow; //Pourcentage
	private PathNode nextNode;
	private PathBridge bridge;
	
	public Monster(Field field, Shape shape, PathNode start, double speed) {
		super(field, shape);
		if(!isIn(start)) {
			throw new IllegalArgumentException("Un monstre doit commencer sur une noeud.");
		}
		this.speed = speed;
		slow = 0;
		deplacement = new Walking();
	}
	
	private Monster() {
		super(null, null);
		speed = 0;
	}
	
	private boolean isIn(PathNode pathNode) {
		return getAppareances().getCircle().getPosition().dist(pathNode.getPosition()) < pathNode.getAppareances().getCircle().getRadius()
			   && getField().equals(pathNode.getField());
	}
	
	public void Fly() {
		deplacement = new Flying();
	}
	
	public void process() {
		move();
		checkCastle();
	}
	
	private void checkCastle() {
		for(PathNode pathNode : field.getPathNodes()) {
			if(pathNode.isCastle()) {
				field.removeLive(strenght);
				kill();
			}
		}
	}
	
	private void move() {
		setPosition(deplacement.move(getSpeed(), getAppareances().getShape(), nextNode, bridge));
		if(isIn(nextNode)) {
			bridge = nextNode.getBridgeToCastle();
		}
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
	public LinkedList<Option> getOptions() {
		LinkedList<Option> options = new LinkedList<>();
		options.add(deplacement);
		return options;
	}
}
