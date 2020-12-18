package fr.tm_nlm.tower_defence.control.data.geometric.shape;

import fr.tm_nlm.tower_defence.control.data.geometric.Position;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;

public class Rect extends Shape {
	private double width;
	private double height;

	public Rect(Position position, double width, double height) {
		super(position);
		setWidth(width);
		setHeight(height);
	}

	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		if(width <= 0) {
			throw new IllegalArgumentException("Width must be >0.");
		}
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		if(height <= 0) {
			throw new IllegalArgumentException("Height must be > 0.");
		}
		this.height = height;
	}
}
