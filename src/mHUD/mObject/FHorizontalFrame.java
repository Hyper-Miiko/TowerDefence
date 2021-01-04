package mHUD.mObject;

import mHUD.geometric.Vector;

public class FHorizontalFrame extends MFrame 
{
	protected void recalculateUp() {
		double sx = 0;
		double sy = 0;
		for(MObject c : child) {
			sx += c.getSize().x;
			sy = Math.max(c.getSize().y, sy);
		}
		this.setElementSize(sx,sy);
		this.setSize(sx, sy);
		
		super.recalculateUp();
	}
	
	protected void recalculateDown() {
		if(mother == null)this.setPos((0.5*getWindowSize().x),(0.5*getWindowSize().y));
		int sumX = 0;
		
		Vector elementCenter = getElementPositon();
		
		for(MObject c : child) {		
			c.setPos((elementCenter.x-getElementSize().x+c.getSize().x+sumX*2),(elementCenter.y));
			sumX+=c.getSize().x;
		}
		
		super.recalculateDown();
	}
}
