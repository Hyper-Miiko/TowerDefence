package fr.tm_nlm.tower_defence.control.data;

import java.awt.Color;

import fr.tm_nlm.tower_defence.control.data.geometric.Shape;
import fr.tm_nlm.tower_defence.control.data.geometric.shape.Circle;
import fr.tm_nlm.tower_defence.control.data.geometric.shape.Rect;

public class Appareances {
	private Shape shape;
	private Color color;
	
	public Appareances(Shape shape) {
		this.shape = shape;
		color = new Color(255, 255, 255);
	}
	
	public Shape getShape() {
		return shape;
	}
	public void setShape(Shape shape ) {
		this.shape = shape;
	}
	
	public boolean isCircle() {
		return shape.isCircle();
	}
	
	public boolean isRect() {
		return shape.isRect();
	}
	
	public Circle getCircle() {
		return shape.getCircle();
	}
	
	public Rect getRect() {
		return shape.getRect();
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(int r, int g, int b) {
		color = new Color(r, g, b);
	}
}
