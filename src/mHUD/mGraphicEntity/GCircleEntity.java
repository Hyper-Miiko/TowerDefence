package mHUD.mGraphicEntity;

import java.awt.AlphaComposite;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import mHUD.geometric.Vector;


public class GCircleEntity extends GPlainEntity {
	private double diameter = 1;
	
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
		
		imageEdit.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
		imageEdit.fillRect(0,0,(int)diameter,(int)diameter);
		imageEdit.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
		
		imageEdit.setColor(getBackgroundColor());
		imageEdit.fill(new Ellipse2D.Double(0,0,diameter,diameter));
		
		imageEdit.setColor(getLineColor());
		imageEdit.draw(new Ellipse2D.Double(0,0,diameter,diameter));
	}
	protected Image getImage() {
		return imageBuffer;
	}
	@Override
	public boolean isIn(double x, double y) {
		return Math.abs(Math.sqrt(Math.pow(getPosition().x-x,2) + Math.pow(getPosition().y-y,2))) <= getDiameter();
	}

}
