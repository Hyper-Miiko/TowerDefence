package mHUD.mGraphicEntity;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import mHUD.geometric.Vector;
import mHUD.StdDraw;

public class GPictureEntity extends MGraphicEntity{
	
	private Vector size = new Vector(0,0);
	Image image = null;
	
	public GPictureEntity() {
		setPosition(0,0);
		setSize(1,1);
	}
	public GPictureEntity(double x, double y, double sx, double sy, String imageName) {
		setPosition(x,y);
		
		System.out.println(image);
		image = StdDraw.getImage(imageName);
		System.out.println(image);
		
		setSize(sx,sy);
	}
	
	protected Vector getSize() {
		return size;
	}
	public void setSize(Vector size) {
		this.size = size;
		reloadCanvas();
	}
	public void setSize(double x, double y) {
		setSize(new Vector(x,y));
	}
	public void setPicture(String imageName) {
		this.image = StdDraw.getImage(imageName);
	}
	
	protected Vector getPosition() {
		return new Vector(super.getPosition().x-getSize().x/2,super.getPosition().y-getSize().y/2);
	}
	
	protected Image getImage() {
		
		imageEdit.drawImage(image, 0,0, null);
		return imageBuffer;
	}
	protected void reloadCanvas() {
		imageBuffer = new BufferedImage((int)size.x, (int)size.y, BufferedImage.TYPE_INT_ARGB);
		imageEdit = imageBuffer.createGraphics();
	}
	

}
