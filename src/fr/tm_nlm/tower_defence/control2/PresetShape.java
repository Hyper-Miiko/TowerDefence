package fr.tm_nlm.tower_defence.control2;

import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class PresetShape {
	public static Geometric circle(double radius) {
		Geometric shape = new Geometric(new Area(new Ellipse2D.Double(0, 0, radius, radius)));
		return shape;
	}
	public static Geometric rect(double width, double height) {
		Geometric shape = new Geometric(new Area(new Rectangle2D.Double(0, 0, width, height)));
		return shape;
	}
}
