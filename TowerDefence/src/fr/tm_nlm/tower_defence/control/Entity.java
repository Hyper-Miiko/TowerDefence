package fr.tm_nlm.tower_defence.control;

import fr.tm_nlm.tower_defence.control.data.Image;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;

public class Entity {
	private Field field;
	private Image img;
	private Shape shape;

	public Entity(Field field, Shape shape) {
		this.field = field;
		img = null;
		this.shape = shape;
	}

	public Shape getShape() {
		return shape;
	}
	
	public Vector getPosition() {
		return shape.getPosition();
	}
}
