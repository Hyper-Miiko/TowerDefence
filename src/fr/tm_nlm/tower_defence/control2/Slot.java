package fr.tm_nlm.tower_defence.control2;

import java.awt.Color;
import java.awt.geom.Area;

import fr.tm_nlm.tower_defence.Couple;

public class Slot implements Displayable {
	private static final Color GREEN = new Color(0, 255, 0, 127);
	private static final Color RED = new Color(255, 0, 0, 127);
	private Geometric shape;
	private Tower tower;
	
	public Slot(Vector position) {
		shape = PresetShape.circle(25);
		shape.setPosition(position);
	}

	@Override
	public Vector getPosition() {
		return shape.getPosition();
	}

	@Override
	public boolean isOnScreen() {
		return true;
	}

	@Override
	public boolean haveImage() {
		return false;
	}

	@Override
	public Couple<Area, Color> getShape() {
		return shape.getShape();
	}

	@Override
	public String getImage() {
		return null;
	}
	public boolean canPlaceTower() {
		return tower == null;
	}
	public void removeTower() {
		tower = null;
	}
	public void setTower(Tower tower) {
		this.tower = tower;
	}
	public Tower getTower() {
		return tower;
	}

}
