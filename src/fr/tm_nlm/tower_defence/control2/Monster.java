package fr.tm_nlm.tower_defence.control2;

import java.awt.Color;
import java.awt.geom.Area;
import java.util.LinkedList;
import java.util.Random;

import fr.tm_nlm.tower_defence.Couple;

/**
 * Entité qui suivent un chemin, si il arrive au bout le joeur perd de la vie
 * @author Hyper Mïko
 *
 */
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
	private double eliminationWorth;
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
	/**
	 * Appel principal
	 */
	public void process() {
		if(!dead) { //Un monstre mort et un monstre sans vie, ou l'inverse?
			double currentTime = Game.time();
			double time = currentTime - lastMoveTimer;
			lastMoveTimer = currentTime;
			if(confuse == 0 || isBoss()) {//Un boss ne peut pas être confu
				shape.setAngle(getPosition().angle(objectif.getPosition()));
			} else {
				if(lastConfuseTimer + 0.5 < currentTime) { //Un monstre confu choisi un angle aléatoire tour les demi seconde
					lastConfuseTimer = currentTime;
					shape.setAngle(new Angle(valueBetween(0, 2*Math.PI)));
				}
			}
			LinkedList<Couple<Double, Double>> newSlows = new LinkedList<>(); //Réduction des temps de ralentissement
			for(Couple<Double, Double> slow : slows) {
				Couple<Double, Double> newSlow = new Couple<>(slow._1, slow._2 - time);
				if(newSlow._2 > 0) {
					newSlows.add(newSlow);
				}
			}
			confuse = (confuse - time > 0) ? confuse - time : 0; //Réduction du temps de confusion;
			slows = newSlows;
			move(time, !flying); //Ce déplace en ingorant les collision avec les tour si il vole
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
	/**
	 * Déplacement des monstre en fonction du temps
	 * @param time
	 * @param collide
	 * @return
	 */
	@Override
	public boolean move(double time, boolean checkCollide) {
		Geometric nextShape = (Geometric) shape.clone();
		nextShape.translateByAngle(getSpeed()*time); //Prochain déplacement si il n'y a pas de collision;
		boolean collide = false;
		if(checkCollide) {
			for(Tower tower : game.readTowers()) {//On cherche si une tour nous bar le chemin
				if(nextShape.collide(tower)) {
					if(tower.handle(this)) {
						collide = true;
						break;
					}
				}
			}
		}
		if(!collide) {
			shape = nextShape; //On valide le déplacement
			if(getPosition().dist(objectif.getPosition()) < 20) {//Si on a atteint le prochain point de passage
				if(objectif.isEnd()) {//Si on a atteint la fin
					dead = true;
					map.removeLives(strength);
				} else {
					objectif = objectif.next(true); //Sinon en continu
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
	/**
	 * Récupère la vitesse actuelle en prenant en compte les ralentissement
	 * @return vitesse actuelle
	 */
	@Override
	public double getSpeed() {
		double speed = getBaseSpeed();
		for(Couple<Double, Double> slow : slows) {
			speed *= 1 - slow._1;
		}
		if(isBoss() && speed < getBaseSpeed()/2) { //Les boss ne peuvent pas être trop ralentie
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
	public void setEliminationWorth(double eliminationWorth) {
		this.eliminationWorth = eliminationWorth;
	}
	public void setColor(Color color) {
		shape.setColor(color);
	}
}
