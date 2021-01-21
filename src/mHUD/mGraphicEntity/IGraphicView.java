package mHUD.mGraphicEntity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

import fr.tm_nlm.tower_defence.Couple;
import mHUD.StdDraw;
import mHUD.mObject.MItem;

public class IGraphicView extends MItem {
	private boolean clicked = false;
	private LinkedList<Couple<MGraphicEntity, Couple<Double,Double>>> savedEntity = new LinkedList<>();
	private ArrayList<LinkedList<MGraphicEntity>> entityList = new ArrayList<LinkedList<MGraphicEntity>>();
	private Image background = null;
	//private ArrayList<MGraphicEntity> entityList =  new ArrayList<MGraphicEntity>();
	
	public static int arraySize;
	
	private Graphics2D imageEdit;
	private BufferedImage imageBuffer;
	private java.awt.Color color = new Color(255,255,255);
	
	@SuppressWarnings("unchecked")
	public IGraphicView(double x, double y, int arraySize) {
		setSize(x,y);
		this.setNeedRedraw(true);
		imageBuffer = new BufferedImage((int)getSize().x-1, (int)getSize().y-1, BufferedImage.TYPE_INT_ARGB);
		imageEdit = imageBuffer.createGraphics();
		
		this.arraySize = arraySize;
		for(int i = 0; i < this.arraySize; i++) {
			entityList.add(new LinkedList<MGraphicEntity>());
		}
	}
	
	public void addGraphicEntityAt(int n,MGraphicEntity e) {
		removeGraphicEntity(e);
		entityList.get(n).add(e);
	}
	public void removeGraphicEntity(MGraphicEntity e) {
		for(int i = 0; i < entityList.size(); i++) {
			if(entityList.get(i).contains(e)) {
				entityList.get(i).remove(e);
				break;
			}
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
	
	protected void refreshObject() {
			if(mousePressed() && !clicked) {
				clicked = true;
				for(int i = 0; i < entityList.size(); i++) {
					for(MGraphicEntity e : entityList.get(i)) {
						if(e != null && e.isIn(mouseX(),mouseY())) {
							savedEntity.add(new Couple<>(e, new Couple<>(mouseX(),mouseY())));
						}
					}
				}
			}
			if(!mousePressed() && clicked) clicked = false;
	}
	
	protected void draw() {	
		if(background != null)imageEdit.drawImage(background,0,0, null);
		
		for(int i = 0; i < entityList.size(); i++) {
			for(MGraphicEntity e : entityList.get(i)) {
				if(e != null && e.isDisplay())imageEdit.drawImage(e.getImage(),(int)e.getPosition().x,(int)e.getPosition().y, null);
			}
		}	
		StdDraw.picture(getPos().x/getWindowSize().x, getPos().y/getWindowSize().y, imageBuffer);
	}
	
	public double mouseX() {
		return (StdDraw.mouseX()*getWindowSize().x/2)-getPos().x/2+getSize().x/2;
	}
	public double mouseY() {
		return Math.abs(StdDraw.mouseY()*getWindowSize().y/2 - getWindowSize().y/2)-Math.abs(getPos().y-getWindowSize().y/2);
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

	public void setBackground(Image background) {
		this.background = background;
	}
}
