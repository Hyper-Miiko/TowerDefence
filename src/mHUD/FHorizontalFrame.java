package mHUD;

import mHUD.data.VectorInt;

public class FHorizontalFrame extends MFrame 
{
	protected void recalculateUp() {
		int sx = 0;
		int sy = 0;
		for(MObject c : child) {
			sx += c.getSize().x;
			sy = Math.max(c.getSize().y, sy);
		}
		this.setElementSize(sx,sy);
		this.setSize(sx, sy);
		
		super.recalculateUp();
	}
	
	protected void recalculateDown() {
		if(mother == null)this.setPos((int)(0.5*getWindowSize().x),(int)(0.5*getWindowSize().y));
		int sumX = 0;
		
		VectorInt elementCenter = getElementPositon();
		
		for(MObject c : child) {			
			c.setPos((int)(elementCenter.x-getElementSize().x+c.getSize().x+sumX*2),(int)(getPos().y));
			sumX+=c.getSize().x;
		}
		
		super.recalculateDown();
	}
}
