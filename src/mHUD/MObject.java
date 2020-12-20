package mHUD;

import java.util.HashSet;
import java.util.Set;

import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.data.geometric.shape.Rect;
import mHUD.frame.MFrame;

public abstract class MObject {
	protected MFrame mother = null;
	protected Set<MObject> child =  new HashSet<MObject>();
	
	private Rect rectShape;
	
	protected MObject() {
		setRect(0.5,0.5,1,1);
	}

	public void setMother(MFrame f) {
		if(f != null)mother = f;
		else if(mother != null)mother.removeObject(this);
	}
	public MFrame getMother() {
		return mother;
	}
	public Set<MFrame> getFrameChild() {
		Set<MFrame> fc = new HashSet<MFrame>();
		for(MObject c : child) {
			if(c instanceof MFrame) {
				fc.add(((MFrame) c));
			}
		}
		return fc;
	}
	
	public double getPx() {
		return rectShape.getPosition().x;
	}
	public double getPy() {
		return rectShape.getPosition().y;
	}
	public double getSx() {
		return rectShape.getSize().x;
	}
	public double getSy() {
		return rectShape.getSize().y;
	}
	
	public Vector getPos() {
		return rectShape.getPosition();
	}
	public Vector getSize() {
		return rectShape.getSize();
	}

	public Rect getRect() {
		return rectShape;
	}
	
	public void setPx(double x) {
		rectShape.setPosition(new Vector(x,rectShape.getPosition().y));
	}
	public void setPy(double y) {
		rectShape.setPosition(new Vector(rectShape.getPosition().x, y));
	}
	public void setSx(double sx) {
		rectShape.setSize(new Vector(sx, getSy()));
	}
	public void setSy(double sy) {
		rectShape.setSize(new Vector(getSx(), sy));
	}
	
	public void setPos(Vector p) {
		rectShape.setPosition(p);
	}
	public void setPos(double x, double y) {
		rectShape.setPosition(new Vector(x,y));
	}
	public void setSize(Vector s) {
		rectShape.setSize(s);
	}
	public void setSize(double sx, double sy) {
		rectShape.setSize(new Vector(sx, sy));
	}
	
	public void setRect(Rect r) {
		this.rectShape = r;
	}
	public void setRect(double x, double y, double tx, double ty) {
		this.rectShape = new Rect(new Vector(x,y),tx,ty);
	}
	
	public abstract void draw();
}
