package mHUD.mGraphicEntity;

import java.awt.AlphaComposite;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import mHUD.geometric.Vector;

public class GRectEntity extends GPlainEntity {
	
	private Vector size = new Vector(0,0);
	private int hyp = 0;
	
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
		hyp = (int)Math.hypot(size.x, size.y);
		reloadCanvas();
	}
	public void setSize(double x, double y) {
		setSize(new Vector(x,y));
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
		
		imageEdit.setColor(getBackgroundColor());
		imageEdit.fill(new Rectangle((int)(hyp/2-size.x/2),(int)(hyp/2-size.y/2),(int)size.x-1, (int)size.y-1));
		
		imageEdit.setColor(getLineColor());
		imageEdit.draw(new Rectangle((int)(hyp/2-size.x/2),(int)(hyp/2-size.y/2),(int)size.x-1, (int)size.y-1));
		imageEdit.rotate(-getRotation(),hyp/2,hyp/2);
	}

	@Override
	public boolean isIn(double x, double y) {
		return x >= getPosition().x-getSize().x &&
			   x <  getPosition().x+getSize().x &&
			   y >= getPosition().y-getSize().y &&
			   y <  getPosition().y+getSize().y;
	}
}
