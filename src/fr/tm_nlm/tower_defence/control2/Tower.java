package fr.tm_nlm.tower_defence.control2;

import java.awt.Color;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.LinkedList;

import fr.tm_nlm.tower_defence.Couple;

public class Tower implements Damageable, Displayable {
	private boolean dead;
	private int maxHandle;
	private double lastShootTimer;
	private ArrayList<Monster> handle;
	private Geometric shape;
	private LinkedList<Attack> attacks;
	private final String name;
	
	{
		attacks = new LinkedList<>();
		handle = new ArrayList<>();
		dead = false;
		shape = PresetShape.circle(30);
	}
	public Tower(String name) {
		this.name = name;
	}
	
	public void process() {
		if(!dead) {
			for(Attack attack : attacks) {
				attack.process();
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

	@Override
	public boolean havePosition() {
		return shape.havePosition();
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
	public Couple<String, Area> getImage() {
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

	public void setPosition(Vector position) {
		shape.setPosition(position);
	}
}
