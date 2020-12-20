package mHUD.frame;


import static fr.tm_nlm.tower_defence.Constant.*;
import fr.tm_nlm.tower_defence.Constant;

import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import mHUD.*;

public abstract class MFrame extends MObject {
	private Constant verticalAlignement = CENTER;
	private Constant honrizontalAlignement = CENTER;
	private Vector minimumSize = new Vector(0,0);
	private Vector elementSize = new Vector(0,0);
	
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
	
	public Vector getElementPositon() {
		double px = 0;
		double py = 0;
		
		switch(getHonrizontalAlignement()) {
		case CENTER:
			px = getPx();
			break;
		case RIGHT:
			px = getPx()-getSx()+getElementSize().x;
			break;
		case LEFT:
			px = getPx()+getSx()-getElementSize().x;
			break;
		default:
			throw new IllegalArgumentException("Absciss must be LEFT, CENTER or RIGHT");
		}
		
		switch(getVerticalAlignement()) {
		case CENTER:
			py = getPy();
			break;
		case BOTTOM:
			py = getPy()-getSy()+getElementSize().y;
			break;
		case TOP:
			py = getPy()+getSy()-getElementSize().y;
			break;
		default:
			throw new IllegalArgumentException("Axis must be TOP, CENTER or BOTTOM");
		}
		return new Vector(px, py);
	}
	
 	public void setMinimumSize(double d, double e) {
		minimumSize = new Vector(d,e);
		recalculateUp();
	}
	public void setMinimumSize(Vector size) {
		minimumSize = size;
		recalculateUp();
	}
	public Vector getMinimumSize() {
		return minimumSize;
	}
	
	public void setElementSize(double d, double e) {
		elementSize = new Vector(d,e);
	}
	public void setElementSize(Vector size) {
		elementSize = size;
	}
	public Vector getElementSize() {
		return elementSize;
	}
	
	public void setSx(double sx) {
		if(sx < getMinimumSize().x)sx = getMinimumSize().x;
		super.setSx(sx);
	}
	public void setSy(double sy) {
		if(sy < getMinimumSize().y)sy = getMinimumSize().y;
		super.setSy(sy);
	}
	
	public void setSize(Vector s) {
		setSize(s.x, s.y);
	}
	public void setSize(double sx, double sy) {
		if(sx < getMinimumSize().x)sx = getMinimumSize().x;
		if(sy < getMinimumSize().y)sy = getMinimumSize().y;
		super.setSize(sx, sy);
	}
	
	public void  addObject(MObject o) {
		child.add(o);
		recalculateUp();
		o.setMother(this);
	}
	public void  removeObject(MObject o) {
		child.remove(o);
		recalculateUp();
	}
	
	public void recalculateUp() {
		if(mother != null)mother.recalculateUp();
		else recalculateDown();
	}
	public void recalculateDown() {
		for(MFrame c : getFrameChild()) 
			c.recalculateDown();
	}
	
	public void draw() {
		drawBackground();
		drawRect();
		
		for(MObject i : child) {
			i.draw();
		}
	}
}
