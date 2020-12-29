package mHUD.geometric;

import static fr.tm_nlm.tower_defence.Constant.BOTTOM;
import static fr.tm_nlm.tower_defence.Constant.LEFT;
import static fr.tm_nlm.tower_defence.Constant.RIGHT;
import static fr.tm_nlm.tower_defence.Constant.TOP;

public class Circle extends Shape {
	private double radius;

	public Circle(Vector position, double radius) {
		super(position);
		setRadius(radius);
	}
	public Circle(double posX, double posY, double radius) {
		super(new Vector(posX, posY));
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

	@Override
	public boolean collide(Shape shape) {
		if(shape instanceof Circle) {
			double cumulRadius = radius + ((Circle) shape).radius;
			if(getPosition().dist(shape.getPosition()) <= cumulRadius) {
				return true;
			}
		} else {
			Rect rect = (Rect) shape;
			double angle = getPosition().angle(rect.getCorner(LEFT, TOP));
			Vector point = getPosition().byAngle(angle, radius);
			if(rect.isIn(point)) {
				return true;
			}
			angle = getPosition().angle(rect.getCorner(LEFT, BOTTOM));
			point = getPosition().byAngle(angle, radius);
			if(rect.isIn(point)) {
				return true;
			}
			angle = getPosition().angle(rect.getCorner(RIGHT, TOP));
			point = getPosition().byAngle(angle, radius);
			if(rect.isIn(point)) {
				return true;
			}
			angle = getPosition().angle(rect.getCorner(RIGHT, BOTTOM));
			point = getPosition().byAngle(angle, radius);
			if(rect.isIn(point)) {
				return true;
			}
		}
		return false;
	}
}