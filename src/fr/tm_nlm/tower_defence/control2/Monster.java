package fr.tm_nlm.tower_defence.control2;

import java.awt.Color;
import java.awt.geom.Area;
import java.util.LinkedList;
import java.util.Random;

import fr.tm_nlm.tower_defence.Couple;

public class Monster implements Damageable, Displayable, Movable, Cloneable {
	private static Random random = new Random();
	private boolean boss;
	private boolean dead;
	private boolean flying;
	private int strength;
	private double lastConfuseTimer;
	private double lastMoveTimer;
	private double confuse;
	private double health;
	private double maxHealth;
	private double baseSpeed;
	private int eliminationWorth;
	/**
	 * _1 Ralentissement 0.0 à 1.0
	 * _2 Timer de fin en seconde
	 */
	private LinkedList<Couple<Double, Double>> slows;
	private Geometric shape;
	private PathNode objectif;
	private Game game;
	private Map map;
	
	{
		baseSpeed = 50;
		confuse = 0;
		dead = false;
		flying = false;
		health = maxHealth = 10;
		shape = PresetShape.circle(20);
		slows = new LinkedList<>();
		strength = 1;
		eliminationWorth = 2;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object clone() {
		Monster monster = new Monster();
		
		monster.boss = boss;
		monster.flying = flying;
		monster.dead = dead;
		monster.strength = strength;
		monster.lastConfuseTimer = lastConfuseTimer;
		monster.lastMoveTimer = lastMoveTimer;
		monster.confuse = confuse;
		monster.health = health;
		monster.maxHealth = maxHealth;
		monster.baseSpeed = baseSpeed;
		monster.eliminationWorth = eliminationWorth;
		monster.slows = (LinkedList<Couple<Double, Double>>) slows.clone();
		monster.shape = (Geometric) shape.clone();
		monster.objectif = objectif;
		monster.game = game;
		monster.map = map;
		
		return monster;
	}
	public void process() {
		if(!dead) {
			double currentTime = Game.time();
			double time = currentTime - lastMoveTimer;
			lastMoveTimer = currentTime;
			if(confuse == 0 || isBoss()) {
				shape.setAngle(getPosition().angle(objectif.getPosition()));
			} else {
				System.out.println();
				if(lastConfuseTimer + 0.5 < currentTime) {
					lastConfuseTimer = currentTime;
					shape.setAngle(new Angle(valueBetween(0, 2*Math.PI)));
				}
			}
			LinkedList<Couple<Double, Double>> newSlows = new LinkedList<>();
			for(Couple<Double, Double> slow : slows) {
				Couple<Double, Double> newSlow = new Couple<>(slow._1, slow._2 - time);
				if(newSlow._2 > 0) {
					newSlows.add(newSlow);
				}
			}
			confuse = (confuse - time > 0) ? confuse - time : 0;
			slows = newSlows;
			move(time, true);
		}
	}
	public void resetMove() {
		lastConfuseTimer = lastMoveTimer = Game.time();
	}
	
	private double valueBetween(double min, double max) {
		return (max > min) ? (max - min)*random.nextDouble() + min : min;
	}

	@Override
	public void heal(double sustain) {
	}
	@Override
	public void hurt(double damage) {
		hurt(damage, true);
	}
	@Override
	public void hurt(double damage, boolean lethal) {
		double minHealth = (lethal) ? 0 : 0.01;
		if(!dead) {
			health -= damage;
			health = (health < minHealth) ? minHealth : health;
			if(health == 0) {
				dead = true;
				game.increaseTemmies(eliminationWorth);
			}
		}
	}
	@Override
	public boolean move(double time, boolean checkCollide) {
		Geometric nextShape = (Geometric) shape.clone();
		nextShape.translateByAngle(getSpeed()*time);
		boolean collide = false;
		if(checkCollide ) {
			for(Tower tower : game.readTowers()) {
				if(nextShape.collide(tower)) {
					System.out.println("hey");
					if(tower.handle(this)) {
						collide = true;
						break;
					}
				}
			}
		}
		if(!collide) {
			shape = nextShape;
			if(getPosition().dist(objectif.getPosition()) < 20) {
				if(objectif.isEnd()) {
					dead = true;
					map.removeLives(strength);
				} else {
					objectif = objectif.next(true);
				}
			}
		}
		return !collide;
	}

	@Override
	public double getHealth() {
		return health;
	}
	@Override
	public double getMaxHealth() {
		return maxHealth;
	}
	@Override
	public boolean isOnScreen() {
		return havePosition();
	}
	@Override
	public Vector getPosition() {
		return shape.getPosition();
	}
	public void setSpeed(double speed) {
		this.baseSpeed = speed;
	}
	@Override
	public double getSpeed() {
		double speed = getBaseSpeed();
		for(Couple<Double, Double> slow : slows) {
			speed *= 1 - slow._1;
		}
		if(isBoss() && speed < getBaseSpeed()/2) {
			speed = getBaseSpeed()/2;
		}
		return speed;
	}
	@Override
	public double getBaseSpeed() {
		return baseSpeed;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	@Override
	public Couple<Area, Color> getShape() {
		return shape.getShape();
	}
	@Override
	public String getImage() {
		return shape.getImage();
	}
	public void setPosition(Vector position) {
		shape.setPosition(position);
	}
	public void setPath(PathNode pathNode) {
		this.objectif = pathNode;
	}
	public Angle getAngle() {
		return shape.getAngle();
	}
	public PathNode getPath() {
		return objectif;
	}
	public void setShape(Geometric shape) {
		this.shape = shape;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public boolean isDead() {
		return dead;
	}
	public double timeToEnd() {
		return objectif.distToEnd(!flying)/getSpeed();
	}
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
		health = maxHealth;
	}
	public boolean isFlying() {
		return flying;
	}
	@Override
	public void slow(Couple<Double, Double> slow) {
		slows.add(slow);
	}
	public void setBoss(boolean boss) {
		this.boss = boss;
	}
	public void setFly(boolean fly) {
		this.flying = fly;
	}
	public void setImage(String image) {
		shape.setImage(image);
	}
	
	public boolean isBoss() {
		return boss;
	}
	@Override
	public void confuse(double confuse) {
		if(confuse > this.confuse) {
			this.confuse = confuse;
		}
	}
	public void setEliminationWorth(int eliminationWorth) {
		this.eliminationWorth = eliminationWorth;
	}
}
