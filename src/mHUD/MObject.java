package mHUD;

import java.util.LinkedHashSet;
import java.util.Set;

import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import fr.tm_nlm.tower_defence.control.data.geometric.shape.Rect;

public abstract class MObject {
	protected MFrame mother = null;
	protected Set<MObject> child =  new LinkedHashSet<MObject>();
	
	private MColor backgroundColor = new MColor(255,255,255);
	private MColor lineColor = new MColor(0,0,0);
	
	private Rect rectShape = new Rect(0.5,0.5,1,1);

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
	
	protected void setPx(double x) {
		rectShape.setPosition(new Vector(x,rectShape.getPosition().y));
	}
	protected void setPy(double y) {
		rectShape.setPosition(new Vector(rectShape.getPosition().x, y));
	}
	protected void setSx(double sx) {
		rectShape.setSize(new Vector(sx, getSy()));
	}
	protected void setSy(double sy) {
		rectShape.setSize(new Vector(getSx(), sy));
	}
	
	protected void setPos(Vector p) {
		rectShape.setPosition(p);
	}
	protected void setPos(double x, double y) {
		rectShape.setPosition(new Vector(x,y));
	}
	protected void setSize(Vector s) {
		rectShape.setSize(s);
	}
	protected void setSize(double sx, double sy) {
		rectShape.setSize(new Vector(sx, sy));
	}
	
	protected void setRect(Rect r) {
		this.rectShape = r;
	}
	protected void setRect(double x, double y, double tx, double ty) {
		this.rectShape = new Rect(x,y,tx,ty);
	}
	
	public void setBackgroundColor(int r, int g, int b) {
		backgroundColor = new MColor(r,g,b);
	}
	public void setLineColor(int r, int g, int b) {
		lineColor = new MColor(r,g,b);
	}
	
	protected void drawBackground() {
		StdDraw.setPenColor(backgroundColor.Red,backgroundColor.Green,backgroundColor.Blue);
		StdDraw.filledRectangle(getPx(),getPy(),getSx(),getSy());
	}
	protected void drawRect() {
		StdDraw.setPenColor(lineColor.Red,lineColor.Green,lineColor.Blue);
		StdDraw.rectangle(getPx(),getPy(),getSx(),getSy());
	}
	
	protected abstract void draw();
}
