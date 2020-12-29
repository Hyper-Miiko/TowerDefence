package mHUD.geometric;

import fr.tm_nlm.tower_defence.Constant;
import static fr.tm_nlm.tower_defence.Constant.BOTTOM;
import static fr.tm_nlm.tower_defence.Constant.LEFT;
import static fr.tm_nlm.tower_defence.Constant.RIGHT;
import static fr.tm_nlm.tower_defence.Constant.TOP;

public class Rect extends Shape {
	Vector size;

	public Rect(Vector position, Vector size) {
		super(position);
		setSize(size);
	}

	public Rect(Vector position, double width, double height) {
		super(position);
		setSize(width, height);
	}

	public Rect(double x, double y, Vector size) {
		super(new Vector(x, y));
		setSize(size);
	}

	public Rect(double x, double y, double width, double height) {
		super(new Vector(x, y));
		setSize(width, height);
	}
	
	public boolean isIn(Vector point) {
		return point.x >= getCorner(LEFT, TOP).x && point.y >= getCorner(LEFT, TOP).y
												 && point.x <= getCorner(RIGHT, BOTTOM).x
												 && point.y <= getCorner(RIGHT, BOTTOM).y;
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