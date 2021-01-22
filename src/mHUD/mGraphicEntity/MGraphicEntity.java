package mHUD.mGraphicEntity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import mHUD.geometric.Vector;

public abstract class MGraphicEntity {
	private Vector position;
	private double rotation = 0;
	
	protected Graphics2D imageEdit;
	protected BufferedImage imageBuffer;
	
	private boolean display = true;
	
	protected abstract Image getImage();

	protected Vector getPosition() {
		return position;
	}
	public void setPosition(Vector position) {
		this.position = position;
	}
	public void setPosition(double x, double y) {
		this.position = new Vector(x,y);
	}
	
	public abstract boolean isIn(double x, double y);
	
	public boolean isIn(Vector position) {
		return isIn(position.x, position.y);
	}
	
	
	public double getRotation() {
		return rotation;
	}

	public void rotate(double rotation) {
		this.rotation = rotation;
		reloadCanvas();
	}
	
	protected abstract void reloadCanvas();

	public boolean isDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}


}
