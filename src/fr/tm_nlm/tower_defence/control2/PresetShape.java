package fr.tm_nlm.tower_defence.control2;

import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class PresetShape {
	public static Geometric usualMonsterShape() {
		Geometric shape = new Geometric(new Area(new Ellipse2D.Double(0, 0, 20, 20)));
		return shape;
	}
}
