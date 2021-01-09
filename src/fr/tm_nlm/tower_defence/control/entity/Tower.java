package fr.tm_nlm.tower_defence.control.entity;

import java.util.LinkedList;

import fr.tm_nlm.tower_defence.Couple;
import fr.tm_nlm.tower_defence.control.Field;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.data.geometric.shape.Circle;
import fr.tm_nlm.tower_defence.control.entity.Monster.Effect;

public class Tower extends Entity implements Damageable {
	private static final int radius = 16;
	
	public static Tower dummy() {
		return new Tower();
	}
	
	private boolean obstacle;
	private int evolveCost;
	private double cost;
	private double health;
	private double maxHealth;
	private LinkedList<Attack> attacks;
	private Tower evolution;

	public Tower(Field field, String name) {
		super(field, null);
		obstacle = false;
		evolveCost = -1;
		cost = 5d;
		maxHealth = 20d;
		health = maxHealth;
		super.setName(name);
		evolution = null;
		attacks = new LinkedList<>();
	}
	
	private Tower() {
		super(null, null);
		kill();
	}
	
	public void process() {
		for(Attack attack : attacks) {
			Monster seek = seek(attack);
			attack.checkForBullet(getPosition());
			if(seek != null) {
				attack.checkForShootAt(seek);
			}
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
	
	private Monster seek(Attack attack) {
		LinkedList<Monster> monsters = field.getMonsters();
		LinkedList<Couple<Monster, Double>> seekeds = new LinkedList<>();
		for(Monster monster : monsters) {
			double dist = getPosition().dist(monster.getPosition());
			if(monster.getAppareances().isRect()) {
				throw new InternalError("Pourquoi ta mis des monstre carré?");
			}
			if(dist < monster.getAppareances().getCircle().getRadius() + attack.getRange() && attack.isValidTarget(monster)) {
				Couple<Monster, Double> seeked = new Couple<>(monster, monster.travelTime());
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
	
	public void addAttack(Attack attack) {
		attack.setOwner(this);
		attacks.add(attack);
	}
	
	public void setCost(double cost) {
		if(cost < 0) {
			throw new IllegalArgumentException("Un prix est sensé être supérieur à 0 mais a reçu : " + cost);
		}
		this.cost = cost;
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
		str += ": " + getName();
		if(isOnField()) {
			str += " en " + getPosition();
		} else {
			str += " n'est pas sur le champ.";
		}
		return str;
	}

	@Override
	public void affectWith(Effect effect, double... ds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void heal(double lifePoint) {
		health += lifePoint;
		health = (health > maxHealth) ? maxHealth : health;
	}

	@Override
	public boolean isBoss() {
		return false;
	}
}
