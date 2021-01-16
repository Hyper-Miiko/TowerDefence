package fr.tm_nlm.tower_defence.control2;

import java.awt.Color;
import java.awt.geom.Area;

import fr.tm_nlm.tower_defence.Couple;

public class Bullet implements Displayable, Movable {

	@Override
	public boolean isOnScreen() {
		// TODO Auto-generated method stub
		return false;
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
	public Couple<String, Area> getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBaseSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Couple<Area, Color> getShape() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean move(double time, boolean collide) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setShape(Geometric circle) {
		// TODO Auto-generated method stub
		
	}

	public void setSpeed(double d) {
		// TODO Auto-generated method stub
		
	}

	public void setDamage(double pi) {
		// TODO Auto-generated method stub
		
	}

	public void setAimingFactor(int i) {
		// TODO Auto-generated method stub
		
	}

}
