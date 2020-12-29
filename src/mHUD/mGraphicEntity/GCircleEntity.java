package mHUD.mGraphicEntity;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.image.BufferedImage;

import mHUD.geometric.Circle;
import mHUD.geometric.Rect;
import mHUD.geometric.Shape;
import mHUD.geometric.Vector;

public class GCircleEntity extends GPlainEntity {
	double oldRadius;
	
	public GCircleEntity(Circle c) {
		super(c);
		oldRadius = c.getRadius();
		imageBuffer = new BufferedImage((int)c.getRadius(), (int)c.getRadius(), BufferedImage.TYPE_INT_ARGB);
		imageEdit = imageBuffer.createGraphics();
	}
	
	protected Image getImage() {
		Circle c = (Circle)shape;
		if(c.getRadius() != oldRadius) {
			imageBuffer = new BufferedImage((int)c.getRadius(), (int)c.getRadius(), BufferedImage.TYPE_INT_ARGB);
			imageEdit = imageBuffer.createGraphics();
		}
		
		imageEdit.setColor(getBackgroundColor());
		imageEdit.fill(new Ellipse2D.Double(0,0,c.getRadius(), c.getRadius()));
		
		imageEdit.setColor(getLineColor());
		imageEdit.draw(new Ellipse2D.Double(0,0,c.getRadius(), c.getRadius()));
		
		return imageBuffer;
	}
	
}
