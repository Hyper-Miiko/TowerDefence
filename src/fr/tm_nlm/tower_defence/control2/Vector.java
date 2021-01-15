package fr.tm_nlm.tower_defence.control2;

import java.awt.geom.Point2D;

public class Vector extends Point2D.Double {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final double x, y;
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector byAngle(Angle angle, double dist) {
		double x = this.x + Math.cos(angle.value())*dist;
		double y = this.y + Math.sin(angle.value())*dist;
		return new Vector(x, y);
	}
	
	public double dist(Vector vector) {
		return Math.sqrt(Math.pow(x - vector.x, 2) + Math.pow(y - vector.y, 2));
	}
	
	public Angle angle(Vector vector) {
		return new Angle(Math.atan2(vector.y - y, vector.x - x));
	}
	
	@Override
	public String toString() {
		return "(" + Integer.toString((int) x) + ", " + Integer.toString((int) y) + ")";
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof Vector) {
			return x == ((Vector) object).x &&
				   y == ((Vector) object).y;
		}
		return false;
	}
}
