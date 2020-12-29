package mHUD.mGraphicEntity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import mHUD.geometric.Vector;

public abstract class MGraphicEntity {
	private Vector position;
	
	protected Graphics2D imageEdit;
	protected BufferedImage imageBuffer;
	
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
	
	protected abstract void reloadCanvas();
}
