package mHUD.mGraphicEntity;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import mHUD.geometric.Rect;
import mHUD.geometric.Shape;
import mHUD.geometric.Vector;

public class GRectEntity extends GPlainEntity {
	Vector oldSize;
	
	public GRectEntity(Rect r) {
		super(r);
		oldSize = r.getSize();
		imageBuffer = new BufferedImage((int)r.getSize().x, (int)r.getSize().y, BufferedImage.TYPE_INT_ARGB);
		imageEdit = imageBuffer.createGraphics();
	}	
		
	public void setPosition(double x, double y) {
		shape.setPosition(x,y);
	}
	public void setPosition(Vector v) {
		shape.setPosition(v);
	}
	
	protected Image getImage() {
		Rect r = (Rect)shape;
		if(!r.getSize().equals(oldSize)) {
			imageBuffer = new BufferedImage((int)r.getSize().x, (int)r.getSize().y, BufferedImage.TYPE_INT_ARGB);
			imageEdit = imageBuffer.createGraphics();
		}
		
		imageEdit.setColor(getBackgroundColor());
		imageEdit.fill(new Rectangle(0,0,(int)r.getSize().x-1, (int)r.getSize().y-1));
		
		imageEdit.setColor(getLineColor());
		imageEdit.draw(new Rectangle(0,0,(int)r.getSize().x-1, (int)r.getSize().y-1));
		
		return imageBuffer;
	}

}
