package mHUD;

import fr.tm_nlm.tower_defence.control.data.geometric.Vector;

public class FHorizontalFrame extends MFrame 
{
	protected void recalculateUp() {
		double sx = 0;
		double sy = 0;
		for(MObject c : child) {
			sx += c.getSx();
			sy = Math.max(c.getSy(), sy);
		}
		this.setElementSize(sx,sy);
		this.setSize(sx, sy);
		
		super.recalculateUp();
	}
	
	protected void recalculateDown() {
		if(mother == null)this.setPos(0.5,0.5);
		float sumX = 0;
		
		Vector elementCenter = getElementPositon();
		
		for(MObject c : child) {			
			c.setPos(elementCenter.x-getElementSize().x+c.getSx()+sumX*2,elementCenter.y);
			sumX+=c.getSx();
		}
		
		super.recalculateDown();
	}
}