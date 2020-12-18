package mHUD.frame;

import mHUD.*;

public abstract class MFrame extends MObject {
	
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
		//StdDraw.rectangle(getPx(),getPy(),getSx(),getSy());
		for(MObject i : child) {
			i.draw();
		}
	}
}
