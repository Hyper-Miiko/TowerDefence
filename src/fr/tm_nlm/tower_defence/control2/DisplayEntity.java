package fr.tm_nlm.tower_defence.control2;

import java.awt.Color;
import java.awt.geom.Area;
import java.awt.geom.Point2D.Double;

import fr.tm_nlm.tower_defence.Couple;
import fr.tm_nlm.tower_defence.NotYetImplementedException;

public class DisplayEntity implements Displayable {
	private final Displayable elem;
	
	public DisplayEntity(Displayable elem) {
		this.elem = elem;
	}

	public int getPriority() {
		throw new NotYetImplementedException("coming soon!");
		
	}
	public boolean isDamageable() {
		return elem instanceof Damageable;
	}
	public boolean isIdentifiable() {
		return elem instanceof Identifiable;
	}
	public boolean haveRange() {
		return elem instanceof Tower;
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
	public String getImage() {
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
	
	public long getId() {
		if(elem instanceof Identifiable) {
			return ((Identifiable) elem).getId();
		} else {
			throw new ClassCastException(elem.getClass().getSimpleName() + " is not an instance of Identifiable.");
		}
	}
	
	public double getRange() {
		if(elem instanceof Tower) {
			return ((Tower) elem).getRange();
		} else {
			throw new ClassCastException(elem + " have no range.");
		}
	}
	
	@Override
	public String toString() {
		return elem.toString();
	}
}
