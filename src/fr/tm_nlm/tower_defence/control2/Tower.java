package fr.tm_nlm.tower_defence.control2;

import java.awt.Color;
import java.awt.geom.Area;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

import fr.tm_nlm.tower_defence.Couple;

public class Tower implements Damageable, Displayable {
	private ArrayList<Monster> handle;
	private int maxHandle;
	
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
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean haveImage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Couple<Area, Color> getShape() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Couple<String, Area> getImage() {
		// TODO Auto-generated method stub
		return null;
	}

}
