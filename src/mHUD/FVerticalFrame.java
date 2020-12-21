package mHUD;

import mHUD.data.VectorInt;

public class FVerticalFrame extends MFrame 
{
	protected void recalculateUp() {
		int sx = 0;
		int sy = 0;
		for(MObject c : child) {
			sy += c.getSize().y;
			sx = Math.max(c.getSize().x, sx);
		}
		this.setElementSize(sx,sy);
		this.setSize(sx, sy);
		
		super.recalculateUp();
	}
	
	protected void recalculateDown() {
		if(mother == null)this.setPos((int)(0.5*getWindowSize().x),(int)(0.5*getWindowSize().y));
		int sumY = 0;
		
		VectorInt elementCenter = getElementPositon();
		
		for(MObject c : child) {			
			c.setPos((int)(getPos().x), (int)(elementCenter.y-getElementSize().y+c.getSize().y+sumY*2));
			sumY+=c.getSize().y;
		}
		
		super.recalculateDown();
	}
}