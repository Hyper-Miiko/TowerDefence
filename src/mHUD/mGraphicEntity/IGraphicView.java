package mHUD.mGraphicEntity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedHashSet;
import java.util.Set;

import mHUD.StdDraw;
import mHUD.mObject.MItem;

public class IGraphicView extends MItem {
	
	private Set<MGraphicEntity> entityList =  new LinkedHashSet<MGraphicEntity>();
	
	private Graphics2D imageEdit;
	private BufferedImage imageBuffer;
	private java.awt.Color color = new Color(255,255,255);
	
	public IGraphicView(double x, double y) {
		setSize(x,y);
		imageBuffer = new BufferedImage((int)getSize().x, (int)getSize().y, BufferedImage.TYPE_INT_ARGB);
		imageEdit = imageBuffer.createGraphics();
	}

	public void addGraphicEntity(MGraphicEntity e) {
		entityList.add(e);
	}
	public void removeGraphicEntity(MGraphicEntity e) {
		entityList.remove(e);
	}
	
	protected void draw() {
		imageEdit.setColor(color);
		imageEdit.fill(new Rectangle(0,0,(int)getSize().x-1, (int)getSize().y-1));
		
		for(MGraphicEntity e : entityList)
			imageEdit.drawImage(e.getImage(),(int)e.getPosition().x,(int)e.getPosition().y, null);
		
		StdDraw.picture(getPos().x/getWindowSize().x, getPos().y/getWindowSize().y, imageBuffer);
	}

}
