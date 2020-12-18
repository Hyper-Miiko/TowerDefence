package fr.tm_nlm.tower_defence.control;

import java.util.ArrayList;

import fr.tm_nlm.tower_defence.control.data.Image;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;

public class Entity {
	
	private Field field;
	private Shape shape;
	private ArrayList<Image> img;

	public Entity(Field field, Shape shape) {
		this.field = field;
		this.shape = shape;
		img = new ArrayList<>();
	}
	
	public Vector getPosition() {
		return shape.getPosition();
	}

	public Shape getShape() {
		return shape;
	}
}
