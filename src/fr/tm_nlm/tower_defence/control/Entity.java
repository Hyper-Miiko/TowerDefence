package fr.tm_nlm.tower_defence.control;

import java.util.ArrayList;

import fr.tm_nlm.tower_defence.control.data.Image;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;

public class Entity {
	
	private Vector position;
	private Shape shape;
	private ArrayList<Image> img;

	public Entity(Vector position, Shape shape) {
		this.position = position;
		this.shape = shape;
		img = new ArrayList<>();
	}

	public void setPosition(Vector position) {
		this.position = position;
	}
	
	public void setPosition(double x, double y) {
		
	}
}
