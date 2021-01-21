package mHUD.mObject;

import java.awt.Color;
import java.util.LinkedHashSet;
import java.util.Set;

import mHUD.StdDraw;
import mHUD.geometric.Vector;

public abstract class MObject {
	protected MFrame mother = null;
	protected Set<MObject> child =  new LinkedHashSet<MObject>();
	
	private Color backgroundColor = new Color(255,255,255);
	private Color lineColor = new Color(0,0,0);
	
	private Vector position = new Vector(0,0);
	private Vector size = new Vector(0,0);
	private Vector windowSize = new Vector(1,1);

	private boolean needRedraw = false;
	
	protected void setMother(MFrame f) {
		if(f != null)mother = f;
		else if(mother != null)mother.removeObject(this);
	}
	protected MFrame getMother() {
		return mother;
	}
	protected Set<MFrame> getFrameChild() {
		Set<MFrame> fc = new LinkedHashSet<MFrame>();
		for(MObject c : child) {
			if(c instanceof MFrame) {
				fc.add(((MFrame) c));
			}
		}
		return fc;
	}
	
	public Vector getPos() {
		return position;
	}
	public Vector getSize() {
		return size;
	}
	
	protected void setPosX(double x) {
		if(getPos().x != x) {
			setNeedRedraw(true);
			position = new Vector(x, position.y);
		}
	}
	protected void setPosY(double y) {
		if(getPos().y != y) {
			setNeedRedraw(true);
			position = new Vector(position.x, y);
		}
	}
	protected void setSizeX(double x) {
		if(getSize().x != x) {
			setNeedRedraw(true);
			size = new Vector(x, size.y);
		}
	}
	protected void setSizeY(double y) {
		if(getSize().y != y) {
			setNeedRedraw(true);
			size = new Vector(size.x, y);
		}
	}
	
	protected void setPos(Vector p) {
		if(!getPos().equals(p)) {
			setNeedRedraw(true);
			position = p;
		}
	}
	protected void setPos(double x, double y) {
		setPosX(x);
		setPosY(y);
	}
	protected void setSize(Vector s) {
		if(!getSize().equals(s)) {
			setNeedRedraw(true);
			size = s;
		}
	}
	protected void setSize(double x, double y) {
		setSizeX(x);
		setSizeY(y);
	}
	
	public void setBackgroundColor(int r, int g, int b) {
		setBackgroundColor(new Color(r,g,b));
	}
	public void setLineColor(int r, int g, int b) {
		setLineColor(new Color(r,g,b));
	}
	public void setBackgroundColor(Color c) {
		if(c != backgroundColor) {
			setNeedRedraw(true);
			backgroundColor = c;
		}
	}
	public void setLineColor(Color c) {
		if(c != lineColor) {
			setNeedRedraw(true);
			lineColor = c;
		}
	}
	
	protected void drawBackground() {
		StdDraw.setPenColor(backgroundColor);
		StdDraw.filledRectangle(position.x/getWindowSize().x,
								position.y/getWindowSize().y, 
								size.x/getWindowSize().x, 
								size.y/getWindowSize().y);
	}
	protected void drawRect() {
		StdDraw.setPenColor(lineColor);
		StdDraw.rectangle(position.x/getWindowSize().x, 
						  position.y/getWindowSize().y, 
						  size.x/getWindowSize().x, 
						  size.y/getWindowSize().y);
	}
	
	protected void setWindowSize(double x, double y) {
		windowSize = new Vector(x,y);
		for(MObject c : child) c.setWindowSize(x,y);
	}
	protected void setWindowSize(Vector v) {
		windowSize = v;
		for(MObject c : child) c.setWindowSize(v);
	}
	protected Vector getWindowSize() {
		return windowSize;
	}
	
	protected boolean mouseIn() {
		return (StdDraw.mouseX()*getWindowSize().x > position.x-size.x &&
				StdDraw.mouseY()*getWindowSize().y > position.y-size.y &&
			    StdDraw.mouseX()*getWindowSize().x < position.x+size.x &&
			    StdDraw.mouseY()*getWindowSize().y < position.y+size.y);
				
	}
	protected boolean pointIn(double x, double y) {
		return (x > position.x-size.x &&
			    y > position.y-size.y &&
			    x < position.x+size.x &&
			    y < position.y+size.y);
				
	}
	
	protected abstract void draw();
	
	protected boolean isRedrawNeeded() {
		return needRedraw;
	}
	protected void setNeedRedraw(boolean needRedraw) {
		this.needRedraw = needRedraw;
	}
	protected abstract void refreshObject();
}
