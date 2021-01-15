package fr.tm_nlm.tower_defence.control2;

import java.awt.Color;
import java.awt.geom.Area;
import java.awt.geom.Point2D.Double;

import fr.tm_nlm.tower_defence.Couple;

public class DisplayEntity implements Displayable {
	private final Displayable elem;
	
	public DisplayEntity(Displayable elem) {
		this.elem = elem;
	}

	@Override
	public boolean isOnScreen() {
		return elem.isOnScreen();
	}
	@Override
	public Vector getPosition() {
		return elem.getPosition();
	}
	@Override
	public boolean haveImage() {
		return elem.haveImage();
	}
	@Override
	public Couple<String, Area> getImage() {
		return elem.getImage();
	}
	@Override
	public Couple<Area, Color> getShape() {
		return elem.getShape();
	}

	public double getHealth() {
		if(!(elem instanceof Damageable)) {
			throw new ClassCastException(elem + " have no health.");
		} else {
			return ((Damageable) elem).getHealth();
		}
	}

	public double getMaxHealth() {
		if(!(elem instanceof Damageable)) {
			throw new ClassCastException(elem + " have no health.");
		} else {
			return ((Damageable) elem).getMaxHealth();
		}
	}
}
