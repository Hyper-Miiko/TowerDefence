package fr.tm_nlm.tower_defence.control2;

import java.awt.Color;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.util.LinkedList;

import fr.tm_nlm.tower_defence.Couple;

public class Monster implements Damageable, Displayable, Movable {
	private boolean boss;
	private boolean dead;
	private boolean fly;
	private Game game;
	private Map map;
	private double health;
	private double maxHealth;
	private int strength;
	/**
	 * _1 Ralentissement 0.0 à 1.0
	 * _2 Timer de fin en seconde
	 */
	private LinkedList<Couple<Double, Double>> slows;
	private double baseSpeed;
	private Geometric shape;
	private double lastMoveTimer;
	private PathNode objectif;
	
	{
		baseSpeed = 50;
		dead = false;
		fly = false;
		health = maxHealth = 10;
		shape = PresetShape.circle(20);
		slows = new LinkedList<>();
		strength = 1;
	}
	public void process() {
		if(!dead) {
			double currentTime = Game.time();
			double time = currentTime - lastMoveTimer;
			lastMoveTimer = currentTime;
			shape.setAngle(getPosition().angle(objectif.getPosition()));
			move(time, true);
		}
	}
	public void resetMove() {
		lastMoveTimer = Game.time();
	}

	@Override
	public void heal(double sustain) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void hurt(double damage) {
		health = (health < damage) ? 0 : health - damage;
		if(health == 0) {
			dead = true;
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
		return objectif.distToEnd(!fly)/getSpeed();
	}
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
		health = maxHealth;
	}
	public boolean fly() {
		return fly;
	}
	@Override
	public void slow(Couple<Double, Double> slow) {
		slows.add(slow);
	}
	public void setBoss(boolean boss) {
		this.boss = boss;
	}
	public void setFly(boolean fly) {
		this.fly = fly;
	}
	public void setImage(String image) {
		shape.setImage(image);
	}
}
