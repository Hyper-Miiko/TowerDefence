package fr.tm_nlm.tower_defence.control2;

import java.awt.Color;
import java.awt.geom.Area;
import java.util.LinkedList;

import fr.tm_nlm.tower_defence.Couple;

public class DisplayEntity implements Displayable {
	private final Displayable elem;
	
	public DisplayEntity(Displayable elem) {
		this.elem = elem;
	}
	
	public Angle getAngle() {
		return elem.getAngle();
	}

	public int getPriority() {
		if(elem instanceof Slot) {
			return 0;
		} else if(elem instanceof Bullet) {
			return 1;
		} else if(elem instanceof Monster) {
			if(((Monster) elem).isFlying()) {
				return 4;
			} else {
				return 2;
			}
		} else {
			return 3;
		}
	}
	public boolean isSlot() {
		return elem instanceof Slot;
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
	public String getClassName() {
		return elem.getClass().getSimpleName();
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
			return ((Damageable) elem).getHealth() / ((Damageable) elem).getMaxHealth();
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
	
	public LinkedList<Double> getCooldowns() {
		if(elem instanceof Tower) {
			return ((Tower) elem).getCooldowns();
		} else {
			throw new ClassCastException(elem + " have no range.");
		}
	}
	
	@Override
	public String toString() {
		return elem.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof DisplayEntity) {
			obj = ((DisplayEntity) obj).elem;
		}
		return elem.equals(obj);
	}

	@Override
	public boolean isFlying() {
		return elem.isFlying();
	}
}
