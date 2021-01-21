package mHUD.mGraphicEntity;

import java.awt.AlphaComposite;
import java.awt.Image;
import java.awt.image.BufferedImage;

import mHUD.geometric.Vector;

public class GMonsterEntity extends GPictureEntity {
	
	int spX = 0;
	
	public GMonsterEntity() {
		setPosition(0,0);
	}
	public GMonsterEntity(double x, double y, String imageName) {
		setPosition(x,y);
		setPicture(imageName);
	}
	
	protected Vector getPosition() {
		return new Vector(super.getPosition().x+(int)(size.x/2),super.getPosition().y+(int)(size.y/2));
	}
	
	protected Image getImage() {
		imageEdit.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
		imageEdit.fillRect(0,0,hyp,hyp);
		imageEdit.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
		
		int directionX = 0;
		int directionY = 0;
		
		
		double r = 2*Math.PI-getRotation();
		if((r < Math.PI/4 && r >= 0) || (r < 5*Math.PI/4 && r >= 3*Math.PI/4) || (r < 2*Math.PI && r >= 7*Math.PI/4))directionX = 68;
		if(r > 3*Math.PI/4 && r < 7*Math.PI/4)directionY = 50;
		
		imageEdit.drawImage(image,-directionX-spX,-directionY, null);
		
		if(System.nanoTime()%500000000 > 250000000)spX = 34;
		else spX = 0;
		
		return imageBuffer;
	}
	protected void reloadCanvas() {
		imageBuffer = new BufferedImage((int)(size.x)/4,(int)(size.y)/2, BufferedImage.TYPE_INT_ARGB);
		imageEdit = imageBuffer.createGraphics();
	}
}
