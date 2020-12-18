package fr.tm_nlm.tower_defence.control.data.geometric.shape;

import fr.tm_nlm.tower_defence.Constant;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;

public class Rect extends Shape {
	Vector size;

	public Rect(Vector position, double width, double height) {
		super(position);
		setSize(width, height);
	}

	public Vector getCorner(Constant absciss, Constant ordonate) {
		double x;
		double y;
		switch(absciss) {
		case LEFT:
			x = getPosition().x - size.x/2;
			break;
		case RIGHT:
			x = getPosition().x + size.x/2;
			break;
		default:
			throw new IllegalArgumentException("Absciss must be LEFT or RIGHT : getCorner(absciss, ordonate)");
		}
		switch(ordonate) {
		case BOTTOM:
			y = getPosition().y - size.y/2;
			break;
		case TOP:
			y = getPosition().y + size.y/2;
			break;
		default:
			throw new IllegalArgumentException("Ordonate must be BOTTOM or TOP : getCorner(absciss, ordonate)");
		}
		return new Vector(x, y);
	}
	
	public Vector getSize() {
		return size;
	}
	public void setSize(double width, double height) {
		if(width <= 0) {
			throw new IllegalArgumentException("Widt must be > 0.");
		}
		if(height <= 0) {
			throw new IllegalArgumentException("Height must be > 0.");
		}
		size = new Vector(width, height);
	}
	public void setSize(Vector size) {
		if(size.x <= 0) {
			throw new IllegalArgumentException("Widt must be > 0.");
		}
		if(size.y <= 0) {
			throw new IllegalArgumentException("Height must be > 0.");
		}
		this.size = size;
	}
}
