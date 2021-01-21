package fr.tm_nlm.tower_defence.control2;

import java.awt.Color;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import fr.tm_nlm.tower_defence.Couple;

public class Tower extends Identifiable implements Damageable, Displayable {
	private boolean ko;
	private int maxHandle;
	private double evolutionPrice;
	private double health, maxHealth;
	private double towerPrice;
	private ArrayList<Monster> handle;
	private Game game;
	private Geometric shape;
	private LinkedList<Attack> attacks;
	private Slot slot;
	private final String name;
	private Tower evolution;
	
	{
		attacks = new LinkedList<>();
		evolution = null;
		evolutionPrice = 0;
		handle = new ArrayList<>();
		health = maxHealth = 50;
		ko = false;
		maxHandle = 3;
		shape = PresetShape.circle(128);
		towerPrice = 10;
	}
	public Tower(String name) {
		if(name == null) {
			throw new IllegalArgumentException("You monster! :(");
		}
		this.name = name;
	}
	
	public void process() {
		if(!ko) {
			if(havePosition()) {
				for(Attack attack : attacks) {
					HashSet<Localisable> monsters = new HashSet<>();
					monsters.addAll(game.readMonsters());
					attack.process(getPosition(), monsters);
				}
			}
		}
	}
	
	public boolean handle(Monster monster) {
		for(Monster monsterHandled : handle) {
			if(monsterHandled.equals(monster)) {
				return true;
			}
		}
		if(handle.size() < maxHandle) {
			handle.add(monster);
			return true;
		}
		return false;
	}
	
	public void resetCooldown() {
		for(Attack attack : attacks) {
			attack.resetCooldown();
		}
	}

	@Override
	public boolean havePosition() {
		return shape != null && shape.havePosition();
	}

	@Override
	public boolean isOnScreen() {
		return slot != null;
	}

	@Override
	public double getMaxHealth() {
		return maxHealth;
	}

	@Override
	public double getHealth() {
		return health;
	}

	@Override
	public void hurt(double damage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void heal(double sustain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector getPosition() {
		return shape.getPosition();
	}

	@Override
	public Couple<Area, Color> getShape() {
		return shape.getShape();
	}

	@Override
	public String getImage() {
		return shape.getImage();
	}
	public void setMaxHandle(int maxHandle) {
		this.maxHandle = maxHandle;
	}
	public void addAttack(Attack attack) {
		this.attacks.add(attack);
	}

	public String getName() {
		return name;
	}
	
	public void setSlot(Slot slot) {
		if(this.slot != null) {
			this.slot.removeTower();
		}
		for(Attack attack : attacks) {
			attack.resetCooldown();
		}
		this.slot = slot;
		this.slot.setTower(this);
		shape.setPosition(slot.getPosition());
	}
	
	@Override
	public String toString() {
		String str = name;
		if(havePosition()) {
			str += ": " + getPosition();
		}
		return str;
	}
	
	public void setGame(Game game) {
		this.game = game;
		for(Attack attack : attacks) {
			attack.setMap(game.getMap());
		}
	}
	
	public boolean isKO() {
		return ko;
	}
	
	public double getRange() {
		double range = 0;
		
		for(Attack attack : attacks) {
			if(attack.getRange() > range) {
				range = attack.getRange();
			}
		}
		
		return range;
	}
	
	public void setImage(String image) {
		shape.setImage(image);
	}

	public LinkedList<Double> getCooldowns() {
		LinkedList<Double> cooldowns = new LinkedList<>();
		for(Attack attack : attacks) {
			cooldowns.add(attack.getCooldown());
		}
		return cooldowns;
	}
	
	public void setEvolution(Tower evolution) {
		this.evolution = evolution;
	}
	
	public Tower getEvolution() {
		return evolution;
	}
	
	public double getPrice() {
		return towerPrice;
	}
	
	public double getEvolutionPrice() {
		return evolutionPrice;
	}

	@Override
	public Angle getAngle() {
		return null;
	}
}
