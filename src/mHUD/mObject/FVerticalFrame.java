package mHUD.mObject;

import mHUD.geometric.Vector;

public class FVerticalFrame extends MFrame 
{
	protected void recalculateUp() {
		double sx = 0;
		double sy = 0;
		for(MObject c : child) {
			sy += c.getSize().y;
			sx = Math.max(c.getSize().x, sx);
		}
		this.setElementSize(sx,sy);
		this.setSize(sx, sy);
		
		super.recalculateUp();
	}
	
	protected void recalculateDown() {
		if(mother == null)this.setPos((0.5*getWindowSize().x),(0.5*getWindowSize().y));
		double sumY = 0;
		
		Vector elementCenter = getElementPositon();
		
		for(MObject c : child) {			
			c.setPos((elementCenter.x), (elementCenter.y-getElementSize().y+c.getSize().y+sumY*2));
			sumY+=c.getSize().y;
		}
		
		super.recalculateDown();
	}
}