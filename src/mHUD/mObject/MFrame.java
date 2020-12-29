package mHUD.mObject;


import static fr.tm_nlm.tower_defence.Constant.*;
import fr.tm_nlm.tower_defence.Constant;
import mHUD.geometric.Vector;

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
		return new Vector(px, py);
	}
	
 	public void setMinimumSize(double x, double y) {
		minimumSize = new Vector(x,y);
		recalculateUp();
	}
	public void setMinimumSize(Vector size) {
		minimumSize = size;
		recalculateUp();
	}
	public Vector getMinimumSize() {
		return minimumSize;
	}
	
	protected void setElementSize(double d, double e) {
		elementSize = new Vector(d,e);
	}
	protected void setElementSize(Vector size) {
		elementSize = size;
	}
	protected Vector getElementSize() {
		return elementSize;
	}
	
	protected void setSize(Vector s) {
		setSize(s.x, s.y);
	}
	protected void setSize(double sx, double sy) {
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
		if(isRedrawNeeded()) {
			drawBackground();
			drawRect();
			setNeedRedraw(false);	
		}
		for(MObject i : child) {
			i.draw();
		}
	}
}
