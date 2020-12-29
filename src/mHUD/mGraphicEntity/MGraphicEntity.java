package mHUD.mGraphicEntity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import mHUD.geometric.Shape;
import mHUD.geometric.Vector;

public abstract class MGraphicEntity {
	protected final Shape shape;
	
	protected MGraphicEntity(Shape s) {
		shape =s;
	}
	
	protected Graphics2D imageEdit;
	protected BufferedImage imageBuffer;
	
	protected abstract Image getImage();
	
	protected Vector getPos() {
		return shape.getPosition();
	}
}
