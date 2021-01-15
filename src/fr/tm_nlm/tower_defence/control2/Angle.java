package fr.tm_nlm.tower_defence.control2;

import java.awt.geom.Point2D;

public class Angle {
	private double angle;
	
	public Angle(double angle) {
		while(angle < 0) {
			angle += 2*Math.PI;
		}
		while(angle > 2*Math.PI) {
			angle -= 2*Math.PI;
		}
		this.angle = angle;
	}
	
	public double value() {
		return angle;
	}
	
	public static Angle angleBeetween(Point2D.Double A, Point2D.Double O, Point2D.Double B) {
		Angle angleA = angleOrtho(A, O);
		Angle angleB = angleOrtho(B, O);
		Angle angleBetween = diff(angleA, angleB);
		return angleBetween;
	}
	
	public static Angle diff(Angle A, Angle B) {
		Angle diff = new Angle(A.value() - B.value());
		if(diff.value() > Math.PI) {
			diff.angle -= Math.PI;
		}
		return diff;
	}
	
	public static Angle angleOrtho(Point2D.Double A, Point2D.Double O) {
		Point2D.Double cloneA = (Point2D.Double) A.clone();
		cloneA.x -= O.x;
		cloneA.y -= O.x;
		Angle angle = new Angle(Math.atan2(cloneA.y, cloneA.x));
		return angle;
	}
	
	public Point2D.Double project(Point2D.Double point, double dist) {
		Point2D.Double newPoint = new Point2D.Double(point.x + Math.cos(angle)*dist,
													 point.y + Math.sin(angle)*dist);
		return newPoint;
	}
}
