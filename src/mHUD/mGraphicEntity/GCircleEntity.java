package mHUD.mGraphicEntity;

import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import mHUD.geometric.Vector;


public class GCircleEntity extends GPlainEntity {
	private double radius;
	
	public GCircleEntity() {
		setPosition(0,0);
		setRadius(1);
	}
	public GCircleEntity(double x, double y, double r) {
		setPosition(x,y);
		setRadius(r);
	}
	
	protected double getRadius() {
		return radius;
	}
	public void setRadius(double r) {
		radius = r;
		reloadCanvas();
	}
	protected Vector getPosition() {
		return new Vector(super.getPosition().x-getRadius()/2,super.getPosition().y-getRadius()/2);
	}
	
	protected void reloadCanvas() {
		imageBuffer = new BufferedImage((int)radius, (int)radius, BufferedImage.TYPE_INT_ARGB);
		imageEdit = imageBuffer.createGraphics();
	}
	protected Image getImage() {
		imageEdit.setColor(getBackgroundColor());
		imageEdit.fill(new Ellipse2D.Double(0,0,radius,radius));
		
		imageEdit.setColor(getLineColor());
		imageEdit.draw(new Ellipse2D.Double(0,0,radius,radius));
		
		return imageBuffer;
	}

}
