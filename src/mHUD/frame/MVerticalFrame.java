package mHUD.frame;

import fr.tm_nlm.tower_defence.control.data.geometric.Vector;
import mHUD.MObject;

public class MVerticalFrame extends MFrame 
{
	public void recalculateUp() {
		Vector v = new Vector(0,0);
		
		for(MObject c : child) {
			v.y += c.getSy();
			v.x = Math.max(c.getSx(), v.x);
			
		}
		
		this.setSize(v);
		
		super.recalculateUp();
	}
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