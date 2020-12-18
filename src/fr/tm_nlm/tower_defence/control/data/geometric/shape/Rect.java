package fr.tm_nlm.tower_defence.control.data.geometric.shape;

import static fr.tm_nlm.tower_defence.Constant.BOTTOM;
import static fr.tm_nlm.tower_defence.Constant.RIGHT;

import fr.tm_nlm.tower_defence.Constant;
import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.Entity;
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

	@Override
	public boolean collide(Shape shape) {
		boolean collide;
		if(shape instanceof Rect) {
			double posX = getPosition().x;
			double posY = getPosition().y;
			double shapePosX = ((Rect) shape).getPosition().x;
			double shapePosY = ((Rect) shape).getPosition().y;
			int compare = (posX <= shapePosX) ? 0 : 1;
			compare += (posY <= shapePosY) ? 0 : 2;
			if(compare == 0) {
				double posCornerX = posX + getCorner(RIGHT, BOTTOM).x/2;
				double posCornerY = posY + getCorner(RIGHT, BOTTOM).y/2;
				double shapePosCornerX = shapePosX + ((Rect) shape).getCorner(RIGHT, BOTTOM).x/2;
				double shapePosCornerY = shapePosY + getCorner(RIGHT, BOTTOM).y/2;
//				if() {
//					
//				}
			} else {
				shape.collide(this);
			}
		}
		return /*collide*/false;
	}
}
