package mHUD;

import java.util.LinkedHashSet;
import java.util.Set;

import mHUD.data.Color;
import mHUD.data.VectorInt;

public abstract class MObject {
	protected MFrame mother = null;
	protected Set<MObject> child =  new LinkedHashSet<MObject>();
	
	private Color backgroundColor = new Color(255,255,255);
	private Color lineColor = new Color(0,0,0);
	
	private VectorInt position = new VectorInt(0,0);
	private VectorInt size = new VectorInt(0,0);
	private VectorInt windowSize = new VectorInt(1,1);

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
	
	public VectorInt getPos() {
		return position;
	}
	public VectorInt getSize() {
		return size;
	}
	
	protected void setPosX(int x) {
		position = new VectorInt(x, position.y);
	}
	protected void setPosY(int y) {
		position = new VectorInt(position.x, y);
	}
	protected void setSizeX(int x) {
		size = new VectorInt(x, size.y);
	}
	protected void setSizeY(int y) {
		size = new VectorInt(size.x, y);
	}
	
	protected void setPos(VectorInt p) {
		position = p;
	}
	protected void setPos(int x, int y) {
		position = new VectorInt(x, y);
	}
	protected void setSize(VectorInt s) {
		size = new VectorInt(s.x, s.y);
	}
	protected void setSize(int x, int y) {
		size = new VectorInt(x, y);
	}
	
	public void setBackgroundColor(int r, int g, int b) {
		backgroundColor = new Color(r,g,b);
	}
	public void setLineColor(int r, int g, int b) {
		lineColor = new Color(r,g,b);
	}
	
	protected void drawBackground() {
		StdDraw.setPenColor(backgroundColor.Red,backgroundColor.Green,backgroundColor.Blue);
		StdDraw.filledRectangle((double)position.x/getWindowSize().x,
								(double)position.y/getWindowSize().y, 
								(double)size.x/getWindowSize().x, 
								(double)size.y/getWindowSize().y);
	}
	protected void drawRect() {
		StdDraw.setPenColor(lineColor.Red,lineColor.Green,lineColor.Blue);
		StdDraw.rectangle((double)position.x/getWindowSize().x, 
						  (double)position.y/getWindowSize().y, 
						  (double)size.x/getWindowSize().x, 
						  (double)size.y/getWindowSize().y);
	}
	
	protected void setWindowSize(int x, int y) {
		windowSize = new VectorInt(x,y);
		for(MObject c : child) c.setWindowSize(x,y);
	}
	protected void setWindowSize(VectorInt v) {
		windowSize = v;
		for(MObject c : child) c.setWindowSize(v);
	}
	protected VectorInt getWindowSize() {
		return windowSize;
	}
	
	protected abstract void draw();
}
