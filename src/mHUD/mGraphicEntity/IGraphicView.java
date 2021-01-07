package mHUD.mGraphicEntity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import fr.tm_nlm.tower_defence.Couple;
import mHUD.StdDraw;
import mHUD.mObject.MItem;

public class IGraphicView extends MItem {
	private boolean active = true;
	private boolean running = false;
	private boolean clicked = false;
	private LinkedList<Couple<MGraphicEntity, Couple<Double,Double>>> savedEntity = new LinkedList<>();
	
	private Set<MGraphicEntity> entityList =  new LinkedHashSet<MGraphicEntity>();
	
	private Graphics2D imageEdit;
	private BufferedImage imageBuffer;
	private java.awt.Color color = new Color(255,255,255);
	
	public IGraphicView(double x, double y) {
		setSize(x,y);
		imageBuffer = new BufferedImage((int)getSize().x-1, (int)getSize().y-1, BufferedImage.TYPE_INT_ARGB);
		imageEdit = imageBuffer.createGraphics();
	}

	public void setActive(boolean a) {
		active = a;
	}
	public boolean isRunning() {
		return running;
	}
	
	public void addGraphicEntity(MGraphicEntity e) {
		if(!running)entityList.add(e);
	}
	public void removeGraphicEntity(MGraphicEntity e) {
		if(!running)entityList.remove(e);
	}
	
	protected void refreshObject() {
		if(active) {
			running = true;
			if(mousePressed() && !clicked) {
				clicked = true;
					for(MGraphicEntity e : entityList) {
						if(e.isIn(mouseX(),mouseY())) {
							savedEntity.add(new Couple<>(e, new Couple<>(mouseX(),mouseY())));
						}
					}
			}
			if(!mousePressed() && clicked) clicked = false;
			running = false;
		}
	}
	
	public void setBackgroundColor(int r, int g, int b) {
		this.setBackgroundColor(new Color(r,g,b));
	}
	public void setBackgroundColor(Color c) {
		if(c != color) {
			setNeedRedraw(true);
			color = c;
		}
	}
	
	protected void draw() {
		if(active) {
			
			running = true;
			imageEdit.setColor(color);
			imageEdit.fill(new Rectangle(0,0,(int)getSize().x-1, (int)getSize().y-1));
			
			for(MGraphicEntity e : entityList) {
				imageEdit.drawImage(e.getImage(),(int)e.getPosition().x,(int)e.getPosition().y, null);
			}
			
			StdDraw.picture(getPos().x/getWindowSize().x, getPos().y/getWindowSize().y, imageBuffer);
			running = false;
		}
	}
	public double mouseX() {
		return (StdDraw.mouseX()*getWindowSize().x/2)-getPos().x/2+getSize().x/2;
	}
	public double mouseY() {
		return (getWindowSize().y/2 - StdDraw.mouseY()*getWindowSize().y/2)-getPos().y/2+getSize().y/2;
	}
	public boolean mousePressed() {
		return  StdDraw.isMousePressed() && mouseIn();
	}
	public boolean haveActiveEntity() {
		return !savedEntity.isEmpty();
	}
	public Couple<MGraphicEntity, Couple<Double,Double>> getActiveEntity() {
		return savedEntity.pollFirst();
	}
}
