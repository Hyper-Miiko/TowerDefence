package fr.tm_nlm.tower_defence.control.data.geometric;

import fr.tm_nlm.tower_defence.control.data.geometric.shape.Circle;
import fr.tm_nlm.tower_defence.control.data.geometric.shape.Rect;

public abstract class Shape {
	private Vector position;
	
	public Shape(Vector position) {
		setPosition(position);
	}
	
	public abstract boolean collide(Shape shape);
	
	public Circle getCircle() {
		if(this instanceof Circle) {
			return (Circle) this;
		}
		throw new ClassCastException("Il ne s'agit pas d'un cerle.");
	}
	
	public Rect getRect() {
		if(this instanceof Rect) {
			return (Rect) this;
		}
		throw new ClassCastException("Il ne s'agit pas d'un rectangle.");
	}
	
	public void setPosition(Vector position) {
		this.position = position;
	}
	public void setPosition(double x, double y) {
		position = new Vector(x, y);
	}
	public Vector getPosition() {
		return position;
	}
}
