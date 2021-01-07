package fr.tm_nlm.tower_defence.control.entity;

import java.util.HashMap;
import java.util.LinkedList;

import fr.tm_nlm.tower_defence.Couple;
import fr.tm_nlm.tower_defence.control.Entity;
import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.data.geometric.shape.Circle;
import fr.tm_nlm.tower_defence.control.entity.monster.Option;

public class Tower extends Entity implements Damageable {
	private static final int radius = 32;
	
	public static Tower dummy() {
		return new Tower();
	}
	
	private boolean obstacle;
	private int evolveCost;
	private int range;
	private double cost;
	private double determination; //multiplicateur du prix de résurrection
	private double health;
	private double maxHealth;
	private Attack attack;
	private String name;
	private Tower evolution;
	private LinkedList<Option> forbbidens;
	private LinkedList<Option> requires;

	public Tower(Field field, String name) {
		super(field, null, false);
		obstacle = false;
		evolveCost = -1;
		range = 100;
		cost = 5d;
		determination = 1.5d;
		maxHealth = 20d;
		health = maxHealth;
		this.name = name;
		evolution = null;
		forbbidens = new LinkedList<>();
		requires = new LinkedList<>();
	}
	
	private Tower() {
		super(null, null);
	}
	
	public void process() {
		Monster seek = seek();
		attack.checkForBullet(getPosition());
		if(seek != null) {
			attack.checkForShootAt(seek);
		}
	}

	@Override
	public void dealDamage(double damage) {
		health = (damage > health) ? 0 : health - damage;
		if(health == 0) {
			leave();
		}
	}
	
	public Tower evolve() {
		if(evolution == null) {
			throw new IllegalStateException("L'entité n'a pas d'évolution disponible.");
		}
		field.buy(evolveCost);
		evolution.place(getPosition());
		leave();
		field.add(evolution);
		field.remove(this);
		return evolution;
	}
	
	public void leave() {
		getAppareances().setShape(null);
		field.remove(this);
		check = false;
	}
	
	public void place(Vector position) {
		getAppareances().setShape(new Circle(position, radius));
		field.add(this);
		check = false;
	}
	
	private Monster seek() {
		LinkedList<Monster> monsters = field.getMonsters();
		LinkedList<Couple<Monster, Double>> seekeds = new LinkedList<>();
		for(Monster monster : monsters) {
			double dist = getPosition().dist(monster.getPosition());
			if(monster.getAppareances().isRect()) {
				throw new InternalError("Pourquoi ta mis des monstre carré?");
			}
			if(dist < monster.getAppareances().getCircle().getRadius() + range
			   && optionAreGood(monster)) {
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
	
	private boolean optionAreGood(Monster monster) {
		LinkedList<Option> options = monster.getOptions();
		for(Option forbbiden : forbbidens) {
			for(Option option : options) {
				if(forbbiden.getClass() == option.getClass()) {
					return false;
				}
			}
		}
		for(Option require : requires) {
			boolean found = false;
			for(Option option : options) {
				if(require.getClass() == option.getClass()) {
					found = true;
					break;
				}
			}
			if(!found) {
				return false;
			}
		}
		return true;
	}
	
	public void evolveIn(Tower evolution, int evolveCost) {
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
	
	public boolean canEvolve() {
		return evolution != null
			   && field.canBuy(evolveCost);
	}
	
	public void setAttack(Attack attack) {
		this.attack = attack;
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
	
	@Override
	public String toString() {
		String str = super.toString();
		str += ": " + name;
		if(isOnField()) {
			str += " en " + getPosition();
			str += " regarde ";
			Monster seek = seek();
			if(seek == null) {
				str += "personne.";
			} else {
				str += seek;
			}
		} else {
			str += " n'est pas sur le champ.";
		}
		return str;
	}
}
