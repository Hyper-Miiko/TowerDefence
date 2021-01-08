package fr.tm_nlm.tower_defence.control.entity;

import java.util.LinkedList;

import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;
import fr.tm_nlm.tower_defence.control.entity.monster.Deplacement;
import fr.tm_nlm.tower_defence.control.entity.monster.Flying;
import fr.tm_nlm.tower_defence.control.entity.monster.Option;
import fr.tm_nlm.tower_defence.control.entity.monster.Walking;

public class Monster extends Entity implements Damageable, Movable {
	public static Monster dummy() {
		return new Monster();
	}
	private int strength;
	private double baseSpeed;
	private double health;
	private double maxHealth;
	private double speedBoost; //Pourcentage
	private Deplacement deplacement;
	private PathNode nextNode;
	private String monsterType;
	
	{
		deplacement = new Walking();
		strength = 1;
		speedBoost = 100d;
		baseSpeed = 10d;
		maxHealth = 10;
		health = maxHealth;
		monsterType = "undefined";
	}
	public Monster(Field field, Shape shape, PathNode start) {
		super(field, shape);
		nextNode = start.getNextToCastle();
	}
	
	public Monster(Field field, Shape shape) {
		super(field, shape);
		nextNode = null;
	}
	
	private Monster() {
		super(null, null);
	}
	
	private boolean isIn(PathNode pathNode) {
		return getAppareances()
				.getCircle()
				.getPosition()
				.dist(pathNode
						.getPosition()) < pathNode
				.getAppareances()
				.getCircle()
				.getRadius()
			   && getField()
			   .equals(pathNode
					   .getField());
	}
	
	public void dealDamage(double damage) {
		health = (damage > health) ? 0 : health - damage;
		if(health == 0) {
			kill();
		}
	}
	
	public void fly() {
		deplacement = new Flying();
	}
	
	public void place(PathNode pathNode) {
		if(getPosition() != null) {
			throw new IllegalStateException("Can't place monster already on field");
		}
		getAppareances().getShape().setPosition(pathNode.getPosition());
		nextNode = pathNode.getNextToCastle();
		field.add(this);
	}
	
	public void process() {
		move();
	}
	
	private void move(double timeToWalk) {
		setPosition(deplacement.move(getSpeed()*timeToWalk, getAppareances().getShape(), nextNode));
		if(isIn(nextNode)) {
			if(nextNode.isCastle()) {
				field.removeLive(strength);
				kill();
			} else {
				setNextNode(nextNode.getNextToCastle());
			}
		}
		check = false;
	}
	
	public double travelTime() {
		double dist = getPosition().dist(nextNode.getPosition());
		dist += nextNode.getDistToCastle();
		double time = dist/getSpeed();
		return time;
	}
	
	public PathNode getNextNode() {
		return nextNode;
	}
	private void setNextNode(PathNode nextNode) {
		this.nextNode = nextNode;
	}
	public void setMonsterType(String monsterType) {
		this.monsterType = monsterType;
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
	
	@Override
	public String toString() {
		String str = super.toString();
		str += ": " + monsterType;
		str += " est en " + getPosition();
		str += ", a " + (int) health + "/" + (int) maxHealth + " points de vie";
		str += " et sera arrivé dans " + (double) ((int) (travelTime()*10))/10d + " secondes.";
		return str;
	}

	@Override
	public void move() {
		long nano = getLastNano();
		refreshNano();
		double timeToWalk = System.nanoTime() - nano;
		if(getPosition() != null) {
			move(timeToWalk/1000000000d);
		}
	}

	@Override
	public double getAngle() {
		return getPosition().angle(nextNode.getPosition());
	}
}
