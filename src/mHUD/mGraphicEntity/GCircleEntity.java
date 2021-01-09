package mHUD.mGraphicEntity;

import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import mHUD.geometric.Vector;


public class GCircleEntity extends GPlainEntity {
	private double diameter;
	
	public GCircleEntity() {
		setPosition(0,0);
		setRadius(1);
	}
	public GCircleEntity(double x, double y, double r) {
		setPosition(x,y);
		setRadius(r);
	}
	
	protected double getDiameter() {
		return diameter;
	}
	public void setRadius(double radius) {
		diameter = radius*2;
		reloadCanvas();
	}
	protected Vector getPosition() {
		return new Vector(super.getPosition().x-getDiameter()/2,super.getPosition().y-getDiameter()/2);
	}
	
	protected void reloadCanvas() {
		imageBuffer = new BufferedImage((int)diameter, (int)diameter, BufferedImage.TYPE_INT_ARGB);
		imageEdit = imageBuffer.createGraphics();
	}
	protected Image getImage() {
		imageEdit.setColor(getBackgroundColor());
		imageEdit.fill(new Ellipse2D.Double(0,0,diameter,diameter));
		
		imageEdit.setColor(getLineColor());
		imageEdit.draw(new Ellipse2D.Double(0,0,diameter,diameter));
		
		return imageBuffer;
	}
	@Override
	public boolean isIn(double x, double y) {
		return Math.abs(Math.sqrt(Math.pow(getPosition().x-x,2) + Math.pow(getPosition().y-y,2))) <= getDiameter();
	}

}
