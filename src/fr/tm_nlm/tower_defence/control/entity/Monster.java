package fr.tm_nlm.tower_defence.control.entity;

import java.util.LinkedList;

import fr.tm_nlm.tower_defence.control.Entity;
import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;
import fr.tm_nlm.tower_defence.control.entity.fieldTile.PathNode;
import fr.tm_nlm.tower_defence.control.entity.monster.Deplacement;
import fr.tm_nlm.tower_defence.control.entity.monster.Flying;
import fr.tm_nlm.tower_defence.control.entity.monster.Option;
import fr.tm_nlm.tower_defence.control.entity.monster.Walking;

public class Monster extends Entity {
	public static Monster dummy() {
		return new Monster();
	}
	private int strength;
	private double baseSpeed;
	private double speedBoost; //Pourcentage
	private Deplacement deplacement;
	private PathNode nextNode;
	
	public Monster(Field field, Shape shape, PathNode start) {
		super(field, shape);
		if(!isIn(start)) {
			throw new IllegalArgumentException("Un monstre doit commencer sur une noeud.");
		}
		strength = 1;
		speedBoost = 100d;
		baseSpeed = 10d;
		deplacement = new Walking();
		nextNode = start.getNextToCastle();
	}
	
	private Monster() {
		super(null, null);
	}
	
	private boolean isIn(PathNode pathNode) {
		return getAppareances().getCircle().getPosition().dist(pathNode.getPosition()) < pathNode.getAppareances().getCircle().getRadius()
			   && getField().equals(pathNode.getField());
	}
	
	public void Fly() {
		deplacement = new Flying();
	}
	
	public void process() {
		long nano = getLastNano();
		refreshNano();
		double timeToWalk = System.nanoTime() - nano;
		move(timeToWalk/1000000000d);
	}
	
	private void move(double timeToWalk) {
		setPosition(deplacement.move(getSpeed()*timeToWalk, getAppareances().getShape(), nextNode));
		if(isIn(nextNode)) {
			if(nextNode.isCastle()) {
				field.removeLive(strength);
				kill();
			} else {
				System.out.println("Hey");
				setNextNode(nextNode.getNextToCastle());
			}
		}
		check = false;
	}
	
	public PathNode getNextNode() {
		return nextNode;
	}
	private void setNextNode(PathNode nextNode) {
		this.nextNode = nextNode;
	}
	public Monster getMonster() {
		return this;
	}
	public double getSpeed() {
		return baseSpeed*speedBoost/100d;
	}
	public void setBaseSpeed(double baseSpeed) {
		if(baseSpeed < 0)  {
			throw new IllegalArgumentException("Speed must be over 9000!, hum over 0");
		}
		this.baseSpeed = baseSpeed;
	}
	public LinkedList<Option> getOptions() {
		LinkedList<Option> options = new LinkedList<>();
		options.add(deplacement);
		return options;
	}
}
