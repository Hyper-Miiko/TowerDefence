package fr.tm_nlm.tower_defence.control2;

import java.awt.Color;
import java.awt.geom.Area;

import fr.tm_nlm.tower_defence.Couple;

public interface Localisable {
	public Vector getPosition();
	public default boolean havePosition() {
		return getPosition() != null;
	}
	public default boolean collide(Localisable elem) {
		Area area = (Area) getShape()._1.clone();
		area.intersect(elem.getShape()._1);
		return !area.isEmpty();
	}
	public Couple<Area, Color> getShape();
	public Angle getAngle();
	public boolean isFlying();
}
