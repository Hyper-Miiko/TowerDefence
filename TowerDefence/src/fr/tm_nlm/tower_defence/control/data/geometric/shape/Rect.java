package fr.tm_nlm.tower_defence.control.data.geometric.shape;

import fr.tm_nlm.tower_defence.Constant;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.data.geometric.Shape;

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

	@Override
	public boolean collide(Shape shape) {
		boolean collide;
		if(shape instanceof Rect) {
			Rect A = this;
			Rect B = ((Rect) shape);
			if(A.getPosition().x > B.getPosition().x) {
				Rect dummy = A;
				A = B;
				B = dummy;
			}
			if(A.getPosition().y > B.getPosition().y) {
				double dummy = A.getPosition().y;
				A.setPosition(A.getPosition().x, B.getPosition().y);
				B.setPosition(B.getPosition().x, dummy);
			}
			double aPosX = A.getPosition().x;
			double aPosY = A.getPosition().y;
			double bPosX = B.getPosition().x;
			double bPosY = B.getPosition().y;
			double aPosCornerX = aPosX + A.getCorner(RIGHT, BOTTOM).x/2;
			double aPosCornerY = aPosY + A.getCorner(RIGHT, BOTTOM).y/2;
			double bPosCornerX = bPosX + B.getCorner(LEFT, TOP).x/2;
			double bPosCornerY = bPosY + B.getCorner(LEFT, TOP).y/2;
			collide = aPosCornerX >= bPosCornerX && aPosCornerY >= bPosCornerY;
		} else {
			collide = shape.collide(this);
		}
		return collide;
	}
}
