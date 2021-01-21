package mHUD.mGraphicEntity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import mHUD.geometric.Vector;

public class LifeBar extends MGraphicEntity {

	private Vector size;
	private double life;
	
	public LifeBar() {
		setPosition(0,0);
		setSize(0,0);
		life = 0;
	}
	public LifeBar(double x, double y, double sx, double sy, double l) {
		setSize(sx,sy);
		setPosition(x,y);
		life = l;
	}
	
	public void setLife(double l) {
		life = l;
	}
	public void setSize(Vector size) {
		this.size = size;
		reloadCanvas();
	}
	public void setSize(double x, double y) {
		setSize(new Vector(x,y));
	}
	public void setPosition(Vector position) {
		this.setPosition(position.x, position.y);
	}
	public void setPosition(double x, double y) {
		super.setPosition(x, y-1.5*size.y);
	}
	protected Vector getPosition() {
		return new Vector(super.getPosition().x-size.x/2,super.getPosition().y-size.y*2-10);
	}
	
	protected Image getImage() {
		imageEdit.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
		imageEdit.fillRect(0,0,(int)size.x,(int)size.y);
		imageEdit.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
		
		imageEdit.setColor(Color.white);
		imageEdit.fill(new Rectangle(0,0,(int)size.x, (int)size.y));
		
		imageEdit.setColor(Color.red);
		imageEdit.fill(new Rectangle(1,1,(int)size.x-2, (int)size.y-2));
		
		imageEdit.setColor(Color.green);
		imageEdit.fill(new Rectangle(1,1,(int)(size.x*life)-2, (int)size.y-2));
		return imageBuffer;
	}

	public boolean isIn(double x, double y) {
		return false;
	}

	@Override
	protected void reloadCanvas() {
		imageBuffer = new BufferedImage((int)size.x, (int)size.y, BufferedImage.TYPE_INT_ARGB);
		imageEdit = imageBuffer.createGraphics();
	}

}
