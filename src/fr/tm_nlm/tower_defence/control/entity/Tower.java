package fr.tm_nlm.tower_defence.control.entity;

import java.util.HashMap;
import java.util.LinkedList;

import fr.tm_nlm.tower_defence.Couple;
import fr.tm_nlm.tower_defence.control.Entity;
import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.data.geometric.shape.Circle;

public class Tower extends Entity {
	private static final int radius = 32;
	private static HashMap<Field, HashMap<Tower, Boolean>> addables = new HashMap<>();
	
	public static HashMap<Tower, Boolean> getAddables(Field field) {
		return addables.get(field);
	}
	
	private boolean obstacle;
	private int evolveCost;
	private int range;
	private double cooldown;
	private double cost;
	private double dammage;
	private double determination; //multiplicateur du prix de résurrection
	private double health;
	private double maxHealth;
	private Bullet bullet;
	private String name;
	private Tower evolution;

	public Tower(Field field, String name) {
		super(field, null);
		obstacle = false;
		evolveCost = -1;
		range = 100;
		cooldown = 1d;
		cost = 5d;
		dammage = 2d;
		determination = 1.5d;
		maxHealth = 20d;
		health = maxHealth;
		this.name = name;
		evolution = null;
		
		HashMap<Tower, Boolean> addables = getAddables(field);
		if(addables == null) {
			addables = new HashMap<>();
			Tower.addables.put(field, addables);
		}
		addables.put(this, false);
	}
	
	public void process() {
		System.nanoTime();
		if(getLastSecond() + cooldown > (double) System.nanoTime()/1000000000d) {
			Monster target = seek();
			if(target != null) {
				refreshNano();
				double angle = getPosition().angle(target.getPosition());
				Bullet newBullet = bullet.add(getPosition(), angle);
				field.add(newBullet);
			}
		}
	}
	
	private Monster seek() {
		LinkedList<Monster> monsters = field.getMonsters();
		LinkedList<Couple<Monster, Double>> seekeds = new LinkedList<>();
		for(Monster monster : monsters) {
			double dist = getPosition().dist(monster.getPosition());
			if(monster.getAppareances().isRect()) {
				throw new InternalError("Pourquoi ta mis des monstre carré?");
			}
			if(dist < monster.getAppareances().getCircle().getRadius() + range) {
				Couple<Monster, Double> seeked = new Couple<>(monster, dist);
				seekeds.add(seeked);
			}
		}
		Couple<Monster, Double> nearest = new Couple<>(null, null);
		while(!seekeds.isEmpty()) {
			Couple<Monster, Double> seeked = seekeds.pop();
			if(nearest._1 == null || seeked._2 < nearest._2) {
				nearest = seeked;
			}
		}
		return nearest._1;
	}
	
	public void canEvolveIn(Tower evolution, int evolveCost) {
		if(evolution == null) {
			throw new IllegalArgumentException("L'évolution doit exister");
		}
		if(evolveCost < 0) {
			throw new IllegalArgumentException("Un prix est sensé être supérieur à 0 mais a reçu : " + evolveCost);
		}
		evolution.setCost(cost + evolveCost);
		this.evolution = evolution;
		this.evolveCost = evolveCost;
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
	
	public void place(Vector position) {
		getAppareances().setShape(new Circle(position, radius));
	}
	
	public void setCost(double cost) {
		if(cost < 0) {
			throw new IllegalArgumentException("Un prix est sensé être supérieur à 0 mais a reçu : " + cost);
		}
		this.cost = cost;
	}
	
	public void setRange(int range) {
		if(range < 0) {
			throw new IllegalArgumentException("Une portée est sensée être supérieure à 0 mais a reçu : " + range);
		}
		this.range = range;
	}
	
	public void setCooldown(double cooldown) {
		if(cooldown < 0) {
			throw new IllegalArgumentException("Un délai de récupération est sensé être supérieur à 0 mais a reçu : " + cooldown);
		}
		if(cooldown == 0) {
			System.err.println("Cooldown de 0 impossible, limité par les performances machine.");
		}
		this.cooldown = cooldown;
	}
	
	public void setDammage(double dammage) {
		if(dammage < 0) {
			throw new IllegalArgumentException("Soin par dégât impossible " + cooldown);
		}
		this.dammage = dammage;
	}
	
	public void setMaxHealth(double maxHealth) {
		if(maxHealth < this.maxHealth) { //On ne réduit pas la vie actuelle (sauf si elle dépasse)
			health = (health > maxHealth) ? maxHealth : health;
		} else { //On augmente la vie proportionnellement
			health /= this.maxHealth;
			health *= maxHealth;
		}
		this.maxHealth = maxHealth;
	}
	
	public void setObstacle(boolean obstacle) {
		this.obstacle = obstacle;
	}
	
	public boolean isObstacle() {
		return obstacle;
	}
	
	public final class presetTower {
		private presetTower() {}
		
		public Tower buildSans(Field field) {
			Tower sans = new Tower(field, "Sans");
			sans.setObstacle(true);
			sans.setCost(0);
			sans.setRange(10);
			sans.setCooldown(10d);
			sans.setDammage(1d);
			sans.setMaxHealth(1d);
			sans.canEvolveIn(buildInsaneSans(field), 10000);
			return sans;
		}
		private Tower buildInsaneSans(Field field) {
			Tower sans = new Tower(field, "Sans");
			sans.setObstacle(true);
			sans.setRange(150);
			sans.setCooldown(0.1d);
			sans.setDammage(1d);
			sans.setMaxHealth(1d);
			return sans;
		}
		
		public Tower buildMadDummy(Field field) {
			Tower madDummy = new Tower(field, "Mad Dummy");
			madDummy.setObstacle(false);
			madDummy.setCost(5);
			madDummy.setRange(100);
			madDummy.setCooldown(1d);
			madDummy.setDammage(Math.PI);
			madDummy.setMaxHealth(Integer.MAX_VALUE);
			madDummy.canEvolveIn(buildMadMewMew(field), 63);
			return madDummy;
		}
		private Tower buildMadMewMew(Field field) {
			Tower madMewMew = new Tower(field, "Mad Mew Mew");
			madMewMew.setObstacle(false);
			madMewMew.setRange(200);
			madMewMew.setCooldown(1.2d);
			madMewMew.setDammage(3*Math.PI);
			madMewMew.setMaxHealth(Integer.MAX_VALUE);
			return madMewMew;
		}
		
		public Tower buildMetaton(Field field) {
			Tower mettaton = new Tower(field, "Mettaton");
			mettaton.setObstacle(true);
			mettaton.setCost(20);
			mettaton.setRange(20);
			mettaton.setCooldown(1d);
			mettaton.setDammage(Math.PI);
			mettaton.setMaxHealth(Integer.MAX_VALUE);
			//mettaton.canEvolveIn(buildMettatonEX(field), 63);
			return mettaton;
		}
		/*private Tower buildMettatonEX(Field field) {
			
		}*/
	}
}
