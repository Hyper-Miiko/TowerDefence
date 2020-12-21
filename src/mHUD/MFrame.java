package mHUD;


import static fr.tm_nlm.tower_defence.Constant.*;
import fr.tm_nlm.tower_defence.Constant;

import mHUD.data.VectorInt;

public abstract class MFrame extends MObject {
	private Constant verticalAlignement = CENTER;
	private Constant honrizontalAlignement = CENTER;
	private VectorInt minimumSize = new VectorInt(0,0);
	private VectorInt elementSize = new VectorInt(0,0);
	
	public void setVerticalAlignement(Constant c) {
		if(c == CENTER || c == TOP || c == BOTTOM) {
			verticalAlignement = c;
			recalculateUp();
		}
		else throw new IllegalArgumentException("Axis must be TOP, CENTER or BOTTOM");
		
	}
	public void setHonrizontalAlignement(Constant c) {
		if(c == CENTER || c == LEFT || c == RIGHT) {
			honrizontalAlignement = c;
			recalculateUp();
		}
		else throw new IllegalArgumentException("Absciss must be LEFT, CENTER or RIGHT");
	}
	protected Constant getVerticalAlignement() {
		return verticalAlignement;
	}
	protected Constant getHonrizontalAlignement() {
		return honrizontalAlignement;
	}
	
	public VectorInt getElementPositon() {
		int px = 0;
		int py = 0;
		
		switch(getHonrizontalAlignement()) {
		case CENTER:
			px = getPos().x;
			break;
		case RIGHT:
			px = getPos().x-getSize().x+getElementSize().x;
			break;
		case LEFT:
			px = getPos().x+getSize().x-getElementSize().x;
			break;
		default:
			throw new IllegalArgumentException("Absciss must be LEFT, CENTER or RIGHT");
		}
		
		switch(getVerticalAlignement()) {
		case CENTER:
			py = getPos().y;
			break;
		case BOTTOM:
			py = getPos().y-getSize().y+getElementSize().y;
			break;
		case TOP:
			py = getPos().y+getSize().y-getElementSize().y;
			break;
		default:
			throw new IllegalArgumentException("Axis must be TOP, CENTER or BOTTOM");
		}
		return new VectorInt(px, py);
	}
	
 	public void setMinimumSize(int x, int y) {
		minimumSize = new VectorInt(x,y);
		recalculateUp();
	}
	public void setMinimumSize(VectorInt size) {
		minimumSize = size;
		recalculateUp();
	}
	public VectorInt getMinimumSize() {
		return minimumSize;
	}
	
	protected void setElementSize(int d, int e) {
		elementSize = new VectorInt(d,e);
	}
	protected void setElementSize(VectorInt size) {
		elementSize = size;
	}
	protected VectorInt getElementSize() {
		return elementSize;
	}
	
	protected void setSize(VectorInt s) {
		setSize(s.x, s.y);
	}
	protected void setSize(int sx, int sy) {
		if(sx < getMinimumSize().x)sx = getMinimumSize().x;
		if(sy < getMinimumSize().y)sy = getMinimumSize().y;
		super.setSize(sx, sy);
	}
	
	public void  addObject(MObject o) {
		child.add(o);
		o.setWindowSize(getWindowSize());
		recalculateUp();
		o.setMother(this);
	}
	public void  removeObject(MObject o) {
		child.remove(o);
		recalculateUp();
	}
	
	protected void recalculateUp() {
		if(mother != null)mother.recalculateUp();
		else recalculateDown();
	}
	protected void recalculateDown() {
		for(MFrame c : getFrameChild()) 
			c.recalculateDown();
	}
	
	protected void draw() {
		drawBackground();
		drawRect();
		
		for(MObject i : child) {
			i.draw();
		}
	}
}
