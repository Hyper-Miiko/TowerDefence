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
	private ArrayList<Monster> handle;
	private Game game;
	private Geometric shape;
	private LinkedList<Attack> attacks;
	private Monster seek;
	private Slot slot;
	private final String name;
	
	{
		attacks = new LinkedList<>();
		handle = new ArrayList<>();
		ko = false;
		shape = PresetShape.circle(30);
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getMaxHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getHealth() {
		// TODO Auto-generated method stub
		return 0;
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
	public boolean haveImage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Couple<Area, Color> getShape() {
		return shape.getShape();
	}

	@Override
	public String getImage() {
		// TODO Auto-generated method stub
		return null;
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
}
