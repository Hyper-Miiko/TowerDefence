package fr.tm_nlm.tower_defence.control.data.geometric.shape;

import fr.tm_nlm.tower_defence.control.data.geometric.Position;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;

public class Circle extends Shape {
	private double radius;

	public Circle(Position position, double radius) {
		super(position);
		setRadius(radius);
	}

	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		if(radius <= 0) {
			throw new IllegalArgumentException("The radius must be > 0.");
		}
		this.radius = radius;
	}
}
