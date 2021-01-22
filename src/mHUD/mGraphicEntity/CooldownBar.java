package mHUD.mGraphicEntity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import mHUD.geometric.Vector;

public class CooldownBar extends MGraphicEntity {

	private Vector size;
	private double cooldown;
	private double decalage;
	
	public CooldownBar(double x, double y,double d, double sx, double sy, double l) {
		decalage = d;
		setSize(sx,sy);
		setPosition(x,y);
		cooldown = l;
	}
	
	public void setCooldown(double l) {
		cooldown = l;
		reloadCanvas();
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
		return new Vector(super.getPosition().x-size.x/2,super.getPosition().y-size.y/2 - decalage);
	}
	
	protected Image getImage() {
		return imageBuffer;
	}

	public boolean isIn(double x, double y) {
		return false;
	}

	@Override
	protected void reloadCanvas() {
		imageBuffer = new BufferedImage((int)size.x, (int)size.y, BufferedImage.TYPE_INT_ARGB);
		imageEdit = imageBuffer.createGraphics();
		
		imageEdit.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
		imageEdit.fillRect(0,0,(int)size.x,(int)size.y);
		imageEdit.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
		
//		imageEdit.setColor(Color.white);
//		imageEdit.fill(new Rectangle(0,0,(int)size.x, (int)size.y));
		
		imageEdit.setColor(Color.black);
		imageEdit.fill(new Rectangle(0,0,(int)size.x, (int)size.y));
		
		imageEdit.setColor(new Color(255,255,255));
		imageEdit.fill(new Rectangle(0,0,(int)(size.x*cooldown), (int)size.y));
	}

}
