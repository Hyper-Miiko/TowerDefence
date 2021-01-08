package mHUD.mGraphicEntity;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import mHUD.geometric.Vector;

public class GRectEntity extends GPlainEntity {
	
	private Vector size = new Vector(0,0);
	
	public GRectEntity() {
		setPosition(0,0);
		setSize(1,1);
	}
	
	public GRectEntity(double x, double y, double sx, double sy) {
		setPosition(x,y);
		setSize(sx,sy);
	}
	
	protected Vector getSize() {
		return size;
	}
	public void setSize(Vector size) {
		this.size = size;
		reloadCanvas();
	}
	public void setSize(double x, double y) {
		setSize(new Vector(x,y));
	}
	protected Vector getPosition() {
		return new Vector(super.getPosition().x-getSize().x/2,super.getPosition().y-getSize().y/2);
	}
	
	protected Image getImage() {
		imageEdit.clearRect(0,0, (int)size.x*2, (int)size.y*2);
		imageEdit.setColor(new Color(255,255,0,20));
		imageEdit.fill(new Rectangle(0,0,(int)size.x*2, (int)size.y*2));
		
		imageEdit.rotate(getRotation());
		imageEdit.setColor(getBackgroundColor());
		imageEdit.fill(new Rectangle(0,0,(int)size.x-1, (int)size.y-1));
		
		imageEdit.setColor(getLineColor());
		imageEdit.draw(new Rectangle(0,0,(int)size.x-1, (int)size.y-1));
		imageEdit.rotate(-getRotation());
		return imageBuffer;
	}
	protected void reloadCanvas() {
		int s = Math.max((int)size.x*2,  (int)size.y*2);
		
		imageBuffer = new BufferedImage(s,s, BufferedImage.TYPE_INT_ARGB);
		imageEdit = imageBuffer.createGraphics();
	}

	@Override
	public boolean isIn(double x, double y) {
		return x >= getPosition().x-getSize().x &&
			   x <  getPosition().x+getSize().x &&
			   y >= getPosition().y-getSize().y &&
			   y <  getPosition().y+getSize().y;
	}
}
