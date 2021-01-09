package fr.tm_nlm.tower_defence.control.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.data.geometric.shape.Circle;

import static fr.tm_nlm.tower_defence.control.entity.Monster.Effect.*;

public class Monster extends Entity implements Damageable, Movable {
	private static final Random random = new Random();
	public static Monster dummy(Vector vector) {
		return new Monster(vector);
	}
	private boolean boss;
	private boolean fly;
	private double strength;
	private double baseSpeed;
	private double health;
	private double maxHealth;
	private Double confuseAngle;
	private PathNode nextNode;
	private String monsterType;
	private HashMap<Effect, double[]> effects;
	
	{
		boss = false;
		fly = false;
		strength = 1;
		baseSpeed = 10d;
		maxHealth = 10;
		confuseAngle = null;
		health = maxHealth;
		monsterType = "undefined";
		effects = new HashMap<>();
		for(Effect effect : Effect.values()) {
			effects.put(effect, null);
		}
	}
	public Monster(Field field, Shape shape, PathNode start) {
		super(field, shape);
		nextNode = start.getNextToCastle();
	}
	
	public Monster(Field field, Shape shape) {
		super(field, shape);
		nextNode = null;
	}
	
	private Monster(Vector vector) {
		super(null, new Circle(vector, 1));
		nextNode = PathNode.dummy(vector);
		kill();
	}
	
	public void affectWith(Effect effect, double... ds) {
		double datas[];
		switch(effect) {
		case BLEED:
			if(ds.length != 2) {
				throw new IllegalArgumentException(effect + " allow 2 and only 2 double.");
			}
			//end time
			//strength
			//last occurence
			datas = effects.get(effect);
			if(datas == null) {
				datas = new double[3];
				datas[2] = ((double) System.nanoTime())/1000000000;
				datas[0] = datas[2] + ds[0];
				datas[1] = ds[1];
			} else {
				datas[0] += ds[0];
				datas[1] = (datas[1] > ds[1]) ? datas[1] : ds[1];
			}
			effects.put(effect, datas);
			break;
		case CONFUSED:
			if(ds.length != 1) {
				throw new IllegalArgumentException(effect + " allow 1 and only 1 double.");
			}
			//Temps de fin
			//Temps prochain changement
			datas = new double[2];
			datas[0] = ((double) System.nanoTime())/1000000000 + ds[0];
			datas[1] = ((double) System.nanoTime())/1000000000;
			confuseAngle = 2*Math.PI*random.nextDouble();
			effects.put(effect, datas);
			break;
		case SLOWED:
			if(ds.length != 2) {
				throw new IllegalArgumentException(effect + " allow 2 and only 2 double.");
			}
			//Temps de fin
			//Force
			datas = new double[2];
			datas[0] = ((double) System.nanoTime())/1000000000 + ds[0];
			datas[1] = ds[1];
			effects.put(effect, datas);
			break;
		}
	}
	
	public void dealDamage(double damage) {
		health = (damage > health) ? 0 : health - damage;
		if(health == 0) {
			kill();
		}
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
		checkDirection();
		effect();
	}
	
	private void checkDirection() {
		double calcSize = getAppareances().getCircle().getRadius();
		double pathNode3_4 = 3*nextNode.getAppareances().getCircle().getRadius()/4;
		calcSize = (calcSize > pathNode3_4) ? pathNode3_4 : calcSize;
		if(getPosition().dist(nextNode.getPosition()) < nextNode.getAppareances().getCircle().getRadius() - calcSize) {
			if(nextNode.isCastle()) {
				field.removeLive(strength);
				kill();
			} else {
				nextNode = nextNode.getNextToCastle();
			}
		}
	}
	
	private void effect() {
		double currentTime = ((double) System.nanoTime())/1000000000;
		for(Map.Entry<Effect, double[]> entry : effects.entrySet()) {
			if(entry.getValue() != null) {
				double datas[] = entry.getValue();
				switch(entry.getKey()) {
				case BLEED:
					if(currentTime > datas[2]) {
						datas[2] = currentTime + 1;
						dealDamage(datas[1]);
					}
					break;
				case CONFUSED:
					if(currentTime > datas[0]) {
						confuseAngle = null;
					} else if(currentTime > 0.3 + datas[1]) {
						confuseAngle = 2*Math.PI*random.nextDouble();
						datas[1] = currentTime;
					}
				case SLOWED:
					break;
				}
				if(currentTime > datas[0]) {
					effects.put(entry.getKey(), null);
				}
			}
		}
	}

	@Override
	public void move() {
		long diffNano = System.nanoTime() - getLastNano();
		refreshNano();
		double diffSecond = (double) diffNano/1000000000d;
		double angle = (confuseAngle != null) ? confuseAngle : getPosition().angle(nextNode.getPosition());
		Vector nextPosition = getPosition().byAngle(angle, getSpeed()*diffSecond);
		getAppareances().getCircle().setPosition(nextPosition);
	}
	
	public double travelTime() {
		double dist = getPosition().dist(nextNode.getPosition());
		dist += nextNode.getDistToCastle();
		double time = dist/getSpeed();
		return time;
	}


	public boolean isBoss() {
		return boss;
	}
	public void setBoss(boolean boss) {
		this.boss = boss;
	}
	
	@Override
	public double getAngle() {
		return getPosition().angle(nextNode.getPosition());
	}
	public PathNode getNextNode() {
		return nextNode;
	}
	public void setMonsterType(String monsterType) {
		this.monsterType = monsterType;
	}
	public double getSpeed() {
		double speed = baseSpeed;
		double[] slowData = effects.get(SLOWED);
		if(slowData != null) {
			speed *= slowData[1];
		}
		return speed;
	}
	public void setBaseSpeed(double baseSpeed) {
		if(baseSpeed < 0)  {
			throw new IllegalArgumentException("Speed must be over 9000!, hum over 0");
		}
		this.baseSpeed = baseSpeed;
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

//	@Override
//	public void move() {
//		long nano = getLastNano();
//		refreshNano();
//		double timeToWalk = System.nanoTime() - nano;
//		if(getPosition() != null) {
//			move(timeToWalk/1000000000d);
//		}
//	}
	
	public enum Effect {
		BLEED,
		CONFUSED,
		SLOWED;
	}

	@Override
	public boolean isSlow() {
		return getSpeed() < baseSpeed;
	}

	@Override
	public void heal(double lifePoint) {
		health += lifePoint;
		health = (health > maxHealth) ? maxHealth : health;
	}

	@Override
	public boolean isFlying() {
		return fly;
	}
	
	public void setHealth(double maxHealth) {
		health *= maxHealth/this.maxHealth;
		this.maxHealth = maxHealth;
	}
	
	public void setFly(boolean fly) {
		this.fly = fly;
	}
	
	public void setStrength(double strength) {
		this.strength = strength;
	}
}
