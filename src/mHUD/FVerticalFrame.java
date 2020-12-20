package mHUD;

import fr.tm_nlm.tower_defence.control.data.geometric.Vector;

public class FVerticalFrame extends MFrame 
{
	protected void recalculateUp() {
		double sx = 0;
		double sy = 0;
		for(MObject c : child) {
			sy += c.getSy();
			sx = Math.max(c.getSx(), sx);
		}
		this.setElementSize(sx,sy);
		this.setSize(sx, sy);
		
		super.recalculateUp();
	}
	
	protected void recalculateDown() {
		if(mother == null)this.setPos(0.5,0.5);
		float sumY = 0;
		
		Vector elementCenter = getElementPositon();
		
		for(MObject c : child) {			
			c.setPos(elementCenter.x, elementCenter.y-getElementSize().y+c.getSy()+sumY*2);
			sumY+=c.getSx();
		}
		
		super.recalculateDown();
	}
}