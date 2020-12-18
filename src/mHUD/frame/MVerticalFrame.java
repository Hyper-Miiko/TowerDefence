package mHUD.frame;

import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import mHUD.MObject;

public class MVerticalFrame extends MFrame 
{
	@Override
	public void recalculateUp() {
		Vector v;
		double vx = 0;
		double vy = 0;
		
		for(MObject c : child) {
			vy += c.getSy();
			vx = Math.max(c.getSx(), vx);
			
		}
		
		v = new Vector(vx, vy);
		
		this.setSize(v);
		
		super.recalculateUp();
	}
	
	@Override
	public void recalculateDown() {
		if(mother == null)this.setPos(0.5,0.5);
		float sumY = 0;
		
		for(MObject c : child) {
			c.setPos(this.getPx(),this.getPy()-this.getSy()+c.getSy()+sumY*2);
			sumY+=c.getSy();
		}
		
		super.recalculateDown();
	}

}