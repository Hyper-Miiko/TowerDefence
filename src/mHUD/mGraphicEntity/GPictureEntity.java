package mHUD.mGraphicEntity;

import java.awt.AlphaComposite;
import java.awt.Image;
import java.awt.image.BufferedImage;

import mHUD.geometric.Vector;
import mHUD.StdDraw;

public class GPictureEntity extends MGraphicEntity{
	
	protected Vector size = new Vector(1,1);
	protected int hyp = 1;
	protected Image image = null;
	
	public GPictureEntity() {
		setPosition(0,0);
	}
	public GPictureEntity(double x, double y, String imageName) {
		setPosition(x,y);
		setPicture(imageName);
	}
	
	protected Vector getSize() {
		return size;
	}

	public void setPicture(String imageName) {
		this.image = StdDraw.getImage(imageName);
		size = new Vector(image.getWidth(null),image.getHeight(null));
		hyp = (int)Math.hypot(size.x, size.y);
		reloadCanvas();
	}
	
	protected Vector getPosition() {
		return new Vector(super.getPosition().x-hyp/2,super.getPosition().y-hyp/2);
	}
	
	protected Image getImage() {
		return imageBuffer;
	}
	protected void reloadCanvas() {
		imageBuffer = new BufferedImage(hyp,hyp, BufferedImage.TYPE_INT_ARGB);
		imageEdit = imageBuffer.createGraphics();
		
		imageEdit.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
		imageEdit.fillRect(0,0,hyp,hyp);
		imageEdit.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
		
		imageEdit.rotate(getRotation(),hyp/2,hyp/2);
		
		imageEdit.drawImage(image,(int)(hyp/2-size.x/2),(int)(hyp/2-size.y/2), null);
		
		imageEdit.rotate(-getRotation(),hyp/2,hyp/2);
	}
	public boolean isIn(double x, double y) {
		return x >= getPosition().x-getSize().x &&
			   x <  getPosition().x+getSize().x &&
			   y >= getPosition().y-getSize().y &&
			   y <  getPosition().y+getSize().y;
	}

}
