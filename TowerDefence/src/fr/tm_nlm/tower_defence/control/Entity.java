package fr.tm_nlm.tower_defence.control;

import java.util.ArrayList;

import fr.tm_nlm.tower_defence.control.data.Image;
import fr.tm_nlm.tower_defence.control.data.geometric.Position;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;

public class Entity {
	private Position position;
	private Shape shape;
	private ArrayList<Image> img;

	public Entity(Position position, Shape shape) {
		this.position = position;
		this.shape = shape;
		img = new ArrayList<>();
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public void setPosition(double x, double y) {
		
	}
}
